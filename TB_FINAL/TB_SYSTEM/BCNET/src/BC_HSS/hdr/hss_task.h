/* -------------------------------------------------------------------------
 * File Name   : hss_task.h
 * Author      : Hyeong-Ik Jeon
 * Copyright   : Copyright (C) 2018 by KBell Inc.
 * Description : HSS(Hyperledger setup Server)
 * History     : 18/09/12    Initial Coded.
 * -------------------------------------------------------------------------*/

#ifndef __HSS_TASK_H__
#define __HSS_TASK_H__

#define GEN_CRYPTO_CONFIG     "cryptogen generate --config=/root/bcnetwork/conf/crypto-config.yaml \
--output=/root/bcnetwork/conf/crypto-config"

#define YAML_CRYPTO_CONFIG_1   "\n\
OrdererOrgs:\n\
  - Name: OrdererOrg0\n\
    Domain: ordererorg0\n\
    Specs:\n"

#define YAML_CRYPTO_CONFIG_2   "\nPeerOrgs:\n"
#define YAML_CRYPTO_CONFIG_3   "\
  - Name: Org%d\n\
    Domain: org%d\n\
    Template:\n\
      Count: %u\n\n"

#define YAML_CRYPTO_CONFIG_4   "\
  - Name: Org%d\n\
    Domain: org%d\n\
    Template:\n\
      Count: %u\n\
      Start: %u\n\n"


#define YAML_CONFIGTX_1 "Profiles:\n\n\
    %s:\n\
       Orderer:\n\
           <<: *OrdererDefaults\n\
           Organizations:\n\
               - *OrdererOrg0\n\
       Consortiums:\n\
           SampleConsortium:\n\
               Organizations:\n"

#define YAML_CONFIGTX_2 "\n\
    %s:\n\
        Consortium: SampleConsortium\n\
        Application:\n\
            <<: *ApplicationDefaults\n\
            Organizations:\n"

#define YAML_CONFIGTX_3 "\n\
Organizations:\n\
    - &OrdererOrg0\n\
        Name: OrdererOrg0\n\
        ID: OrdererOrg0MSP\n\
        MSPDir: crypto-config/ordererOrganizations/ordererorg0/msp/\n\n"

#define YAML_CONFIGTX_4 "\
    - &Org%d\n\
        Name: Org%dMSP\n\
        ID: Org%dMSP\n\
        MSPDir: crypto-config/peerOrganizations/org%d/msp/\n\
        AnchorPeers:\n\
            - Host: peer%d\n\
              Port: 7051\n"

#define YAML_CONFIGTX_5 "\n\
Orderer: &OrdererDefaults\n\
    OrdererType: %s\n\
    Addresses:\n"

#define YAML_CONFIGTX_6 "\
    BatchTimeout: %us\n\
    BatchSize:\n\
        MaxMessageCount: %u\n\
        AbsoluteMaxBytes: %u MB\n\
        PreferredMaxBytes: %u KB\n\
    Kafka:\n\
        Brokers:\n\
            - kafka-zookeeper:9092\n\
    Organizations:\n\n\
Application: &ApplicationDefaults\n\
    Organizations:\n"

#define YAML_CONFIGTX_7 "\
    BatchTimeout: %us\n\
    BatchSize:\n\
        MaxMessageCount: %u\n\
        AbsoluteMaxBytes: %u MB\n\
        PreferredMaxBytes: %u KB\n\
    Organizations:\n\n\
Application: &ApplicationDefaults\n\
    Organizations:\n"


#define SH_START_ORDERER_1 "\
ORDERER_GENERAL_LOGLEVEL=info \\\n\
ORDERER_GENERAL_LISTENADDRESS=%s \\\n\
ORDERER_GENERAL_GENESISMETHOD=file \\\n\
ORDERER_GENERAL_GENESISFILE=/root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/%s.ordererorg0/genesis.block \\\n\
ORDERER_GENERAL_LOCALMSPID=OrdererOrg0MSP \\\n\
ORDERER_GENERAL_LOCALMSPDIR=/root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/%s.ordererorg0/msp \\\n\
ORDERER_GENERAL_TLS_ENABLED=false \\\n\
ORDERER_GENERAL_TLS_PRIVATEKEY=/root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/%s.ordererorg0/tls/server.key \\\n\
ORDERER_GENERAL_TLS_CERTIFICATE=/root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/%s.ordererorg0/tls/server.crt \\\n\
ORDERER_GENERAL_TLS_ROOTCAS=[/root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/%s.ordererorg0/tls/ca.crt,"

#define SH_START_ORDERER_2 "/root/bcnetwork/conf/crypto-config/peerOrganizations/org%d/peers/peer%d.org%d/tls/ca.crt"

#define SH_START_ORDERER_3 "\
CONFIGTX_ORDERER_BATCHTIMEOUT=%us \\\n\
CONFIGTX_ORDERER_ORDERERTYPE=%s \\\n\
CONFIGTX_ORDERER_KAFKA_BROKERS=[kafka-zookeeper:9092] \\\n\
orderer &"






extern int SethostsFile(void);
extern int SendBcnetCfgFile(void);
extern int ProcessBcManager(void);
extern int ProcessOrderer(void);
extern int ProcessPeer(void);
extern int ProcessKafkaZookeeper(void);


#endif
