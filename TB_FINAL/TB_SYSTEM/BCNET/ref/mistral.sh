#!/bin/sh

export OS_MISTRAL_URL=http://controller:8989/v2
#export OS_TENANT_NAME=service
#export OS_TENANT_NAME=admin

case "$1" in
        start)
#                echo " "
#                echo "[api & engine START]"
                 mistral-server --server api,engine --config-file /etc/mistral/mistral.conf &
                 sleep 1
                 mistral-server --server executor --config-file /etc/mistral/mistral.conf &
#                echo " "

                ;;

        stop)
                echo " "
                echo "[mistral STOP]"
                echo " "

                pkill mistral-server

                killist=`/bin/ps -ef | egrep "mistral-server" | egrep -v egrep | egrep -v vi | awk '{print $2}'`
                killist_name=`/bin/ps -ef | egrep "mistral-server" | egrep -v egrep | egrep -v vi | awk '{print $8}'`
                echo '     ---> KILL-LIST ['$killist_name']'
                ( kill -TERM $killist ) > /dev/null

                sleep 1

                killist=`/bin/ps -ef | egrep "mistral-server" | egrep -v egrep | egrep -v vi | awk '{print $2}'`
                killist_name=`/bin/ps -ef | egrep "mistral-server" | egrep -v egrep | egrep -v vi | awk '{print $8}'`
                echo '     ---> KILL-LIST ['$killist_name']'
                ( kill -KILL $killist ) > /dev/null

                sleep 1

                ;;
        *)
                echo "Usage : mistral.sh {start|stop}"
                exit 1
esac

exit 0
