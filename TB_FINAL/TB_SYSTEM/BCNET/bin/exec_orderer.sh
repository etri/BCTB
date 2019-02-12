#!/bin/sh

CONF_PATH=~/bcnetwork/conf
SAMPLE_PATH=~/BCNET/bin/sample

cd $CONF_PATH

MANAGER_IP=$1
HNAME=$2

echo "===================================================="
echo "bamanager IP  : $MANAGER_IP"
echo "Host name     : $HNAME"
echo ""

echo "===================================================="
echo "Get crypto-config $ genesis.block"

ftp -inv $MANAGER_IP << EOF
user root root 
prompt
cd /root/BCNET/bcnetwork/$HNAME
bin
get $HNAME.tar.gz
cd /root/bcnetwork/conf
get genesis.block
bye
EOF

tar xvfz $HNAME.tar.gz
mv genesis.block /root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/$HNAME.ordererorg0

echo "===================================================="
echo "Execute orderer"
#cp $SAMPLE_PATH/start-orderer.sh  /root/gopath/src/github.com/hyperledger/fabric

chmod 755 /root/gopath/src/github.com/hyperledger/fabric/start-orderer.sh
/root/gopath/src/github.com/hyperledger/fabric/start-orderer.sh


