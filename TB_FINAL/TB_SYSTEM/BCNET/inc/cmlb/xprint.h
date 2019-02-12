
#ifndef __XPRINT_H__
#define __XPRINT_H__

#include <stdio.h>
#include <stdarg.h>

#define DBG_TERMINAL        ((unsigned int) 0x00000001)    /* 1 = 0001 */
#define DBG_FILE            ((unsigned int) 0x00000002)    /* 2 = 0010 */
#define DBG_THIS            ((unsigned int) 0x00000004)    /* 4 = 0100 */
#define DBG_SYSLOG          ((unsigned int) 0x00000008)    /* 8 = 1000 */
#if 0
#define DBG_MAX_MSG_LEN     4096
#else
#define DBG_MAX_MSG_LEN     65536
#endif

#define ROW_PRINT           ((unsigned int) 0x80000000)
#define TRACE_MASK          ((unsigned int) 0x7FFFFFF0)
#define TRC_LEVEL_MASK      ((unsigned int) 0x0000000F)
#define MAX_TRC_LEVEL       ((unsigned int) 0x7)

#define TIME_ON             1
#define TIME_OFF            0

#define FMS_LOOKS           ((unsigned int) 1)        /* Important Information */
#define FMS_FATAL           ((unsigned int) 2)        /* Fatal */
#define FMS_BUG             ((unsigned int) 3)        /* Bug */
#define FMS_ERROR           ((unsigned int) 4)        /* Error */
#define FMS_WARNING         ((unsigned int) 5)        /* Warning */
#define FMS_INFO1           ((unsigned int) 6)        /* Information Level 1 */
#define FMS_INFO2           ((unsigned int) 7)        /* Information Level 2 */
#define FMS_INFO3           ((unsigned int) 8)        /* Information Level 3 */
#define FMS_INFO4           ((unsigned int) 9)        /* Information Level 4 */
#define FMS_INFO5           ((unsigned int) 10)       /* Information Level 5 */

/* Modules */
#define FMS_CLB             ((unsigned int) 0x00000010)    /* Common Library */
#define FMS_DBM             ((unsigned int) 0x00000020)    /* DB Libary      */
#define FMS_HSS             ((unsigned int) 0x00000040)    /* H Setup Server */

#define MAX_MODULE_NUM      6

/* Color print */
#define COLOR_RED           "\033[1;31m"
#define COLOR_GREEN         "\033[1;32m"
#define COLOR_YELLOW        "\033[1;33m"
#define COLOR_BLUE          "\033[1;34m"
#define COLOR_MAGENTA       "\033[1;35m"
#define COLOR_CYAN          "\033[1;36m"
#define COLOR_WHITE         "\033[1;37m"
#define COLOR_BLACK         "\033[1;38m"
#define COLOR_END           "\033[0m"

#ifdef __cplusplus
extern "C" void xprint(unsigned int msgLevel, const char *fmt,...);
#else
extern void xprint(unsigned int msgLevel, const char *fmt,...);
#endif

extern int  init_xprint(unsigned int mask, unsigned char level, unsigned int trace);
extern void close_xprint(void);
extern void xdump(unsigned int msgLevel, unsigned char *Buf, int ByteCnt, char *info);

extern unsigned int    gMask;
extern FILE            *logfp;
extern pthread_mutex_t print_mutex;

#endif    /* __XPRINT_H__ */
