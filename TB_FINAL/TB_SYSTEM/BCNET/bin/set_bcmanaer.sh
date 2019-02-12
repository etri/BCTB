#/bin/sh

CONF_PATH=~/bcnetwork/conf
cd $CONF_PATH


# Create genesis block
echo "Create genesis block"
configtxgen -profile $1 -outputBlock genesis.block
sleep 1

# Channel configuration transaction
echo "Channel configuration transaction"
configtxgen -profile $2 -outputCreateChannelTx ch1.tx -channelID ch1
sleep 1

# Anchor Peer Configuration Transaction
echo "Anchor Peer Configuration Transaction"

for ((i=0; i<$3; i=i+1))
do
    configtxgen -profile $2 -outputAnchorPeersUpdate Org"$i"MSPanchors.tx -channelID ch1 -asOrg Org"$i"MSP
    sleep 1
done

