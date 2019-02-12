#!/bin/sh

export GOPATH=/root/gopath
export GOROOT=/root/go
export PATH=$PATH:$GOROOT/bin:$GOPATH/src/github.com/hyperledger/fabric/build/bin
export BCNET_HOME=/root/BCNET

echo 'Test :: '$BCNET_HOME'/bin/bcnetHSS'
test -x $BCNET_HOME/bin/bcnetHSS || exit 0

echo 'Exec :: '$BCNET_HOME'/bin/bcnetHSS &'
$BCNET_HOME/bin/bcnetHSS &

echo 'End  :: '$BCNET_HOME'/bin/bcnetHSS &'
