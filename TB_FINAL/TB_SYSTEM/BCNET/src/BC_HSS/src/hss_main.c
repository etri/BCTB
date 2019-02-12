

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


static void hss_shutdown(void);
static void hss_signal_handler(int signum);
static void InitDebug(char *path, unsigned int mask, unsigned char level, unsigned int trace);

static void hss_shutdown(void)
{
    static unsigned char once = 0;

    if (once) {
        return;
    } else {
        once = 1;
    }

    CloseDatabase();

    FreeVariable();
    terminateLog();
    close_xprint();

    exit(0);
}

/* Signal handler */
static void hss_signal_handler(int signum)
{
    xprint(FMS_HSS | FMS_LOOKS, "Received Signal(%d)\n", signum);

    hss_shutdown();
}

static void InitDebug(char *path, unsigned int mask, unsigned char level, unsigned int trace)
{
    char LogPath[512];
    unsigned int  DbgMask  = mask;
    unsigned int  DbgTrace = trace;
    unsigned char DbgLevel = level;

    if (init_xprint(DbgMask, DbgLevel, DbgTrace) < 0) {
        fprintf(stderr, "init_xprint error, So exit\n");
        exit(0);
    }

    /* 로그 디렉토리 및 로그파일의 초기화 */
    memset(&LogPath, 0x00, sizeof(LogPath));
    sprintf(LogPath, "%s/%s", path, "log/HSS");

    if (initializeLog(LogPath) < 0) {
        xprint(FMS_HSS | FMS_FATAL, "initializeLog error, So exit\n");
        close_xprint();
        exit(0);
    }
}

int main(int argc, char *argv[])
{
    char *pEnv;

    /*--------------------------------------------------------------------------
      프로세스 중복 체크
      ---------------------------------------------------------------------------*/
    if (dupcheckbyname(argv[0]) != 0) {
        fprintf(stderr, "oops!!! duplicated process(%s). So, exit\n", argv[0]);
        exit(0);
    }

    /*--------------------------------------------------------------------------
      시그널 등록
      ---------------------------------------------------------------------------*/
    setupSignal(hss_signal_handler);

    /*--------------------------------------------------------------------------
      Init Environment
      ---------------------------------------------------------------------------*/
    /* get environment variable */
    if ((pEnv = getenv(BCNET_ENV_NAME)) == NULL){
        fprintf(stderr, "'%s' environment variable not found(%s)\n", BCNET_ENV_NAME, strerror(errno));
        exit(0);
    }

    /*--------------------------------------------------------------------------
      디버그 프린트 및 로그 초기화
      ---------------------------------------------------------------------------*/
    InitDebug(pEnv, FMS_HSS | FMS_DBM | FMS_CLB , FMS_INFO1, DBG_TERMINAL | DBG_FILE | DBG_THIS);

    xprint(FMS_HSS | FMS_LOOKS, "\n");
    xprint(FMS_HSS | FMS_LOOKS, "=========================================================\n");
    xprint(FMS_HSS | FMS_LOOKS, " HSS(bcnetHSS) : START\n");
    xprint(FMS_HSS | FMS_LOOKS, "=========================================================\n");
    xprint(FMS_HSS | FMS_LOOKS, "\n");

    /*--------------------------------------------------------------------------
      Read configration & Init Global Variable
      ---------------------------------------------------------------------------*/
    if (InitVariable(pEnv) < 0) {
        xprint(FMS_HSS | FMS_FATAL, "Initialize failed, So exit\n");
        terminateLog();
        close_xprint();
        exit(0);
    }

    xprint(FMS_HSS | FMS_LOOKS, "Hyperledger Fabric Setup START \n");

    /* commom :: /etc/hosts 수정 */
    SethostsFile();

    /* bcmanager */
    if (strcmp(hssConf.myhost.org_type, "manager") == 0) {

        SendBcnetCfgFile();
        ProcessBcManager();

    /* orderer */
    } else if (strcmp(hssConf.myhost.org_type, "orderer") == 0) {
        ProcessOrderer();

    /* peer */
    } else if (strcmp(hssConf.myhost.org_type, "peer") == 0) {
        ProcessPeer();

    /* kafka-zookeeper */
    } else if (strcmp(hssConf.myhost.org_type, "kafka") == 0) {
        ProcessKafkaZookeeper();

    } else {
        xprint(FMS_HSS | FMS_WARNING, "Not configuration [org type = %s] \n", hssConf.myhost.org_type);
    }


    xprint(FMS_HSS | FMS_LOOKS, "Hyperledger Fabric Setup END \n\n");
    hss_shutdown();

    return(0);
}
