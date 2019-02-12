

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <pthread.h>

#include "xprint.h"
#include "logdir.h"
#include "kutil.h"

static int comp_ymdh(void);
static int createDirectory(char *logPath);
static int createLog(char *logPath);
static int closeLog(void);

static char BaseLogPath[1024];
int o_year, o_month, o_date, o_hour;
pthread_t logTid;

/*
// ��/��/��/�� ���� �ʱ�ȭ�Ѵ�.
*/
static int init_ymdh(void)
{
    o_year  = 0;
    o_month = 0;
    o_date  = 0;
    o_hour  = 0;

    return(0);
}

/*
 ��/��/��/��/�� �����Ͽ� �ٸ� ���̸� -1�� �����ϰ� ������ 0�� �����Ѵ�.
*/
static int comp_ymdh(void)
{
    int n_year, n_month, n_date, n_hour;
    char mbuf[64];

    memset(mbuf, 0x00, sizeof(mbuf));

    sprintf(mbuf, "%s", time_stamp());
    sscanf(mbuf, "%d-%d-%d, %02d", &n_year, &n_month, &n_date, &n_hour);

    if (o_year != n_year) {
        fflush(stdout);
        return(0);
    }
    if (o_month != n_month) {
        fflush(stdout);
        return(0);
    }
    if (o_date != n_date) {
        fflush(stdout);
        return(0);
    }
    if (o_hour != n_hour) {
        fflush(stdout);
        return(0);
    }

    return(-1);
}

/*
// ��/��/��/��/�� �����Ͽ� ����ġ�ϴ� ���� �����丮�� �����Ѵ�.
*/
static int createDirectory(char *logPath)
{
    char mbuf[128];
    int n_year, n_month, n_date, n_hour;
    int ret;

    if (logPath == NULL) {
        fprintf(stderr, "logPath is null(%d at %s)\n", __LINE__, __func__);
        return(-1);
    }

    memset(mbuf, 0x00, sizeof(mbuf));

    sprintf(mbuf, "%s", time_stamp());
    sscanf(mbuf, "%d-%d-%d, %02d", &n_year, &n_month, &n_date, &n_hour);

    if (n_date != o_date) {
        if (n_year != o_year) {
            if (n_month != o_month) {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, n_year, n_month, n_date);
            } else {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, n_year, o_month, n_date);
            }
        } else {
            if (n_month != o_month) {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, o_year, n_month, n_date);
            } else {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, o_year, o_month, n_date);
            }
        }

        if ((ret = mkdir((const char *)logPath, S_IRWXU | S_IRWXG | S_IRWXO)) != 0) {
            switch(errno) {
                case EEXIST:
                     break;
                default:
                     fprintf(stderr, "%s mkdir error(%s : %d at %s)\n", logPath, strerror(errno), __LINE__, __FILE__);
                     return(-1);
                     break;
            }
        }
    } else {
        if (n_year != o_year) {
            if (n_month != o_month) {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, n_year, n_month, o_date);
            } else {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, n_year, o_month, o_date);
            }

            if ((ret = mkdir((const char *)logPath, S_IRWXU | S_IRWXG | S_IRWXO)) != 0) {
                switch(errno) {
                    case EEXIST:
                         break;
                    default:
                         fprintf(stderr, "%s mkdir error(%s : %d at %s)\n", logPath, strerror(errno), __LINE__, __FILE__);
                         return(-1);
                         break;
                }
            }
        } else {
            if (n_month != o_month) {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, o_year, n_month, o_date);
            } else {
                sprintf(logPath, "%s/%d.%02d.%02d", BaseLogPath, o_year, o_month, o_date);
            }

            if ((ret = mkdir((const char *)logPath, S_IRWXU | S_IRWXG | S_IRWXO)) != 0) {
                switch(errno) {
                    case EEXIST:
                         break;
                    default:
                         fprintf(stderr, "%s mkdir error(%s : %d at %s)\n", logPath, strerror(errno), __LINE__, __FILE__);
                         return(-1);
                         break;
                }
            }
        }
    }

    o_year  = n_year;
    o_month = n_month;
    o_date  = n_date;
    o_hour  = n_hour;

    return(0);
}

/*
 compare log
*/
static int compareLog(void)
{
    int result;
    char logPath[1024];

    result = comp_ymdh();
    if (result == 0) {
        closeLog();
        memset(logPath, 0x00, sizeof(logPath));
        if (createDirectory(&logPath[0]) == 0) {
            createLog(&logPath[0]);
        }
    }

    return(0);
}

/*
 create log
*/
static int createLog(char *logPath)
{
    int year, month, date, hour;
    char buf[128], logfile[1024];

    memset(buf, 0x00, sizeof(buf));
    memset(logfile, 0x00, sizeof(logfile));

    sprintf(buf, "%s", time_stamp());
    sscanf(buf, "%d-%d-%d, %02d", &year, &month, &date, &hour);
    sprintf(logfile, "%s/%02d.LOG", logPath, hour);

    pthread_mutex_lock(&print_mutex);

    logfp = fopen(logfile, "a+");
    if (!logfp) {
        fprintf(stdout, "cannot open %s(%d at %s)\n", logfile, __LINE__, __func__);
        fflush(stdout);
        pthread_mutex_unlock(&print_mutex);
        return(-1);
    }

    pthread_mutex_unlock(&print_mutex);

    return(0);
}

/*
 close log
*/
static int closeLog(void)
{
    pthread_mutex_lock(&print_mutex);

    if (logfp != NULL) {
        fflush(logfp);
        fclose(logfp);
        logfp = NULL;
    }

    pthread_mutex_unlock(&print_mutex);

    return(0);
}

/*
 log compare task
*/
static void *logTask(void *argc)
{
    for(;;) {
        compareLog();
        sleep(1);
    }

    return NULL;
}

/*
 initialize log
*/
int initializeLog(char *logPath)
{
    int ret;

    if (logPath == NULL) {
        fprintf(stderr, "logPath is null(%d at %s)\n", __LINE__, __func__);
        return(-1);
    }

    memset(BaseLogPath, 0x00, sizeof(BaseLogPath));
    sprintf(BaseLogPath, "%s", logPath);

    if ((ret = mkdir((const char *)BaseLogPath, S_IRWXU | S_IRWXG | S_IRWXO)) != 0) {
        switch(errno) {
            case EEXIST:
                 break;
            default:
                 fprintf(stderr, "%s mkdir error(%s : %d at %s)\n", BaseLogPath, strerror(errno), __LINE__, __FILE__);
                 return(-1);
                 break;
        }
    }

    init_ymdh();

    if (pthread_create(&logTid, NULL, logTask, NULL) != 0) {
        fprintf(stderr, "logTask create error(%s : %d at %s)\n", strerror(errno), __LINE__, __func__);
        return(-1);
    }

    usleep(90000);

    return(0);
}

/*
// terminate log
*/
void terminateLog(void)
{
    pthread_cancel(logTid);
}
