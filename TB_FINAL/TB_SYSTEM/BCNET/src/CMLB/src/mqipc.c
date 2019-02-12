

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <sys/msg.h>

#include "xprint.h"
#include "mqipc.h"

int ipcClean(int msg_qid)
{
    int ret;
    ipc_msg_struct dropBuf;

    for(;;) {
        if ((ret = msgrcv(msg_qid, &dropBuf, sizeof(ipc_msg_struct), 0L, IPC_NOWAIT)) < 0) {
            if (errno == ENOMSG) {
                break;
            } else {
                xprint(FMS_CLB | FMS_FATAL, "msgrcv at ipcClean(%s)\n", strerror(errno));
                exit(2);
            }
        } else {
            continue;
        }
    }

    return(ret);
}

int ipcOpen(key_t msgKey, int msgflag)
{
    int msg_qid;

    if ((msg_qid = msgget(msgKey, msgflag)) == -1) {
        if (errno == ENOENT) {
            while((msg_qid = msgget(msgKey, msgflag)) == -1) {
                sleep(1);
            }
        } else {
            xprint(FMS_CLB | FMS_FATAL, "msgget at ipcOpen(%s)\n", strerror(errno));
            return(msg_qid);
        }
    }

    return(msg_qid);
}

int ipcSend(int msg_qid, unsigned int mtype, int src_qid, int msgsize, void *sndBuf)
{
    int ret;
    ipc_msg_struct msgBuf;

    if (mtype < 1 || msg_qid < 0) {
        xprint(FMS_CLB | FMS_ERROR, "mtype less than 1 or msg_qid is less than 0(%d)at ipcSend\n", msg_qid);
        return(-1);
    }

    memset(&msgBuf, 0x00, sizeof(ipc_msg_struct));

    msgBuf.msgtype = (long)mtype;
    msgBuf.src_qid = src_qid;
    msgBuf.dst_qid = msg_qid;
    msgBuf.datalen = msgsize;

    memcpy(msgBuf.userdata, sndBuf, msgsize);

    if ((ret = msgsnd(msg_qid, &msgBuf, msgsize + 16, IPC_NOWAIT)) == -1) {
        xprint(FMS_CLB | FMS_ERROR, "msgsnd at ipcSend(%d)(%s)\n", msg_qid, strerror(errno));
        switch(errno) {
            case EINVAL:    /* Invalid argument    */
                 break ;
            case EIDRM :    /* Identifier removed    */
                 break ;
            case EAGAIN :    /* Resource temporarily unavailable */
                 break ;
        }
    }

    return(ret);
}

int ipcRecv(int msg_qid, ipc_msg_struct *msgBuf, int msgflg)
{
    int ret;

    if (msg_qid < 0 || msgBuf == NULL) {
        xprint(FMS_CLB | FMS_ERROR, "Invaild argument at ipcRecv failed(%d)\n", msg_qid);
        return(-1);
    }

    if ((ret = msgrcv(msg_qid, msgBuf, sizeof(ipc_msg_struct), 0L, msgflg)) == -1) {
        xprint(FMS_CLB | FMS_ERROR, "msgrcv at ipcRecv(%s)\n", strerror(errno));
        switch(errno) {
            case EINVAL:    /* Invalid argument     */
                 break;
            case E2BIG: {    /* Arg list too long    */
                 char dropBuf[IPC_MAX_LENGTH];
                 if ((ret = msgrcv(msg_qid, dropBuf, IPC_MAX_LENGTH, 0L, 0)) == -1) {
                     xprint(FMS_CLB | FMS_ERROR, "drop msgrcv at ipcRecv(%s)\n", strerror(errno));
                 }
                 break;
            }
            case EIDRM:     /* Identifier removed   */
                 break;
            case EAGAIN:    /* Resource temporarily unavailable */
                 break;
            default:
                 break;
        }
        return(ret);
    }

    if (msgBuf->datalen > IPC_DATA_LENGTH) {
        xprint(FMS_CLB | FMS_FATAL, "unavailable message length(%d) at ipcRecv\n", msgBuf->datalen);
        return(-1);
    }

    return(msgBuf->msgtype);
}
