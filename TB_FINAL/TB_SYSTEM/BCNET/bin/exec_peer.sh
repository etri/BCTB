#!/bin/sh

CONF_PATH=~/bcnetwork/conf
SAMPLE_PATH=~/BCNET/bin/sample

cd $CONF_PATH

MANAGER_IP=$1
HNAME=$2
ONAME=$3
ONUM=$4

echo "===================================================="
echo "bamanager IP  : $MANAGER_IP"
echo "Host name     : $HNAME"
echo "Org name      : $ONAME"
echo "Org number    : $ONUM"
echo ""

echo "===================================================="
echo "Get crypto-config"

ftp -inv $MANAGER_IP << EOF
user root root 
prompt
cd /root/BCNET/bcnetwork/$HNAME
bin
get $HNAME.tar.gz
bye
EOF

tar xvfz $HNAME.tar.gz

echo "===================================================="
echo "Execute peer"

cp $SAMPLE_PATH/start-peer.sh  /root/gopath/src/github.com/hyperledger/fabric

sed -i 's/HOST_NAME.ORG_NAME/'${HNAME}'.'${ONAME}'/;s/HOST_NAME/'${HNAME}'/;s/ORG_NAME/'${ONAME}'/;s/OrgNUM/Org'${ONUM}'/;' /root/gopath/src/github.com/hyperledger/fabric/start-peer.sh

chmod 755 /root/gopath/src/github.com/hyperledger/fabric/start-peer.sh
/root/gopath/src/github.com/hyperledger/fabric/start-peer.sh



