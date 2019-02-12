#!/bin/sh

export GOPATH=/root/gopath
export GOROOT=/root/go
export PATH=$PATH:$GOROOT/bin:$GOPATH/src/github.com/hyperledger/fabric/build/bin
export BCNET_HOME=$HOME/BCNET

echo ''$BCNET_HOME'/bin/bcnetHSS'

test -x $BCNET_HOME/bin/bcnetHSS || exit 0

case "$1" in
  start)
    echo " "

    echo "     [BC_HSS]"
    #./bcnetHSS &
    $BCNET_HOME/bin/bcnetHSS &

    echo " "
    ;;

  stop)
    echo " "

    killist=`/bin/ps -ef | egrep "bcnetHSS" | egrep -v egrep | egrep -v vi | egrep -v sh | awk '{print $2}'`
    killist_name=`/bin/ps -ef | egrep "bcnetHSS" | egrep -v egrep | egrep -v vi | egrep -v sh | awk '{print $8}'`
    echo '     ---> KILL-LIST ['$killist_name']'
    ( kill -TERM $killist ) > /dev/null

    sleep 3

    ;;

  *)
    echo "Usage : run.sh {start|stop}"
    exit 1
esac


exit 0
