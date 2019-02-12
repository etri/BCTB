

#ifndef __DBMB_H__
#define __DBMB_H__

#ifndef TRUE
#define TRUE     1
#endif

#ifndef FALSE
#define FALSE    0
#endif

/* TB NAME */
#define HLF_HOST_INFO_PROFILE_TB    "hlf_host_info_profile"
#define HLF_INFO_TB                 "hyperledger_table"


/* ------------------------------------------------------------------------ */
/*     MAIN                                                                 */
/* ------------------------------------------------------------------------ */
extern void CloseDatabase(void);
extern int DatabaseInit(char *addr, char *userid, char *pass, char *name, unsigned int port);


/* ------------------------------------------------------------------------ */
/*     HSS                                                                  */
/* ------------------------------------------------------------------------ */
extern int GetCountHostDB(unsigned int profile_id);
extern int GetHlfhostInfoDB(unsigned int profile_id, host_info_t *host);
extern int GetHlfInfoDB(unsigned int profile_id, hlf_info_t *hlf);
extern int GetBcmanagerIPDB(unsigned int profile_id, char *ip);
extern int GetCountPeerOrdererDB(unsigned int profile_id);
extern int GetPeerOrdererStatusDB(unsigned int profile_id);
extern int UpdateStatusDB(unsigned int profile_id, char *hostname);
extern int UpdateUserStatusDB(unsigned int profile_id);
extern int GetBcmanagerStatusDB(unsigned int profile_id);
extern int GetKafkaStatusDB(unsigned int profile_id);



#endif /* __DBMB_H__ */
