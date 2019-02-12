/*
 -------------------------------------------------------------------------
  File Name       : dbmb_main.h
  Author          : Hyeong-Ik Jeon
  Copyright       : Copyright (C) 2014 KBell Inc.
  Descriptions    : DBMB(DataBase Manager Block) manager(init, event)
  History         : 14/07/15       Initial Coded
 -------------------------------------------------------------------------
*/
#ifndef __DBMB_MAIN_H__
#define __DBMB_MAIN_H__

#define OPEN_DB            0x01

#define GET_ADDR           0x01
#define GET_USER           0x02
#define GET_PASSWORD       0x03
#define GET_NAME           0x04
#define GET_PORT           0x05

/* ==============================================================================================================*/
#define MAX_QUERY_LENGTH   (1024*4)
#define MAX_DB_TABLE       64
#define MAX_DBM_INFO       16

#if 0
#define DELETE_TABLE              "DELETE FROM %s"
#define SELECT_TABLE              "SELECT * FROM %s"
#define SELECT_ROW_COUNT          "SELECT COUNT(*) FROM %s"
#define DROP_TABLE                "DROP TABLE %s"
#define TRUNCATE_TABLE            "TRUNCATE TABLE %s"
#define CREATE_YEAR_TABLE         "CREATE TABLE %s_%s LIKE %s"
#define CREATE_MONTH_TABLE        "CREATE TABLE %s_%s LIKE %s"
#define CREATE_DAY_TABLE          "CREATE TABLE %s_%03d_%s LIKE %s"
#define CREATE_DAY_NOLINE_TABLE   "CREATE TABLE %s_%s LIKE %s"
#define CHECK_LINE_MONTH_TABLE    "SHOW TABLES LIKE '%s_%03u_%s'"
#define CHECK_MONTH_TABLE         "SHOW TABLES LIKE '%s_%s'"
#define CHECK_YEAR_TABLE          "SHOW TABLES LIKE '%s_%s'"
#define CHECK_DAY_TABLE           "SHOW TABLES LIKE '%s_%03d_%s'"
#define CHECK_DAY_NOLINE_TABLE    "SHOW TABLES LIKE '%s_%s'"
#define SELECT_CHECK_TIMES_HOUR   "SELECT TIME_TO_SEC(DATE_SUB(now(), interval hour(now()) hour))"
#define CHECK_DROP_TABLE          "SHOW TABLES LIKE '%s_%s'"
#define CHECK_DROP_LINEID_TABLE   "SHOW TABLES LIKE '%s_%%_%s'"
#endif

#define SELECT_CHECK_TIMES        "SELECT TIME_TO_SEC(now())"

typedef struct {
    char           Address[128];
    char           UserID[128];
    char           Password[128];
    char           DBName[128];
    unsigned int   Port;
} DBParm_t;


extern MYSQL *handler1;
extern MYSQL *handler2;
extern MYSQL *DBManager;
extern MYSQL *orchehandler;


extern int ExecQuery1(char *query, int len);
extern int ExecQuery2(char *query, int len);
extern int ExecDBM(char *query, int len);
extern int CommitID(void);
extern int CommitID2(void);
extern int CommitDBM(void);

extern MYSQL *OpenDBHandler(void);
extern void CloseDBHandler(MYSQL *h);
extern int ExecQuery(MYSQL *h, char *query, int len);
extern int ExecCommit(MYSQL *h);

#endif /* __DBMB_MAIN_H__ */
