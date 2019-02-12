#!/bin/sh

auto_tel(){
sleep 1;echo 'root'
sleep 1;echo 'root'
sleep 1;echo '/root/BCNET/bin/autorun.sh > /root/BCNET/log/autorun.log'
sleep 1;
}
auto_tel | telnet $1
