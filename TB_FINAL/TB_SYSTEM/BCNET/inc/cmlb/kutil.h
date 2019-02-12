

#ifndef __KUTIL_H__
#define __KUTIL_H__


/* FindDayOfWeek & GetDateDayWeek */
#define DAYWEEK_MONDAY      0x01
#define DAYWEEK_TUESDAY     0x02
#define DAYWEEK_WEDNESDAY   0x03
#define DAYWEEK_THURSDAY    0x04
#define DAYWEEK_FRIDAY      0x05
#define DAYWEEK_SATURDAY    0x06
#define DAYWEEK_SUNDAY      0x07


extern void nanoSleep(int msecs);
extern char *time_stamp(void);
extern int dupcheckbyname(char *name);
extern void setupSignal(void(*sigcb)(int signum));
extern void ignoreSignal(int signum);
extern int gmt2local(time_t t);
extern unsigned short FindDayOfWeek(void);    /* by jeon */
extern unsigned short GetDateDayWeek(int day, char *date);
extern char *directory_time(void);

extern char *GetTimeStamp(void);
extern char *get_dateTime(unsigned long unix_usec, char *date);
extern void init_insertTime(char *fivesec, char *fivemin);
extern int Get1MinTimeStamp(unsigned char *time);
extern unsigned int GetLocalIP(int fd);

extern unsigned long NBO_HTONLL(unsigned long x);
extern unsigned long NBO_NTOHLL(unsigned long x);

extern char *addr2str(unsigned int addr);
extern char *addr2ntoa(unsigned int addr);
extern char    *ipStr(unsigned int addr);

extern int CmpNetmaskIp(unsigned int addr1, unsigned int addr2, unsigned int netmask);
extern int proccheckbyname(char *name);

#endif  /* __KUTIL_H__ */
