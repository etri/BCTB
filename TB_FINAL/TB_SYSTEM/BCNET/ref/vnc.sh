#!/bin/sh


case "$1" in
        start)
                echo " "
                echo "[VNC START]"
#                vnc4server
                vnc4server -geometry 1280x800
                sleep 1
                ps -ef | grep vnc

                ;;

        stop)
                echo " "
                echo "[vnc STOP]"
                echo " "

                vnc4server -kill :1

                sleep 1
                ps -ef | grep vnc

                ;;
        *)
                echo "Usage : vnc.sh {start|stop}"
                exit 1
esac

exit 0
