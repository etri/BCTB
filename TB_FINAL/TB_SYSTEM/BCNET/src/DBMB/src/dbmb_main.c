/*
 -------------------------------------------------------------------------
  File Name       : dbmb_main.c
  Author          : Hyeong-Ik Jeon
  Copyright       : Copyright (C) 2011 KBell Inc.
  Descriptions    : DBMB(DataBase Manager Block) manager(init, event)
  History         : 14/07/15       Initial Coded
 -------------------------------------------------------------------------
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <mysql.h>
#include <errmsg.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

#include "bcnet.h"

#include "xprint.h"
#include "kutil.h"

#include "dbmb.h"
#include "dbmb_main.h"


MYSQL *handler1     = NULL;
MYSQL *handler2     = NULL;
MYSQL *DBManager    = NULL; 

DBParm_t DBParm;


static void CloseDatabase1(void)
{
    if (handler1 != NULL) {
        mysql_close(handler1);
        handler1 = NULL;
    }
}

static void CloseDatabase2(void)
{
    if (handler2 != NULL) {
        mysql_close(handler2);
        handler2 = NULL;
    }
}

/* 20090709, by jeon */
static void CloseDBM(void)
{
    if (DBManager != NULL) {
        mysql_close(DBManager);
        DBManager = NULL;
    }
}

static int OpenDatabase(void)
{
    handler1 = mysql_init(handler1);
    if(!mysql_real_connect(handler1,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database: %s\n",
                mysql_error(handler1));
        CloseDatabase1();

        return -1;
    }

    handler2 = mysql_init(handler2);
    if(!mysql_real_connect(handler2,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database: %s\n",
                mysql_error(handler2));
        CloseDatabase2();

        return -1;
    }    

    DBManager = mysql_init(DBManager);
    if(!mysql_real_connect(DBManager,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database DBManager: %s\n",
                mysql_error(DBManager));
        CloseDBM();

        return -1;
    }

    return 0;
}

static int OpenDatabase1(void)
{
    handler1 = mysql_init(handler1);
    if(!mysql_real_connect(handler1,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database: %s\n",
                mysql_error(handler1));
        CloseDatabase1();

        return -1;
    }

    return 0;
}

static int OpenDatabase2(void)
{
    handler2 = mysql_init(handler2);
    if(!mysql_real_connect(handler2,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database: %s\n",
                mysql_error(handler2));
        CloseDatabase2();

        return -1;
    }

    return 0;
}

static int OpenDBM(void)
{
    DBManager = mysql_init(DBManager);
    if(!mysql_real_connect(DBManager,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)){
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database DBManager: %s\n",
                mysql_error(DBManager));
        CloseDBM();

        return -1;
    }

    return 0;
}

int ExecQuery1(char *query, int len)
{
    int retval;

#if 0
    xprint(FMS_DBM | FMS_INFO5, "%s(%d) : h1 query (%s)\n", __func__, __LINE__, query);
#endif
    if (handler1 == NULL) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : handler1 is NULL\n", __func__, __LINE__);
        if (OpenDatabase1() < 0) {
            if (OpenDatabase1() < 0) {
                /* NEXT_FIX, by icecom 090513 DB Handler */
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler1 Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler1 Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    retval = mysql_real_query(handler1, query, len);
    if (retval) {
        retval = mysql_errno(handler1);
        xprint(FMS_DBM | FMS_ERROR, "%s(%d : %d) : %s\n", __func__, __LINE__, retval, mysql_error(handler1));

        /* HSKANG */
        if (retval == CR_SERVER_GONE_ERROR || retval == CR_SERVER_LOST || retval == CR_UNKNOWN_ERROR || retval == CR_COMMANDS_OUT_OF_SYNC) {
            CloseDatabase1();
            if (OpenDatabase1() < 0) {
                /* NEXT_FIX, DB Handler*/
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler1 Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler1 Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : query = %s\n", __func__, __LINE__, query);
    }

    return retval;
}

int ExecQuery2(char *query, int len)
{
    int retval;

#if 0
    xprint(FMS_DBM | FMS_INFO5, "%s(%d) : h2 query (%s)\n", __func__, __LINE__, query);
#endif
    if (handler2 == NULL) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : handler2 is NULLn", __func__, __LINE__);
        if (OpenDatabase2() < 0) {
            if (OpenDatabase2() < 0) {
                /* NEXT_FIX, DB Handler */
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler2 Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler2 Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    retval = mysql_real_query(handler2, query, len);
    if (retval) {
        retval = mysql_errno(handler2);
        xprint(FMS_DBM | FMS_ERROR, "%s(%d : %d) : %s\n", __func__, __LINE__, retval, mysql_error(handler2));

        /* HSKANG */
        if (retval == CR_SERVER_GONE_ERROR || retval == CR_SERVER_LOST || retval == CR_UNKNOWN_ERROR || retval == CR_COMMANDS_OUT_OF_SYNC) {
            CloseDatabase2();
            if (OpenDatabase2() < 0) {
                /* NEXT_FIX, DB Handler */
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler2 Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler2 Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : query = %s\n", __func__, __LINE__, query);
    }

    return retval;
}

int ExecDBM(char *query, int len)
{
    int retval;

#if 0
    xprint(FMS_DBM | FMS_INFO5, "%s(%d) : dbm query (%s)\n", __func__, __LINE__, query);
#endif
    if (DBManager == NULL) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : DBManager is NULLn", __func__, __LINE__);
        if (OpenDBM() < 0) {
            xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB DBManager Open failed. Restart Process...\n", __func__, __LINE__);
            return(-1);
        } else {
            xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB DBManager Re-Open Success.\n", __func__, __LINE__);
        }
    }

    retval = mysql_real_query(DBManager, query, len);
    if (retval) {
        retval = mysql_errno(DBManager);
        xprint(FMS_DBM | FMS_ERROR, "%s(%d : %d) : %s\n", __func__, __LINE__, retval, mysql_error(DBManager));

        /* HSKANG */
        if (retval == CR_SERVER_GONE_ERROR || retval == CR_SERVER_LOST || retval == CR_UNKNOWN_ERROR || retval == CR_COMMANDS_OUT_OF_SYNC) {
            CloseDBM();
            if (OpenDBM() < 0) {
                /* NEXT_FIX, by icecom 090513 DB Handler */
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB DBManager Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB DBManager Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : query = %s\n", __func__, __LINE__, query);
    }

    return retval;
}

int CommitID(void)
{
    int retval;
    char Query[16];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, "commit");
    retval = ExecQuery1(Query, strlen(Query));

    return retval;
}

int CommitID2(void)
{
    int retval;
    char Query[16];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, "commit");
    retval = ExecQuery2(Query, strlen(Query));

    return retval;
}

int CommitDBM(void)
{
    int retval;
    char Query[16];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, "commit");
    retval = ExecDBM(Query, strlen(Query));

    return retval;
}

void CloseDatabase(void)
{
    if (handler1 != NULL) {
        mysql_close(handler1);
        handler1 = NULL;
    }

    if (handler2 != NULL) {
        mysql_close(handler2);
        handler2 = NULL;
    }

    if (DBManager != NULL) {
        mysql_close(DBManager);
        DBManager = NULL;
    }
}

/* DB connection and table create, by jeon */
int DatabaseInit(char *addr, char *userid, char *pass, char *name, unsigned int port)
{
    int retval;

    memset((char *)&DBParm, 0x00, sizeof(DBParm_t));
    sprintf(DBParm.Address,  "%s", addr);
    sprintf(DBParm.UserID,   "%s", userid);
    sprintf(DBParm.Password, "%s", pass);
    sprintf(DBParm.DBName,   "%s", name);
    DBParm.Port = port;

    retval = OpenDatabase();

    xprint(FMS_DBM | FMS_LOOKS, "## ================================================================= ##\n");
    xprint(FMS_DBM | FMS_LOOKS, "      DB Address    : %s\n", DBParm.Address);
    xprint(FMS_DBM | FMS_LOOKS, "      DB UserID     : %s\n", DBParm.UserID);
    xprint(FMS_DBM | FMS_LOOKS, "      DB Schema     : %s\n", DBParm.DBName);
    xprint(FMS_DBM | FMS_LOOKS, "      DB Port       : %u\n", DBParm.Port);
    xprint(FMS_DBM | FMS_LOOKS, "## ================================================================= ##\n\n");

    return retval;
}

MYSQL *OpenDBHandler(void)
{
    MYSQL *h = NULL;

    h = mysql_init(h);
    if(!mysql_real_connect(h,
                DBParm.Address,
                DBParm.UserID,
                DBParm.Password,
                DBParm.DBName,
                DBParm.Port,
                NULL,
                0)) {
        xprint(FMS_DBM | FMS_ERROR, "Failed to connect to database DBHandler: %s\n", mysql_error(h));
        CloseDBHandler(h);

        return NULL;
    }

    return h;
}

void CloseDBHandler(MYSQL *h)
{
    if (h != NULL) {
        mysql_close(h);
        h = NULL;
    }
}

int ExecQuery(MYSQL *h, char *query, int len)
{
    int retval;

    if (h == NULL) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : handler is NULL\n", __func__, __LINE__);
        h = OpenDBHandler();
        if (h == NULL) {
            xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler Open failed. Restart Process...\n", __func__, __LINE__);
            return(-1);
        } else {
            xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler Re-Open Success.\n", __func__, __LINE__);
        }
    }

    retval = mysql_real_query(h, query, len);
    if (retval) {
        retval = mysql_errno(h);
        xprint(FMS_DBM | FMS_ERROR, "%s(%d : %d) : %s\n", __func__, __LINE__, retval, mysql_error(h));

        /* HSKANG */
        if (retval == CR_SERVER_GONE_ERROR || retval == CR_SERVER_LOST || retval == CR_UNKNOWN_ERROR || retval == CR_COMMANDS_OUT_OF_SYNC) {
            CloseDBHandler(h);
            h = OpenDBHandler();
            if (h == NULL) {
                xprint(FMS_DBM | FMS_FATAL, "%s(%d) : DB handler Open failed. Restart Process...\n", __func__, __LINE__);
            } else {
                xprint(FMS_DBM | FMS_LOOKS, "%s(%d) : DB handler Re-Open Success.\n", __func__, __LINE__);
            }
        }
    }

    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : query = %s\n", __func__, __LINE__, query);
    }

    return retval;
}

int ExecCommit(MYSQL *h)
{
    int retval;
    char Query[16];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, "commit");
    retval = ExecQuery(h, Query, strlen(Query));

    return retval;
}



