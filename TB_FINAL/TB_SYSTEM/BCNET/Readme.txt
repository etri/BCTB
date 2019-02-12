
* 설치 및 설정

export BCNET_HOME=$HOME/BCNET
export LANG=ko_KR.UTF-8

apt install libmysqlclient-dev
apt install vsftpd
   - /etc/vsftpd.conf --> write_enable=YES
   - /etc/ftpusers    --> # root (주석처리)

apt install xinetd telnetd
vi /etc/xinetd.conf
------------------------------------------
다음 추가

includedir /etc/xinetd.d

service telnet
{
disable = no
flags = REUSE
socket_type = stream
wait = no
user = root
server = /usr/sbin/in.telnetd
log_on_failure += USERID
}
------------------------------------------
vi /etc/securetty

다음 추가(root 접속)

tty62
tty63
pts/0
pts/1
pts/2
pts/3
pts/4
pts/5
pts/6
pts/7
pts/8
pts/9

# UART serial ports
ttyS0
ttyS1
------------------------------------------
/etc/init.d/xinetd restart
