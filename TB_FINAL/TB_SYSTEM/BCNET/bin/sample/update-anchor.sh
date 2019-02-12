PEERS=( 'peer0' 'peer1' 'peer2' 'peer3' 'peer4' 'peer5' 'peer6' 'peer7' 'peer8' 'peer9' 'peer10' 'peer11' 'peer12' 'peer13' 'peer14' 'peer15' )
TOTAL_ORG=$1
NPEERS_PER_ORG=$2

echo "Update Anchor {Total Org count} {Org peer count}"
echo "Org Count         : $TOTAL_ORG"
echo "Peer Count(Org)   : $NPEERS_PER_ORG"

setGlobals () {
    PEER_ID=$1
    ORG_ID=$(($PEER_ID/$NPEERS_PER_ORG))
    export CORE_PEER_LOCALMSPID="Org${ORG_ID}MSP"
    export CORE_PEER_MSPCONFIGPATH=/root/bcnetwork/conf/crypto-config/peerOrganizations/org${ORG_ID}/users/Admin@org${ORG_ID}/msp
    export CORE_PEER_ADDRESS=${PEERS[$PEER_ID]}:7051
}

updateAnchorPeers() {
    PEER_ID=$1
    setGlobals $PEER_ID
    echo "----------------------------------------------------"
    echo "PEER_ID : $PEER_ID"
    echo "ORG_ID  : $ORG_ID"
    echo "CORE_PEER_LOCALMSPID    : $CORE_PEER_LOCALMSPID"
    echo "CORE_PEER_MSPCONFIGPATH : $CORE_PEER_MSPCONFIGPATH"
    echo "CORE_PEER_ADDRESS       : $CORE_PEER_ADDRESS"
    peer channel create -o orderer0:7050 -c ch1 -f /root/bcnetwork/conf/${CORE_PEER_LOCALMSPID}anchors.tx
}

for ((i=0; i<$TOTAL_ORG*$NPEERS_PER_ORG; i=i+$NPEERS_PER_ORG))
do
    updateAnchorPeers $i
done
