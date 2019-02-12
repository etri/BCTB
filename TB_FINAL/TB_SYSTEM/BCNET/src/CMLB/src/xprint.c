

#include <stdio.h>
#include <stdarg.h>
#include <string.h>
#include <strings.h>
#include <ctype.h>
#include <stdlib.h>
#include <sys/time.h>
#include <unistd.h>
#include <pthread.h>
#include <syslog.h>
#include <errno.h>

#include "xprint.h"

/* global variables */
unsigned int    glTaskTraceMask = FMS_CLB | FMS_DBM | FMS_HSS ;
unsigned char   gbDebugLevel    = FMS_FATAL;
unsigned char   gbPrintTime     = TIME_ON;
unsigned int    gbTrace         = DBG_TERMINAL;
unsigned int    gMask           = 0;
char            gIdent[32];
pthread_mutex_t print_mutex;
FILE            *logfp          = NULL;

/*
 initialize the xprint
*/
int init_xprint(unsigned int mask, unsigned char level, unsigned int trace)
{
    if (mask == 0) {
        glTaskTraceMask = FMS_CLB | FMS_DBM | FMS_HSS ;
    } else {
        gMask = glTaskTraceMask = mask;
        if (mask & FMS_CLB) {
            sprintf(gIdent, "%s", "[ CLB ] ");
            gMask = FMS_CLB;
        } else if (mask & FMS_DBM) {
            sprintf(gIdent, "%s", "[ DBM ] ");
            gMask = FMS_DBM;
        } else if (mask & FMS_HSS) {
            sprintf(gIdent, "%s", "[ HSS ] ");
            gMask = FMS_HSS;
        } else {
            sprintf(gIdent, "%s", "[ UKN ] ");
        }
    }

    if (level == 0) {
        gbDebugLevel = FMS_FATAL;
    } else {
        gbDebugLevel = level;
    }

    if (trace == 0) {
        gbTrace = DBG_TERMINAL;
    } else {
        gbTrace = trace;
        if (gbTrace & DBG_SYSLOG) {
            openlog(gIdent, LOG_PID, LOG_USER);
        }
    }

    if (pthread_mutex_init(&print_mutex, NULL) != 0) {
        fprintf(stdout, "pthread_mutex_init error(%s) at %s\n", strerror(errno), __func__);
        return(-1);
    }

    return(0);
}

/*
 close the xprint
*/
void close_xprint(void)
{
    if (gbTrace & DBG_SYSLOG) {
        closelog();
    }

    if (logfp != NULL) {
        fflush(logfp);
        fclose(logfp);
        logfp = NULL;
    }

    pthread_mutex_destroy(&print_mutex);
}

/*
// The messages print at terminal or log file.
*/
void xprint(unsigned int msgLevel, const char *fmt,...)
{
    unsigned char bPrintTime;

    bPrintTime = gbPrintTime;

    if ((gbDebugLevel >= (msgLevel & TRC_LEVEL_MASK)) && (TRACE_MASK & msgLevel & glTaskTraceMask)) {
        int rmutex;
        if ((rmutex = pthread_mutex_lock(&print_mutex)) != 0) {
            fprintf(stdout, "[%s : %d] : pthread_mutex_lock error(%d : %s)\n", __func__, __LINE__, rmutex, strerror(errno));
        }

        {
            char           dbgstr[DBG_MAX_MSG_LEN], timestr[512], stackstr[32];
            static char    lastc = '\n';
            static char    lastc_file = '\n';
            va_list        arg;
            int            len=0;
            struct tm      *ctime;
            struct timeval tv;

            dbgstr[0] = '\0';

            va_start(arg, fmt);
            vsprintf(dbgstr, fmt, arg);

            gettimeofday(&tv, NULL);
            ctime = localtime((time_t *)&(tv.tv_sec));

            /* Row Print */
            if (!(msgLevel & ROW_PRINT)) {
                if (bPrintTime == TIME_ON && lastc == '\n') {
                    if (ctime != NULL) {
                        if (gbTrace & DBG_TERMINAL) {
                            printf("[%4d/%02d/%02d %02d:%02d:%02d:%03d] ", ctime->tm_year + 1900,
                                    ctime->tm_mon + 1, ctime->tm_mday, ctime->tm_hour, ctime->tm_min, ctime->tm_sec,
                                    (int)(tv.tv_usec / 1000));
                            fflush(stdout);
                        }

                        if (gbTrace & DBG_THIS) {
                            timestr[0] = '\0';
                            sprintf(timestr, "[%4d/%02d/%02d %02d:%02d:%02d:%03d] ",
                                    ctime->tm_year + 1900, ctime->tm_mon + 1,
                                    ctime->tm_mday, ctime->tm_hour,
                                    ctime->tm_min, ctime->tm_sec, (int)(tv.tv_usec / 1000));
                        }
                    } else {
                        fprintf(stdout, "ctime is null(%d at %s)\n", __LINE__, __FILE__);
                    }
                }
            }

            if (((gbTrace & DBG_TERMINAL) && lastc == '\n') && !(msgLevel & ROW_PRINT)) {
                if (msgLevel & FMS_CLB)                     printf("[ CLB ] ");
                if (msgLevel & FMS_DBM)                     printf("[ DBM ] ");
                if (msgLevel & FMS_HSS)                     printf("[ HSS ] ");

                if ((msgLevel & 0x0000000F) == FMS_LOOKS)   printf("[ LOOKS ] ");
                if ((msgLevel & 0x0000000F) == FMS_FATAL)   printf("[ FATAL ] ");
                if ((msgLevel & 0x0000000F) == FMS_BUG)     printf("[  BUG  ] ");
                if ((msgLevel & 0x0000000F) == FMS_ERROR)   printf("[ ERROR ] ");
                if ((msgLevel & 0x0000000F) == FMS_WARNING) printf("[WARNING] ");
                if ((msgLevel & 0x0000000F) == FMS_INFO1)   printf("[ INFO1 ] ");
                if ((msgLevel & 0x0000000F) == FMS_INFO2)   printf("[ INFO2 ] ");
                if ((msgLevel & 0x0000000F) == FMS_INFO3)   printf("[ INFO3 ] ");
                if ((msgLevel & 0x0000000F) == FMS_INFO4)   printf("[ INFO4 ] ");
                if ((msgLevel & 0x0000000F) == FMS_INFO5)   printf("[ INFO5 ] ");

                fflush(stdout);
            }

            if (gbTrace & DBG_TERMINAL) {
                len = printf("%s", dbgstr);
                fflush(stdout);
                if (len) {
                    lastc = dbgstr[len - 1];
                }
            }

            if (gbTrace & DBG_FILE) {
                if (lastc_file == '\n') {
                    if (logfp != NULL) {
                        fprintf(logfp, "%s", timestr);
                        if (!(msgLevel & ROW_PRINT)) {
                            if (msgLevel & FMS_CLB)                         sprintf(stackstr, "%s", "[ CLB ] ");
                            if (msgLevel & FMS_DBM)                         sprintf(stackstr, "%s", "[ DBM ] ");
                            if (msgLevel & FMS_HSS)                         sprintf(stackstr, "%s", "[ HSS ] ");

                            fprintf(logfp, "%s", stackstr);
                            fflush(logfp);

                            if ((msgLevel & 0x0000000F) == FMS_LOOKS)       sprintf(stackstr, "%s", "[ LOOKS ] ");
                            if ((msgLevel & 0x0000000F) == FMS_FATAL)       sprintf(stackstr, "%s", "[ FATAL ] ");
                            if ((msgLevel & 0x0000000F) == FMS_BUG)         sprintf(stackstr, "%s", "[  BUG  ] ");
                            if ((msgLevel & 0x0000000F) == FMS_ERROR)       sprintf(stackstr, "%s", "[ ERROR ] ");
                            if ((msgLevel & 0x0000000F) == FMS_WARNING)     sprintf(stackstr, "%s", "[WARNING] ");
                            if ((msgLevel & 0x0000000F) == FMS_INFO1)       sprintf(stackstr, "%s", "[ INFO1 ] ");
                            if ((msgLevel & 0x0000000F) == FMS_INFO2)       sprintf(stackstr, "%s", "[ INFO2 ] ");
                            if ((msgLevel & 0x0000000F) == FMS_INFO3)       sprintf(stackstr, "%s", "[ INFO3 ] ");
                            if ((msgLevel & 0x0000000F) == FMS_INFO4)       sprintf(stackstr, "%s", "[ INFO4 ] ");
                            if ((msgLevel & 0x0000000F) == FMS_INFO5)       sprintf(stackstr, "%s", "[ INFO5 ] ");
                            fprintf(logfp, "%s", stackstr);
                            fflush(logfp);
                        }
                    }
                }

                if (gbTrace & DBG_SYSLOG) {
                    syslog(LOG_INFO, "%s", dbgstr);
                }

                if (logfp != NULL) {
                    len = fprintf(logfp, "%s", dbgstr);
                    fflush(logfp);
                    if (len) {
                        lastc_file = dbgstr[len - 1];
                    }
                }
            }

            if (gbTrace & DBG_THIS) {
                len = strlen(dbgstr);
                if (len) {
                    lastc = dbgstr[len - 1];
                }
            }
        }

        if (rmutex == 0) {
            if (pthread_mutex_unlock(&print_mutex) != 0) {
                fprintf(stdout, "[%s : %d] : pthread_mutex_unlock error(%s)\n", __func__, __LINE__, strerror(errno));
                fflush(stdout);
            }
        }
    }
}

/*
// display hex code
*/
void xdump(unsigned int msgLevel, unsigned char *Buf, int ByteCnt, char *info)
{
    if ((gbDebugLevel >= (msgLevel & TRC_LEVEL_MASK)) && (TRACE_MASK & msgLevel & glTaskTraceMask)) {
        char title[62];
        char tempTitle[]={"========================================================================\n"};
        char Ascii[80];
        int  noOfItem = 16;
        int  Li, LMax, ToDo, Bi, Ai;
        unsigned char B;

        if (ByteCnt == 0) {
            xprint(msgLevel, "[WARNING] Length is zero, this parameter may be OPTION PARAMETER\n");
            return;
        } else if (ByteCnt < 0) {
            xprint(msgLevel, "Length is less than zero\n");
            return;
        } else if (ByteCnt >= DBG_MAX_MSG_LEN) {                  /* DBG_MAX_MSG_LEN = 2000 */
            xprint(msgLevel, "Length exceed DBG_MAX_MSG_LEN\n");
            return;
        }

        sprintf(title, " %s[%s] [LENGTH : %d] ", "INFO : ", info, ByteCnt);
        bcopy(title, tempTitle + ((strlen(tempTitle)/2) - (strlen(title)/2)), strlen(title));
        xprint(msgLevel, tempTitle);

        /* Dump to std out in hex followed by ascii */
        for(ToDo = ByteCnt, Bi = 0; ToDo > 0; ToDo -= LMax) {
            LMax = (ToDo > noOfItem) ? noOfItem : ToDo; /* max bytes this line */
            xprint(msgLevel, "%04X| ", Bi);
            Ai = 0;
            for(Li=0; Li<LMax; Li++) {                  /* each byte this line */
                 B = *Buf++;                            /* next byte to show */
                 xprint(msgLevel, "%02X ", B);          /* as Hex */
                 if (!isprint(B)) {                     /* printable ascii? */
                     B = '.';                           /* no, use this */
                 }
                 Ascii[Ai++] = B;                       /* into ascii portiob */
            }

            /* for Li */
            for(Li = LMax; Li < noOfItem; Li++) {       /* pad short ln if necessary */
                 xprint(msgLevel, "   ");               /* so asci lines up(%02X) */
            }
            Ascii[Ai] = 0;                              /* terminate the ascii */
            xprint(msgLevel, "  ");
            xprint(msgLevel, "%s\n", Ascii);            /* show the ascii */
            Bi += LMax;
        }   /* for ToDo */

        /* # length = 62 */
        xprint(msgLevel, "========================================================================\n");
    }
}
