/*
 -------------------------------------------------------------------------
  File Name       : dbmb_hss.h
  Author          : Hyeong-Ik Jeon
  Copyright       : Copyright (C) 2014 KBell Inc.
  Descriptions    : DBMB(DataBase Manager Block) hss
  History         : 18/09/14       Initial Coded
 -------------------------------------------------------------------------
*/
#ifndef __DBMB_HSS_H__
#define __DBMB_HSS_H__

#define SELECT_COUNT_HOST "SELECT COUNT(*) FROM %s WHERE profile_id = %u "

#define SELECT_HOST_INFO  "SELECT profile_id, org_type, org_name, host_type, host_name, host_ip FROM %s \
WHERE profile_id = %u AND status > 0"

#define SELECT_HLF_INFO "SELECT orderer_cnt, peer_org_cnt, org_peer_cnt, \
batch_timeout, max_message_cnt, absolute_max_bytes, preferred_max_bytes, orderer_type FROM %s WHERE idx = %u"

#define SELECT_BCMANAGER_IP "SELECT host_ip FROM %s WHERE profile_id = %u AND host_name = 'bcmanager'"

#define SELECT_COUNT_PEER_ORDERER "SELECT COUNT(*) FROM %s \
WHERE profile_id = %u AND (host_type = 'orderer' || host_type = 'peer' || host_type = 'kafka')"

#define SELECT_PEER_ORDERER_STATUS "SELECT COUNT(*) FROM %s \
WHERE profile_id = %u AND status = 2 AND (host_type = 'orderer' || host_type = 'peer' || host_type = 'kafka')"

#define UPDATE_SET_STATUS "UPDATE %s SET status = 2 WHERE profile_id = %u AND host_name = '%s'"
#define UPDATE_SET_USER_STATUS "UPDATE %s SET status = 2 WHERE profile_id = %u AND host_type = 'user'"
#define SELECT_BCMANAGER_STATUS "SELECT status FROM %s WHERE profile_id = %u AND host_name = 'bcmanager'"
#define SELECT_KAFKA_STATUS "SELECT status FROM %s WHERE profile_id = %u AND host_name = 'kafka-zookeeper'"




#endif /* __DBMB_HSS_H__ */
