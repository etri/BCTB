#/bin/sh

CONF_PATH=~/bcnetwork/conf
SAMPLE_PATH=~/BCNET/bin/sample

cd $CONF_PATH

KAFKA_IP=$1

echo "KAFKA IP  : $KAFKA_IP"

echo ""

echo "===================================================="
echo "Execute kafka"

cp $SAMPLE_PATH/docker-compose.yaml ./

sed -i 's/KAFKA_IP/'${KAFKA_IP}'/;' docker-compose.yaml

docker-compose up &
