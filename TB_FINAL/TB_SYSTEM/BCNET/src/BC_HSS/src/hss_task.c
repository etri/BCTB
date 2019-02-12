
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <errno.h>

#include "bcnet.h"

#include "dbmb.h"

#include "xprint.h"
#include "logdir.h"
#include "kutil.h"

#include "hss_main.h"
#include "hss_conf.h"
#include "hss_task.h"


static int CreateCryptoconfigFile(void);
static int CreateConfigtxFile(void);
static int CreateStartordererFile(void);
static int ProcessTelentAutorun(char *host_ip);


int SethostsFile(void)
{
    int i;
    FILE *file;
    char filename[MAX_INFO_LENGTH], message[MAX_INFO_LENGTH];

    xprint(FMS_HSS | FMS_LOOKS, "1. Modify /etc/hosts \n");

    memset(filename, 0x00, sizeof(filename));
#ifdef __TEST__
    sprintf(filename, "%s/bin/hosts", hssConf.EnvPath);
#else
    sprintf(filename, "/etc/hosts");
#endif

    file = fopen(filename, "a");

    memset(message, 0x00, sizeof(message));
    sprintf(message, "\n#Set Hyperledger Fabric Test System\n");
    fwrite(message, sizeof(char), strlen(message), file);

    for (i = 0; i < hssConf.h_count; i++) {
        memset(message, 0x00, sizeof(message));
        sprintf(message, "%s %s\n", hssConf.h_info[i].host_ip, hssConf.h_info[i].host_name);
        fwrite(message, sizeof(char), strlen(message), file);
        nanoSleep(30);
    }

    fclose(file);
    return 0;
}

int SendBcnetCfgFile(void)
{
    int i, b_flag;
    FILE *pfile, *ffile;
    char path[MAX_INFO_LENGTH], data[MAX_DATA_LENGTH], temp[MAX_INFO_LENGTH];
    char cmd[MAX_INFO_LENGTH], log[MAX_DATA_LENGTH];

    /* create cfg file */
    for (i = 0; i < hssConf.h_count; i ++) {
        if (strcmp(hssConf.h_info[i].host_name, "bcmanager") == 0 ||
            strcmp(hssConf.h_info[i].host_name, "kafka-zookeeper") == 0) {
            hssConf.h_info[i].cfg_flag = 1;
            continue;
        }

        memset(data, 0x00, sizeof(data));
        memset(path, 0x00, sizeof(path));
        sprintf(path, "/root/BCNET/cfg/tmp/cfg_%s.cfg", hssConf.h_info[i].host_name);

        ffile = fopen(path, "w");

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "DB_ADDRESS = %s\n", hssConf.DbAddr);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "DB_PORT = %u\n", hssConf.DbPort);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "PEER_ORG_COUNT = %u\n", hssConf.org_count);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "PROFILE_ID = %u\n", hssConf.profile_id);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "PEER_PER_ORG_COUNT = %u\n", hssConf.org_peer_count);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "HOST_NAME = %s\n", hssConf.h_info[i].host_name);
        strcat(data, temp);

        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "CRYPTO_DEL_MODE = %d\n", hssConf.crypto_del);
        strcat(data, temp);

        fwrite(data, sizeof(char), strlen(data), ffile);

        fclose(ffile);
        nanoSleep(50);
    }

    /* send cfg file */
    b_flag = 0;
    for (;;) {
        if (b_flag == 1) {
            break;
        }

        for (i = 0; i < hssConf.h_count; i ++) {
            if (hssConf.h_info[i].cfg_flag == 1) {
                continue;
            }

            memset(cmd, 0x00, sizeof(cmd));
            sprintf(cmd, "/bin/bash %s/bin/ftp_cfg.sh %s cfg_%s.cfg",
                         hssConf.EnvPath, hssConf.h_info[i].host_ip, hssConf.h_info[i].host_name);
            xprint(FMS_HSS | FMS_LOOKS, "cmd [= %s]\n", cmd);

            if ((pfile = popen(cmd, "r")) == NULL) {
                xprint(FMS_HSS | FMS_ERROR, "POPEN Open Fail!!! [= %s]\n", cmd);
                return -1;
            }

            memset(log, 0x00, sizeof(log));
            fread(log, sizeof(char), MAX_DATA_LENGTH, pfile);

            xprint(FMS_HSS | FMS_INFO1, "%s\n", log);

            if (strstr(log, "Invalid command") != NULL ||
                strstr(log, "Not connected") != NULL) {
                xprint(FMS_HSS | FMS_WARNING, "%s :: bcnet.cfg Send Fail!!\n", hssConf.h_info[i].host_name);

            } else {
                if (ProcessTelentAutorun(hssConf.h_info[i].host_ip) == 0) {
                    hssConf.h_info[i].cfg_flag = 1;
                } else {
                    xprint(FMS_HSS | FMS_WARNING, "%s :: Telent Run Fail!!\n", hssConf.h_info[i].host_name);
                }
            }

            pclose(pfile);
            nanoSleep(50);
        }

        b_flag = 1;
        for (i = 0; i < hssConf.h_count; i ++) {
            if (hssConf.h_info[i].cfg_flag == 0) {
                b_flag = 0;
            }
        }

        nanoSleep(1000);
    }

    return 0;
}

int ProcessBcManager(void)
{
    int i, p_count = 0, c_count = 0;
    char cmd[MAX_INFO_LENGTH], tmp1[MAX_INFO_LENGTH], tmp2[MAX_INFO_LENGTH];

    /* get hyperledger TB */
    GetHlfInfoDB(hssConf.profile_id, &hssConf.hlf);

    /* create crypto-config.yaml */
    xprint(FMS_HSS | FMS_INFO1, "Create crypto-config.yaml\n");
    if (CreateCryptoconfigFile() < 0) {
        xprint(FMS_HSS | FMS_ERROR, "crypto-config.yaml create fail!! \n");
        return -1;
    }

    /* create configtx.yaml */
    xprint(FMS_HSS | FMS_INFO1, "Create configtx.yaml\n");
    if (CreateConfigtxFile() < 0) {
        xprint(FMS_HSS | FMS_ERROR, "configtx.yaml create fail!! \n");
        return -1;
    }

#if 1
    /* create crypto-config */
    xprint(FMS_HSS | FMS_LOOKS, "2. Create crypto-config \n");
    memset(cmd, 0x00, sizeof(cmd));
    sprintf(cmd, GEN_CRYPTO_CONFIG);
    xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    system(cmd);

    nanoSleep(100);

    /* cp crypto-config */
    xprint(FMS_HSS | FMS_LOOKS, "3. Copy crypto-config by type org\n");
    for (i = 0; i < hssConf.h_count; i++) {
        if (!(strcmp(hssConf.h_info[i].org_type, "orderer") == 0 || strcmp(hssConf.h_info[i].org_type, "peer") == 0)) {
            continue;
        }

        memset(cmd, 0x00, sizeof(cmd));
#ifdef __TEST__
        sprintf(cmd, "/bin/bash %s/bin/cp_crypto.sh %s %s %s %s %u %u", hssConf.EnvPath,
                      hssConf.h_info[i].org_type, hssConf.h_info[i].org_name, hssConf.h_info[i].host_type,
                      hssConf.h_info[i].host_name, hssConf.org_count, hssConf.org_peer_count);
#else
        sprintf(cmd, "/bin/bash %s/bin/cp_crypto.sh %s %s %s %s %u %u > %s/log/crypto/%s.log", hssConf.EnvPath,
                      hssConf.h_info[i].org_type, hssConf.h_info[i].org_name, hssConf.h_info[i].host_type,
                      hssConf.h_info[i].host_name, hssConf.org_count, hssConf.org_peer_count,
                      hssConf.EnvPath, hssConf.h_info[i].host_name);
#endif
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
        system(cmd);
        nanoSleep(100);
    }


    /* create genesis block */
    /* create Channel configuration transaction */
    /* create Anchor Peer Configuration Transaction */
    xprint(FMS_HSS | FMS_LOOKS,
           "4. Create genesis block / Channel configuration transaction / Anchor Peer Configuration Transaction\n");
    memset(cmd, 0x00, sizeof(cmd));
    memset(tmp1, 0x00, sizeof(tmp1));
    memset(tmp2, 0x00, sizeof(tmp2));
    if (hssConf.org_count == 1) {
        sprintf(tmp1, "OneOrgsOrdererGenesis");
        sprintf(tmp2, "OneOrgsChannel");
    } else if (hssConf.org_count == 2) {
        sprintf(tmp1, "TwoOrgsOrdererGenesis");
        sprintf(tmp2, "TwoOrgsChannel");
    } else if (hssConf.org_count == 3) {
        sprintf(tmp1, "ThreeOrgsOrdererGenesis");
        sprintf(tmp2, "ThreeOrgsChannel");
    } else if (hssConf.org_count == 4) {
        sprintf(tmp1, "FourOrgsOrdererGenesis");
        sprintf(tmp2, "FourOrgsChannel");
    } else {
        xprint(FMS_HSS | FMS_ERROR, "org count is wrong [= %u]\n", hssConf.org_count);
    }

    sprintf(cmd, "/bin/bash %s/bin/set_bcmanaer.sh %s %s %u > %s/log/set_bcmanaer.log",
                  hssConf.EnvPath, tmp1, tmp2, hssConf.org_count, hssConf.EnvPath);
    xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    system(cmd);
    nanoSleep(100);
#endif

    /* update status DB */
    UpdateStatusDB(hssConf.profile_id, hssConf.hname);


    /* waitting until the peer & orderer execute */
    p_count = GetCountPeerOrdererDB(hssConf.profile_id);
    for (;;) {
        c_count = GetPeerOrdererStatusDB(hssConf.profile_id);

        if (p_count == c_count) {
            break;
        }

        xprint(FMS_HSS | FMS_INFO1, "Not Exec Orderer & Peer, So waitting... (%d / %d)\n", c_count, p_count);
        nanoSleep(5000);
    }


    /* create channel */
    /* join channel */
    /* update Anchor peer */
    xprint(FMS_HSS | FMS_LOOKS, "5. Create Channel / join channel / update Anchor peer\n");
    memset(cmd, 0x00, sizeof(cmd));
    sprintf(cmd, "/bin/bash %s/bin/exec_bcmanaer.sh %u %u %u > %s/log/exec_bcmanaer.log", hssConf.EnvPath,
                  hssConf.org_count, hssConf.org_peer_count, hssConf.org_count * hssConf.org_peer_count, hssConf.EnvPath);
    xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    system(cmd);
    nanoSleep(100);

    /* Delete crypto-config */
    if (hssConf.crypto_del != 0) {
        memset(cmd, 0x00, sizeof(cmd));
        sprintf(cmd, "rm -rf /root/bcnetwork/conf/crypto-config");
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
        system(cmd);
        nanoSleep(100);

        /* Delete crypto-config */
        memset(cmd, 0x00, sizeof(cmd));
        sprintf(cmd, "rm -rf /root/BCNET/bcnetwork/*");
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
        system(cmd);
        nanoSleep(100);
    }

    /* update user status DB */
    UpdateUserStatusDB(hssConf.profile_id);

    xprint(FMS_HSS | FMS_LOOKS, "bcmanager Setup END [= %s]\n", hssConf.myhost.host_name);
    return 0;
}

int ProcessOrderer(void)
{
    int s_manager =0, s_kafka = 0;
    char cmd[MAX_INFO_LENGTH];

    /* watting for bcmanager steup */
    for (;;) {
        s_manager = GetBcmanagerStatusDB(hssConf.profile_id);
        s_kafka   = GetKafkaStatusDB(hssConf.profile_id);

        if (s_manager == 2 && s_kafka == 2) {
            break;
        }

        xprint(FMS_HSS | FMS_INFO1, "Not configuration bcmanager(%d) or kafka(%d), So waitting\n", s_manager, s_kafka);
        nanoSleep(5000);
    }

    memset(cmd, 0x00, sizeof(cmd));

    /* exec orderer */
    if (strcmp(hssConf.myhost.host_type, "orderer") == 0) {
        /* get hyperledger TB */
        GetHlfInfoDB(hssConf.profile_id, &hssConf.hlf);

        /* create start-orderer.sh */
        xprint(FMS_HSS | FMS_INFO1, "Create start-orderer.sh\n");
        if (CreateStartordererFile() < 0) {
            xprint(FMS_HSS | FMS_ERROR, "start-orderer.sh create fail!! \n");
            return -1;
        }

        xprint(FMS_HSS | FMS_LOOKS, "2. Exec orderer \n");
        sprintf(cmd, "/bin/bash %s/bin/exec_orderer.sh %s %s > %s/log/exec_orderer.log",
                      hssConf.EnvPath, hssConf.bcmanager_ip, hssConf.hname, hssConf.EnvPath);
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);

    /* exec adminuser */
    } else {
        xprint(FMS_HSS | FMS_LOOKS, "2. Exec orderer adminuser \n");
        sprintf(cmd, "/bin/bash %s/bin/exec_adminuser.sh %s %s > %s/log/exec_adminuser.log",
                      hssConf.EnvPath, hssConf.bcmanager_ip, hssConf.hname, hssConf.EnvPath);
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    }

    system(cmd);

    nanoSleep(1000);

    /* update status DB */
    UpdateStatusDB(hssConf.profile_id, hssConf.hname);

    xprint(FMS_HSS | FMS_LOOKS, "Orderer Setup END [= %s]\n", hssConf.myhost.host_name);
    return 0;
}

int ProcessPeer(void)
{
    int s_manager = 0, s_kafka = 0;
    char cmd[MAX_INFO_LENGTH], num[MAX_ALAIS_LENGTH];

    /* watting for bcmanager steup */
    for (;;) {
        s_manager = GetBcmanagerStatusDB(hssConf.profile_id);
        s_kafka   = GetKafkaStatusDB(hssConf.profile_id);

        if (s_manager == 2 && s_kafka == 2) {
            break;
        }

        xprint(FMS_HSS | FMS_INFO1, "Not configuration bcmanager(%d) or kafka(%d), So waitting\n", s_manager, s_kafka);
        nanoSleep(5000);
    }

    memset(cmd, 0x00, sizeof(cmd));

    /* exec orderer */
    if (strcmp(hssConf.myhost.host_type, "peer") == 0) {
        xprint(FMS_HSS | FMS_LOOKS, "2. Exec peer \n");

        memset(num, 0x00, sizeof(num));
        strncpy(num, &hssConf.myhost.org_name[3], 1);

        sprintf(cmd, "/bin/bash %s/bin/exec_peer.sh %s %s %s %s > %s/log/exec_peer.log",
                      hssConf.EnvPath, hssConf.bcmanager_ip, hssConf.hname, hssConf.myhost.org_name, num, hssConf.EnvPath);
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);

    /* exec adminuser */
    } else {
        xprint(FMS_HSS | FMS_LOOKS, "2. Exec peer adminuser \n");
        sprintf(cmd, "/bin/bash %s/bin/exec_adminuser.sh %s %s > %s/log/exec_adminuser.log",
                      hssConf.EnvPath, hssConf.bcmanager_ip, hssConf.hname, hssConf.EnvPath);
        xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    }

    system(cmd);

    nanoSleep(1000);

    /* update status DB */
    UpdateStatusDB(hssConf.profile_id, hssConf.hname);

    xprint(FMS_HSS | FMS_LOOKS, "Peer Setup END [= %s]\n", hssConf.myhost.host_name);

    return 0;
}

int ProcessKafkaZookeeper(void)
{
    char cmd[MAX_INFO_LENGTH];

    /* watting for bcmanager steup */
    for (;;) {
        if (GetBcmanagerStatusDB(hssConf.profile_id) == 2) {
            break;
        }

        xprint(FMS_HSS | FMS_INFO1, "Not configuration bcmanager, So waitting\n");
        nanoSleep(5000);
    }

    /* exec orderer */
    xprint(FMS_HSS | FMS_LOOKS, "2. Exec kafka \n");
    memset(cmd, 0x00, sizeof(cmd));

    sprintf(cmd, "/bin/bash %s/bin/exec_kafka.sh %s > %s/log/exec_kafka.log",
                  hssConf.EnvPath, hssConf.myhost.host_ip, hssConf.EnvPath);
    xprint(FMS_HSS | FMS_LOOKS, "cmd[= %s]\n", cmd);
    system(cmd);

    nanoSleep(1000);

    /* update status DB */
    UpdateStatusDB(hssConf.profile_id, hssConf.hname);

    xprint(FMS_HSS | FMS_LOOKS, "Kafka Setup END [= %s]\n", hssConf.myhost.host_name);

    return 0;
}

/* create crypto-config.yaml */
static int CreateCryptoconfigFile(void)
{
    int i;
    FILE *file;
    char path[MAX_INFO_LENGTH], data[MAX_DATA_LENGTH], temp[MAX_INFO_LENGTH];

    memset(path, 0x00, sizeof(path));
    memset(data, 0x00, sizeof(data));

    sprintf(path, "/root/bcnetwork/conf/crypto-config.yaml");

    file = fopen(path, "w");

    sprintf(data, "%s", YAML_CRYPTO_CONFIG_1);
    for (i = 0; i < hssConf.hlf.orderer_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "      - Hostname: orderer%d\n", i);
        strcat(data, temp);
    }

    strcat(data, YAML_CRYPTO_CONFIG_2);
    for (i = 0; i < hssConf.hlf.org_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        if (i == 0) {
            sprintf(temp, YAML_CRYPTO_CONFIG_3, i, i, hssConf.hlf.org_peer_count);
        } else {
            sprintf(temp, YAML_CRYPTO_CONFIG_4, i, i, hssConf.hlf.org_peer_count, i * hssConf.hlf.org_peer_count);
        }

        strcat(data, temp);
    }

    fwrite(data, sizeof(char), strlen(data), file);

    fclose(file);
    return 0;
}

/* create configtx.yaml  */
static int CreateConfigtxFile(void)
{
    int i;
    FILE *file;
    char path[MAX_INFO_LENGTH], data[MAX_DATA_LENGTH], temp[MAX_INFO_LENGTH * 2];
    char tmp1[MAX_NAME_LENGTH], tmp2[MAX_NAME_LENGTH];

    memset(path, 0x00, sizeof(path));
    memset(data, 0x00, sizeof(data));

    sprintf(path, "/root/bcnetwork/conf/configtx.yaml");

    if (hssConf.hlf.org_count == 1) {
        sprintf(tmp1, "OneOrgsOrdererGenesis");
        sprintf(tmp2, "OneOrgsChannel");
    } else if (hssConf.hlf.org_count == 2) {
        sprintf(tmp1, "TwoOrgsOrdererGenesis");
        sprintf(tmp2, "TwoOrgsChannel");
    } else if (hssConf.hlf.org_count == 3) {
        sprintf(tmp1, "ThreeOrgsOrdererGenesis");
        sprintf(tmp2, "ThreeOrgsChannel");
    } else if (hssConf.hlf.org_count == 4) {
        sprintf(tmp1, "FourOrgsOrdererGenesis");
        sprintf(tmp2, "FourOrgsChannel");
    } else {
        xprint(FMS_HSS | FMS_ERROR, "org count is wrong [= %u]\n", hssConf.hlf.org_count);
        return -1;
    }


    file = fopen(path, "w");

    /* set OrgsOrdererGenesis */
    sprintf(data, YAML_CONFIGTX_1, tmp1);
    for (i = 0; i < hssConf.hlf.org_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "                    - *Org%d\n", i);
        strcat(data, temp);
    }

    /* set OrgsChannel */
    memset(temp, 0x00, sizeof(temp));
    sprintf(temp, YAML_CONFIGTX_2, tmp2);
    strcat(data, temp);

    for (i = 0; i < hssConf.hlf.org_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "                - *Org%d\n", i);
        strcat(data, temp);
    }

    /* set Organizations */
    memset(temp, 0x00, sizeof(temp));
    sprintf(temp, "%s", YAML_CONFIGTX_3);
    strcat(data, temp);

    for (i = 0; i < hssConf.hlf.org_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, YAML_CONFIGTX_4, i, i, i, i, i * hssConf.hlf.org_peer_count);
        strcat(data, temp);
    }

    /* set Orderer */
    memset(temp, 0x00, sizeof(temp));
    sprintf(temp, YAML_CONFIGTX_5, hssConf.hlf.orderer_type);
    strcat(data, temp);

    for (i = 0; i < hssConf.hlf.orderer_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, "        - orderer%d:7050\n", i);
        strcat(data, temp);
    }

    memset(temp, 0x00, sizeof(temp));
#if 1
    if (strcmp(hssConf.hlf.orderer_type, "kafka") == 0) {
        sprintf(temp, YAML_CONFIGTX_6, hssConf.hlf.batch_timeout, hssConf.hlf.max_message_count,
                hssConf.hlf.absolute_max_bytes, hssConf.hlf.preferred_max_bytes);
    } else {
        sprintf(temp, YAML_CONFIGTX_7, hssConf.hlf.batch_timeout, hssConf.hlf.max_message_count,
                hssConf.hlf.absolute_max_bytes, hssConf.hlf.preferred_max_bytes);
    }

    strcat(data, temp);
#endif

    fwrite(data, sizeof(char), strlen(data), file);

    fclose(file);
    return 0;
}

/* create start-orderer.sh */
static int CreateStartordererFile(void)
{
    int i;
    FILE *file;
    char path[MAX_INFO_LENGTH], data[MAX_DATA_LENGTH], temp[MAX_INFO_LENGTH * 2];

    memset(path, 0x00, sizeof(path));
    memset(data, 0x00, sizeof(data));

    sprintf(path, "/root/gopath/src/github.com/hyperledger/fabric/start-orderer.sh");

    file = fopen(path, "w");

    /* set */
    sprintf(data, SH_START_ORDERER_1, hssConf.hname, hssConf.hname, hssConf.hname, hssConf.hname, hssConf.hname, hssConf.hname);

    for (i = 0; i < hssConf.hlf.org_count; i++) {
        memset(temp, 0x00, sizeof(temp));
        sprintf(temp, SH_START_ORDERER_2, i, i * hssConf.hlf.org_peer_count, i);

        if (i == hssConf.hlf.org_count - 1) {
            strcat(temp, "] \\\n");
        } else {
            strcat(temp, ",");
        }

        strcat(data, temp);
    }

    memset(temp, 0x00, sizeof(temp));
    sprintf(temp, SH_START_ORDERER_3, hssConf.hlf.batch_timeout, hssConf.hlf.orderer_type);
    strcat(data, temp);

    fwrite(data, sizeof(char), strlen(data), file);

    fclose(file);
    return 0;
}

static int ProcessTelentAutorun(char *host_ip)
{
    FILE *pfile;
    int retval = 0;
    char cmd[MAX_INFO_LENGTH], log[MAX_DATA_LENGTH];

    memset(cmd, 0x00, sizeof(cmd));
    memset(log, 0x00, sizeof(log));
    sprintf(cmd, "/bin/bash %s/bin/telnet_run.sh %s", hssConf.EnvPath, host_ip);
    xprint(FMS_HSS | FMS_LOOKS, "cmd [= %s]\n", cmd);

    if ((pfile = popen(cmd, "r")) == NULL) {
        xprint(FMS_HSS | FMS_ERROR, "POPEN Open Fail!!! [= %s]\n", cmd);
        return -1;
    }

    fread(log, sizeof(char), MAX_DATA_LENGTH, pfile);

    xprint(FMS_HSS | FMS_INFO1, "log : %s\n", log);

    if (strstr(log, "Password:") == NULL) {
        retval = -1;

    } else if (strstr(log, "No such file or directory") != NULL ||
        strstr(log, "Connection refused") != NULL) {
        retval = -1;
    }

    pclose(pfile);
    return retval;
}
