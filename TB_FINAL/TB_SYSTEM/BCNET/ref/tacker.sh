#!/bin/sh

#export OS_TENANT_NAME=admin
#export OS_INTERFACE=internal
#export OS_REGION_NAME=RegionOne

case "$1" in
        start)
#                echo " "
#                echo "[tacker-server START]"
                 tacker-server --config-file /usr/local/etc/tacker/tacker.conf --log-file /var/log/tacker/tacker.log &
#                echo " "

                ;;

        stop)
                echo " "
                echo "[tacker-server STOP]"
                echo " "

                pkill tacker-server

                killist=`/bin/ps -ef | egrep "tacker-server" | egrep -v egrep | egrep -v vi | awk '{print $2}'`
                killist_name=`/bin/ps -ef | egrep "tacker-server" | egrep -v egrep | egrep -v vi | awk '{print $8}'`
                echo '     ---> KILL-LIST ['$killist_name']'
                ( kill -TERM $killist ) > /dev/null

                sleep 1

                killist=`/bin/ps -ef | egrep "tacker-server" | egrep -v egrep | egrep -v vi | awk '{print $2}'`
                killist_name=`/bin/ps -ef | egrep "tacker-server" | egrep -v egrep | egrep -v vi | awk '{print $8}'`
                echo '     ---> KILL-LIST ['$killist_name']'
                ( kill -KILL $killist ) > /dev/null

                sleep 1

                ;;
        *)
                echo "Usage : tacker.sh {start|stop}"
                exit 1
esac

exit 0
