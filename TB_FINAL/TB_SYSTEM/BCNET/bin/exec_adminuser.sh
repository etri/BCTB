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


