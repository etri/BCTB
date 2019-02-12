#/bin/sh

otype=$1
oname=$2
htype=$3
hname=$4
TOTAL_ORG=$5
NPEERS_PER_ORG=$6

echo "=============================================="
echo "Org  Type       : $otype"
echo "Org  Name       : $oname"
echo "Host Type       : $htype"
echo "Host Name       : $hname"
echo "Org Count       : $TOTAL_ORG"
echo "Peer Count(Org) : $NPEERS_PER_ORG"
echo ""

echo "=============================================="
case "$otype" in
  orderer)
    case "$htype" in
      orderer)
        echo "$otype --> $htype --> $hname"
          mkdir -p /root/BCNET/bcnetwork/$hname/crypto-config/ordererOrganizations/ordererorg0/orderers/$hname.ordererorg0

          cp -rf /root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/orderers/$hname.ordererorg0/* \
                /root/BCNET/bcnetwork/$hname/crypto-config/ordererOrganizations/ordererorg0/orderers/$hname.ordererorg0

          for ((i=0; i<$TOTAL_ORG*$NPEERS_PER_ORG; i=i+$NPEERS_PER_ORG)) do
              ORG_ID=$(($i/$NPEERS_PER_ORG))
              echo "---------------------"
              echo "    PEER_ID : $i"
              echo "    ORG_ID  : $ORG_ID"

              mkdir -p /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/org${ORG_ID}/peers/peer${i}.org${ORG_ID}/tls
              cp -rf /root/bcnetwork/conf/crypto-config/peerOrganizations/org${ORG_ID}/peers/peer${i}.org${ORG_ID}/tls/ca.crt \
                /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/org${ORG_ID}/peers/peer${i}.org${ORG_ID}/tls 
          done


          cd /root/BCNET/bcnetwork/$hname
          tar cvfz $hname.tar.gz crypto-config 

        ;;

      user)
        echo "$otype --> $htype --> $hname"
          mkdir -p /root/BCNET/bcnetwork/$hname/crypto-config/ordererOrganizations/ordererorg0/users/Admin@ordererorg0

          cp -rf /root/bcnetwork/conf/crypto-config/ordererOrganizations/ordererorg0/users/Admin@ordererorg0/* \
                 /root/BCNET/bcnetwork/$hname/crypto-config/ordererOrganizations/ordererorg0/users/Admin@ordererorg0

          cd /root/BCNET/bcnetwork/$hname
          tar cvfz $hname.tar.gz crypto-config

        ;;
      *)
        echo ""
        echo "ERROR :: orderer --> htype"
        exit 1
    esac

    ;;

  peer)
    case "$htype" in
      peer)
        echo "$otype --> $htype --> $hname"
          mkdir -p /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/$oname/peers/$hname.$oname

          cp -rf /root/bcnetwork/conf/crypto-config/peerOrganizations/$oname/peers/$hname.$oname/* \
                /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/$oname/peers/$hname.$oname

          cd /root/BCNET/bcnetwork/$hname
          tar cvfz $hname.tar.gz crypto-config

        ;;

      user)
        echo "$otype --> $htype --> $hname"
          mkdir -p /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/$oname/users/Admin@$oname

          cp -rf /root/bcnetwork/conf/crypto-config/peerOrganizations/$oname/users/Admin@$oname/* \
                 /root/BCNET/bcnetwork/$hname/crypto-config/peerOrganizations/$oname/users/Admin@$oname

          cd /root/BCNET/bcnetwork/$hname
          tar cvfz $hname.tar.gz crypto-config

        ;;
      *)
        echo ""
        echo "ERROR :: peer --> htype"
        exit 1
    esac

    ;;

  *)
    echo "Usage : cp_crypto.sh orderer ordererorg0 orderer orderer0 3 2"
    echo "Usage : cp_crypto.sh orderer ordererorg0 user adminordererorg0 2 3"
    echo "Usage : cp_crypto.sh peer org0 peer peer0 2 2"
    echo "Usage : cp_crypto.sh peer org0 user adminorg0 3 2"
    exit 1
esac


exit 0
