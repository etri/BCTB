#!/bin/sh

CONF_PATH=~/bcnetwork/conf
SAMPLE_PATH=~/BCNET/bin/sample

cd $CONF_PATH

ORG_COUNT=$1
PEER_COUNT=$2
TOTAL_PEER_COUNT=$3
CWD=$PWD

echo "===================================================="
echo "PWD               : $CWD"
echo "Org Count         : $ORG_COUNT"
echo "Peer Count(Org)   : $PEER_COUNT"
echo "Peer Count(Total) : $TOTAL_PEER_COUNT"
echo ""


# Create channel 
echo "===================================================="
echo "Create Channel"
cp $SAMPLE_PATH/create-channel.sh $CONF_PATH/create-channel.sh
#/bin/bash $CONF_PATH/create-channel.sh 
/root/bcnetwork/conf/create-channel.sh
sleep 1


# Join channel
echo ""
echo "===================================================="
echo "Join Channel"
cp $SAMPLE_PATH/join-channel.sh $CONF_PATH/join-channel.sh
#/bin/bash $CONF_PATH/join-channel.sh $TOTAL_PEER_COUNT $PEER_COUNT
/root/bcnetwork/conf/join-channel.sh $TOTAL_PEER_COUNT $PEER_COUNT
sleep 1


# Update Anchor Peer 
echo ""
echo "===================================================="
echo "Update Anchor Peer"
cp $SAMPLE_PATH/update-anchor.sh $CONF_PATH/update-anchor.sh
#/bin/bash $CONF_PATH/update-anchor.sh $ORG_COUNT $PEER_COUNT
/root/bcnetwork/conf/update-anchor.sh $ORG_COUNT $PEER_COUNT


