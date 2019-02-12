PEERS=( 'peer0' 'peer1' 'peer2' 'peer3' 'peer4' 'peer5' 'peer6' 'peer7' 'peer8' 'peer9' 'peer10' 'peer11' 'peer12' 'peer13' 'peer14' 'peer15' )
TOTAL_PEER=$1
NPEERS_PER_ORG=$2

echo "Peer Count(Org)   : $NPEERS_PER_ORG"
echo "Peer Count(Total) : $TOTAL_PEER"

setGlobals () {
    PEER_ID=$1
    ORG_ID=$(($PEER_ID/$NPEERS_PER_ORG))
    export CORE_PEER_LOCALMSPID="Org${ORG_ID}MSP"
    export CORE_PEER_MSPCONFIGPATH=/root/bcnetwork/conf/crypto-config/peerOrganizations/org${ORG_ID}/users/Admin@org${ORG_ID}/msp
    export CORE_PEER_ADDRESS=${PEERS[$PEER_ID]}:7051
}

joinChannel () {
    echo "Join Channel {Total peer count} {Org peer count}"

    for ((peer=0; peer<$TOTAL_PEER; peer=peer+1)) do
        setGlobals $peer
        echo "----------------------------------------------------"
        echo "PEER_ID : $PEER_ID"
        echo "ORG_ID  : $ORG_ID"
        echo "CORE_PEER_LOCALMSPID    : $CORE_PEER_LOCALMSPID"
        echo "CORE_PEER_MSPCONFIGPATH : $CORE_PEER_MSPCONFIGPATH"
        echo "CORE_PEER_ADDRESS       : $CORE_PEER_ADDRESS"
        peer channel join -b /root/bcnetwork/conf/ch1.block
        sleep 5
#        echo
    done
}

joinChannel 
