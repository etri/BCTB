#!/bin/sh

CFG_PATH=/root/BCNET/cfg/tmp
cd $CFG_PATH

DIP=$1
FNAME=$2

cp $FNAME bcnet.cfg

#echo "===================================================="
#echo "Dest IP   : $DIP"
#echo "File Name : $FNAME"
#echo "===================================================="
#echo "PUT File : $FNAME"

ftp -inv $DIP << EOF
user root root 
prompt
cd /root/BCNET/cfg/
bin
passive
put bcnet.cfg
bye
EOF

rm -rf bcnet.cfg

