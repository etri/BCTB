CORE_PEER_LOCALMSPID="Org0MSP" \
CORE_PEER_TLS_ROOTCERT_FILE=/root/bcnetwork/conf/crypto-config/peerOrganizations/org0/peers/peer0.org0/tls/ca.crt \
CORE_PEER_MSPCONFIGPATH=/root/bcnetwork/conf/crypto-config/peerOrganizations/org0/users/Admin@org0/msp \
CORE_PEER_ADDRESS=peer0:7051 \
peer channel create -o orderer0:7050 -c ch1 -f /root/bcnetwork/conf/ch1.tx
