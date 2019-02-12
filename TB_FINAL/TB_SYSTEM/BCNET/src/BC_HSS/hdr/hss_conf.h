/* -------------------------------------------------------------------------
 * File Name   : hss_conf.h
 * Author      : Hyeong-Ik Jeon
 * Copyright   : Copyright (C) 2018 by KBell Inc.
 * Description : HSS(Hyperledger Setup Server)
 * History     : 18/09/11    Initial Coded.
 * -------------------------------------------------------------------------*/

#ifndef __HSS_CONF_H__
#define __HSS_CONF_H__


#define MAX_STR_LENGTH        256
#define MAX_ADDR_LENGTH       32
#define MAX_ID_LENGTH         32
#define MAX_DB_SCHEMA         32


typedef struct {
    char         EnvPath[MAX_INFO_LENGTH];

    char         DbAddr[MAX_ADDR_LENGTH];
    char         DbUserid[MAX_ID_LENGTH];
    char         DbPass[MAX_ID_LENGTH];
    char         DbName[MAX_DB_SCHEMA];

    unsigned int DbPort;

    unsigned int profile_id;
    unsigned int org_count;
    unsigned int org_peer_count;
    char         hname[MAX_NAME_LENGTH];

    char         bcmanager_ip[MAX_IP_LENGTH];
    host_info_t  myhost; /*my host*/

    int          crypto_del;
    int          h_count;
    host_info_t  h_info[MAX_SYSTEM_NUM];

    hlf_info_t   hlf;
} hss_config_t;

extern int read_config(char *path);
extern void FreeVariable(void);
extern int InitVariable(char *path);

extern hss_config_t hssConf;

#endif
