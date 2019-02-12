
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <ctype.h>

#include "bcnet.h"
#include "dbmb.h"

#include "xprint.h"
#include "kutil.h"

#include "hss_main.h"
#include "hss_conf.h"


unsigned int Isdigit(char *s, unsigned int *c);
void Gettoken(char *name, char *value);
void Lineparser(char *buf);

int read_config(char *path);
void FreeVariable(void);
int InitVariable(char *path);

hss_config_t hssConf;

/* 16진수, 10진수의 스트링값을 읽어 변환하는 함수 */
unsigned int Isdigit(char *s, unsigned int *c)
{
    size_t i, size;

    size = strlen(s);

    if (strncasecmp(s, "0x", 2) == 0 || strncasecmp(s, "0X", 2) == 0) {    /* 16진수 확인 */
        for (i = 2; i < size; i++) {
            if (!isxdigit((int)s[i])) {
                return(0);
            }
        }
        sscanf(s, "%x", c);
    } else {                                /* 10진수 확인 */
        for (i = 0; i < size; i++) {
            if (!isdigit((int)s[i])) {
                return(0);
            }
        }
        sscanf(s, "%u", c);
    }

    return(1);
}

void Gettoken(char *name, char *value)
{
    unsigned int d, retval;

    /* MySQL DB Information */
    if (!strcmp(name, "DB_ADDRESS")) {
        memset(hssConf.DbAddr, 0x00, sizeof(hssConf.DbAddr));
        strcpy(hssConf.DbAddr, value);
    }

    if (!strcmp(name, "DB_PORT")) {
        retval = Isdigit(value, &d);
        if (retval) {
            hssConf.DbPort = d;
        } else {
            xprint(FMS_HSS | FMS_ERROR, "%s(%d) : Check Config(DB port) File!\n", __func__, __LINE__);
        }
    }

    if (!strcmp(name, "PROFILE_ID")) {
        retval = Isdigit(value, &d);
        if (retval) {
            hssConf.profile_id = d;
        } else {
            xprint(FMS_HSS | FMS_ERROR, "%s(%d) : Check Config(profile id) File!\n", __func__, __LINE__);
        }
    }

    if (!strcmp(name, "PEER_ORG_COUNT")) {
        retval = Isdigit(value, &d);
        if (retval) {
            hssConf.org_count = d;
        } else {
            xprint(FMS_HSS | FMS_ERROR, "%s(%d) : Check Config(peer org count) File!\n", __func__, __LINE__);
        }
    }

    if (!strcmp(name, "PEER_PER_ORG_COUNT")) {
        retval = Isdigit(value, &d);
        if (retval) {
            hssConf.org_peer_count = d;
        } else {
            xprint(FMS_HSS | FMS_ERROR, "%s(%d) : Check Config(peer per org count) File!\n", __func__, __LINE__);
        }
    }

    if (!strcmp(name, "HOST_NAME")) {
        memset(hssConf.hname, 0x00, sizeof(hssConf.hname));
        strcpy(hssConf.hname, value);
    }

    if (!strcmp(name, "CRYPTO_DEL_MODE")) {
        retval = Isdigit(value, &d);
        if (retval) {
            hssConf.crypto_del = d;
        } else {
            xprint(FMS_HSS | FMS_ERROR, "%s(%d) : Check Config(crypto del) File!\n", __func__, __LINE__);
        }
    }

    return;
}

/* 라인별로 스트링을 읽어 파싱한다. */
void Lineparser(char *buf)
{
    int  r, i = 0;
    char c, str[MAX_STR_LENGTH*8], token[5][MAX_STR_LENGTH];

    while (1) {
        c = *(buf + i);
        str[i] = c;
        if (c=='#' || c==0) {
            str[i] = 0;
            break;
        }
        i++;
    }

    r = sscanf(str, "%s%s%s%s%s", token[0], token[1], token[2], token[3], token[4]);
    if (r == 3) {
        Gettoken(token[0], token[2]);
    }
}

int read_config(char *path)
{
    FILE *fp;
    char *c;
    char CfgPath[512];
    char str[MAX_STR_LENGTH*8];

    if (path == NULL) {
        xprint(FMS_HSS | FMS_FATAL, "configuration file path is null at %s\n", __func__);
        return(-1);
    }

    sprintf(CfgPath, "%s/cfg/bcnet.cfg", path);

    if ((fp = fopen(CfgPath, "r")) == (FILE *)NULL) {
        xprint(FMS_HSS | FMS_FATAL, "fopen error(%s) at %s\n", strerror(errno), __func__);
        return(-1);
    }

    while (1) {
        c = fgets(str, sizeof(str), fp);
        if (c == NULL) {
            break;
        }
        Lineparser(str);
    }
    fclose(fp);

    return(0);
}

void FreeVariable(void)
{
    return;
}

int InitVariable(char *path)
{
    int i, h_count = 0;

    memset(&hssConf, 0x00, sizeof(hss_config_t));

    sprintf(hssConf.EnvPath, "%s", path);
    sprintf(hssConf.DbAddr, "127.0.0.1");
    hssConf.DbPort          = 3306;

    sprintf(hssConf.DbUserid, "bctt");
    sprintf(hssConf.DbPass,   "*bctt*");
    sprintf(hssConf.DbName,   "bctt");

    hssConf.crypto_del = 1;

    /* config file check */
    if (read_config(path) < 0) {
        xprint(FMS_HSS | FMS_FATAL, "Configuration error, So exit\n");
        return -1;
    }

    /* MySQL initialize */
    if (DatabaseInit(hssConf.DbAddr, hssConf.DbUserid, hssConf.DbPass, hssConf.DbName, hssConf.DbPort) < 0) {
        xprint(FMS_HSS | FMS_FATAL, "DatabaseInit failed\n");
        return -1;
    }

    /* get host info */
    hssConf.h_count = GetCountHostDB(hssConf.profile_id);
    for (;;) {
        h_count = GetHlfhostInfoDB(hssConf.profile_id, &hssConf.h_info[0]);
        if (hssConf.h_count != h_count) {
            xprint(FMS_HSS | FMS_WARNING, "Not configurationDB Host Info , So waitting (%d / %d)\n", h_count, hssConf.h_count);
            memset(&hssConf.h_info[0], 0x00, sizeof(host_info_t) * MAX_SYSTEM_NUM);
            nanoSleep(5000);
            continue;
        } else {
            break;
        }
    }

    /* get bcmanager ip */
    GetBcmanagerIPDB(hssConf.profile_id, hssConf.bcmanager_ip);

    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");
    xprint(FMS_HSS | FMS_INFO1, "profile id : %u\n", hssConf.profile_id);
    xprint(FMS_HSS | FMS_INFO1, "host count : %u\n", hssConf.h_count);
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");
    xprint(FMS_HSS | FMS_INFO1, "org type\torg name\thost type\thost name\t\thost ip\n");
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");

    for (i = 0; i < hssConf.h_count; i++) {
        /* find myhost */
        if (strcmp(hssConf.hname, hssConf.h_info[i].host_name) == 0) {
            memcpy(&hssConf.myhost, &hssConf.h_info[i], sizeof(host_info_t));
        }

        if (strlen(hssConf.h_info[i].org_name) < 6) {
            if (strlen(hssConf.h_info[i].host_name) < 6) {
                xprint(FMS_HSS | FMS_INFO1, "%s\t%s\t\t%s\t\t%s\t\t\t%s\n",hssConf.h_info[i].org_type, hssConf.h_info[i].org_name,
                                     hssConf.h_info[i].host_type, hssConf.h_info[i].host_name, hssConf.h_info[i].host_ip);
            } else {
                xprint(FMS_HSS | FMS_INFO1, "%s\t%s\t\t%s\t\t%s\t\t%s\n", hssConf.h_info[i].org_type, hssConf.h_info[i].org_name,
                                     hssConf.h_info[i].host_type, hssConf.h_info[i].host_name, hssConf.h_info[i].host_ip);
            }
        } else {
            if (strlen(hssConf.h_info[i].host_name) < 15) {
                xprint(FMS_HSS | FMS_INFO1, "%s\t%s\t%s\t\t%s\t\t%s\n", hssConf.h_info[i].org_type, hssConf.h_info[i].org_name,
                                     hssConf.h_info[i].host_type, hssConf.h_info[i].host_name, hssConf.h_info[i].host_ip);
            } else {
                xprint(FMS_HSS | FMS_INFO1, "%s\t%s\t%s\t\t%s\t%s\n", hssConf.h_info[i].org_type, hssConf.h_info[i].org_name,
                                     hssConf.h_info[i].host_type, hssConf.h_info[i].host_name, hssConf.h_info[i].host_ip);
            }
        }
    }
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");
    xprint(FMS_HSS | FMS_INFO1, "My org type        : %s\n", hssConf.myhost.org_type);
    xprint(FMS_HSS | FMS_INFO1, "My org name        : %s\n", hssConf.myhost.org_name);
    xprint(FMS_HSS | FMS_INFO1, "My host name       : %s\n", hssConf.myhost.host_name);
    xprint(FMS_HSS | FMS_INFO1, "My host ip         : %s\n", hssConf.myhost.host_ip);
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");
    xprint(FMS_HSS | FMS_INFO1, "Peer Org count     : %u\n", hssConf.org_count);
    xprint(FMS_HSS | FMS_INFO1, "Peer per org count : %u\n", hssConf.org_peer_count);
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");
    xprint(FMS_HSS | FMS_INFO1, "BCManager ip       : %s\n", hssConf.bcmanager_ip);
    xprint(FMS_HSS | FMS_INFO1, "---------------------------------------------------------------------------\n");

    return 0;
}
