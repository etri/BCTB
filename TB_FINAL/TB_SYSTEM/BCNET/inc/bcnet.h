

#ifndef __BCNET__H__
#define __BCNET__H__

/* ------------------------------------------------------------------------ */
/* Define Value                                                             */
/* ------------------------------------------------------------------------ */
#define MAX_DATA_LENGTH              65535
#define MAX_INFO_LENGTH              256

#define MAX_RESULT_LIST              512
#define MAX_RESULT_COUNT             100

#define MAX_NAME_LENGTH              32
#define MAX_TIME_LENGTH              32
#define MAX_SYSTEM_NUM               32
#define MAX_IP_LENGTH                16
#define MAX_CIPHER_LENGTH            16
#define MAX_ALAIS_LENGTH             8


/* ------------------------------------------------------------------------ */
/* Define struct                                                            */
/* ------------------------------------------------------------------------ */
typedef struct {
    unsigned int    cfg_flag;
    unsigned int    profile_id;
    char            org_type[MAX_NAME_LENGTH];
    char            org_name[MAX_NAME_LENGTH];
    char            host_type[MAX_NAME_LENGTH];
    char            host_name[MAX_NAME_LENGTH];
    char            host_ip[MAX_IP_LENGTH];
} __attribute__((packed)) host_info_t;

typedef struct {
    unsigned int    profile_id;
    unsigned int    orderer_count;
    unsigned int    org_count;
    unsigned int    org_peer_count;
    unsigned int    batch_timeout;
    unsigned int    max_message_count;
    unsigned int    absolute_max_bytes;
    unsigned int    preferred_max_bytes;
    char            orderer_type[MAX_NAME_LENGTH];
} __attribute__((packed)) hlf_info_t;

#endif /* __BCNET__H__*/
