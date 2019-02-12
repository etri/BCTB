/*
 -------------------------------------------------------------------------
  File Name       : dbmb_hss.c
  Author          : Hyeong-Ik Jeon
  Copyright       : Copyright (C) 2018 KBell Inc.
  Descriptions    : DBMB(DataBase Manager Block) hss
  History         : 18/09/14       Initial Coded
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
#include "dbmb_hss.h"


int GetCountHostDB(unsigned int profile_id)
{
    int retval, count = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_COUNT_HOST, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    sscanf(row[0], "%d", &count);

    mysql_free_result(res);
    return count;
}

int GetHlfhostInfoDB(unsigned int profile_id, host_info_t *host)
{
    int retval, count = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_HOST_INFO, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_store_result(handler1);
    for (;;) {

        if (count >= MAX_SYSTEM_NUM) {
            xprint(FMS_DBM | FMS_WARNING, "%s(%d) : count over (= %d)\n", __func__, __LINE__, count);
            break;
        }

        row = mysql_fetch_row(res);

        if (row == NULL) {
            break;
        } else {
            sscanf(row[0], "%u", &host[count].profile_id);
            if (row[1] != NULL) strcpy((char *)&host[count].org_type[0], row[1]);
            if (row[2] != NULL) strcpy((char *)&host[count].org_name[0], row[2]);
            if (row[3] != NULL) strcpy((char *)&host[count].host_type[0], row[3]);
            if (row[4] != NULL) strcpy((char *)&host[count].host_name[0], row[4]);
            if (row[5] != NULL) strcpy((char *)&host[count].host_ip[0], row[5]);
        }

        count ++;
    }

    mysql_free_result(res);
    return count;
}

int GetHlfInfoDB(unsigned int profile_id, hlf_info_t *hlf)
{
    int retval;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_HLF_INFO, HLF_INFO_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    if (row[0] != NULL) sscanf(row[0], "%u", &hlf->orderer_count);
    if (row[1] != NULL) sscanf(row[1], "%u", &hlf->org_count);
    if (row[2] != NULL) sscanf(row[2], "%u", &hlf->org_peer_count);
    if (row[3] != NULL) sscanf(row[3], "%u", &hlf->batch_timeout);
    if (row[4] != NULL) sscanf(row[4], "%u", &hlf->max_message_count);
    if (row[5] != NULL) sscanf(row[5], "%u", &hlf->absolute_max_bytes);
    if (row[6] != NULL) sscanf(row[6], "%u", &hlf->preferred_max_bytes);
    if (row[7] != NULL) strcpy(hlf->orderer_type, row[7]);

    mysql_free_result(res);
    return retval;
}

int GetBcmanagerIPDB(unsigned int profile_id, char *ip)
{
    int retval;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_BCMANAGER_IP, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    if (row[0] != NULL) strcpy(ip, row[0]);

    mysql_free_result(res);
    return retval;
}

int GetCountPeerOrdererDB(unsigned int profile_id)
{
    int retval, count = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_COUNT_PEER_ORDERER, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    sscanf(row[0], "%d", &count);

    mysql_free_result(res);
    return count;
}

int GetPeerOrdererStatusDB(unsigned int profile_id)
{
    int retval, count = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_PEER_ORDERER_STATUS, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    sscanf(row[0], "%d", &count);

    mysql_free_result(res);
    return count;
}

int UpdateStatusDB(unsigned int profile_id, char *hostname)
{
    int retval = 0;
    char Query[MAX_QUERY_LENGTH];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, UPDATE_SET_STATUS, HLF_HOST_INFO_PROFILE_TB, profile_id, hostname);
    xprint(FMS_DBM | FMS_INFO3, "%s(%d) : Query = %s\n", __func__, __LINE__, Query);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
    }

    return retval;
}

int UpdateUserStatusDB(unsigned int profile_id)
{
    int retval = 0;
    char Query[MAX_QUERY_LENGTH];

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, UPDATE_SET_USER_STATUS, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
    }

    return retval;
}

int GetBcmanagerStatusDB(unsigned int profile_id)
{
    int retval = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_BCMANAGER_STATUS, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    sscanf(row[0], "%d", &retval);

    mysql_free_result(res);
    return retval;
}

int GetKafkaStatusDB(unsigned int profile_id)
{
    int retval = 0;
    char Query[MAX_QUERY_LENGTH];
    MYSQL_RES *res;
    MYSQL_ROW row;

    memset((char *)&Query, 0x00, sizeof(Query));
    sprintf(Query, SELECT_KAFKA_STATUS, HLF_HOST_INFO_PROFILE_TB, profile_id);

    retval = ExecQuery1(Query, strlen(Query));
    if (retval) {
        xprint(FMS_DBM | FMS_ERROR, "%s(%d) : ExecQuery1 Error.\n", __func__, __LINE__);
        return -1;
    }

    res = mysql_use_result(handler1);
    row = mysql_fetch_row(res);

    if (row == NULL) {
        mysql_free_result(res);
        xprint(FMS_DBM | FMS_ERROR, " %s(%d) : result error.\n", __func__, __LINE__);
        return -1;
    }

    sscanf(row[0], "%d", &retval);

    mysql_free_result(res);
    return retval;
}
