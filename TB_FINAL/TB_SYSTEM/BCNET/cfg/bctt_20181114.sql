-- MySQL dump 10.16  Distrib 10.1.34-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: bctt
-- ------------------------------------------------------
-- Server version	10.1.34-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ethereum_table`
--

DROP TABLE IF EXISTS `ethereum_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ethereum_table` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `time_stamp` datetime DEFAULT NULL,
  `profile_name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `network_type` varchar(16) DEFAULT NULL,
  `node_cnt` int(11) DEFAULT NULL,
  `client_cnt` int(11) DEFAULT NULL,
  `num_cpus` varchar(16) DEFAULT NULL,
  `mem_size` varchar(16) DEFAULT NULL,
  `disk_size` varchar(16) DEFAULT NULL,
  `network_id` varchar(64) DEFAULT NULL,
  `network_name` varchar(64) DEFAULT NULL,
  `chainid` varchar(32) DEFAULT NULL,
  `difficulty` varchar(32) DEFAULT NULL,
  `gaslimit` varchar(32) DEFAULT NULL,
  `flavor_id` varchar(64) DEFAULT NULL,
  `flavor_name` varchar(128) DEFAULT NULL,
  `vnfd_id` varchar(64) DEFAULT NULL,
  `vnf_id` varchar(64) DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ethereum_table`
--

LOCK TABLES `ethereum_table` WRITE;
/*!40000 ALTER TABLE `ethereum_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `ethereum_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flavor_table`
--

DROP TABLE IF EXISTS `flavor_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flavor_table` (
  `name` varchar(128) NOT NULL DEFAULT '',
  `id` varchar(256) NOT NULL DEFAULT '',
  `vcpus` int(11) unsigned NOT NULL DEFAULT '0',
  `ram` int(11) unsigned NOT NULL DEFAULT '0',
  `root_disk` int(11) unsigned NOT NULL DEFAULT '0',
  `ephemeral_disk` int(11) unsigned NOT NULL DEFAULT '0',
  `swap_disk` int(11) unsigned NOT NULL DEFAULT '0',
  `rxtx_factor` varchar(16) NOT NULL DEFAULT '',
  `is_public` varchar(16) NOT NULL DEFAULT '',
  `disabled` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flavor_table`
--

LOCK TABLES `flavor_table` WRITE;
/*!40000 ALTER TABLE `flavor_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `flavor_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hlf_host_info_profile`
--

DROP TABLE IF EXISTS `hlf_host_info_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hlf_host_info_profile` (
  `profile_id` int(10) unsigned NOT NULL DEFAULT '0',
  `vdu_name` varchar(32) NOT NULL DEFAULT ' ',
  `org_type` varchar(32) NOT NULL DEFAULT ' ',
  `org_name` varchar(32) NOT NULL DEFAULT ' ',
  `host_type` varchar(32) NOT NULL DEFAULT ' ',
  `host_name` varchar(32) NOT NULL DEFAULT ' ',
  `host_ip` varchar(16) NOT NULL DEFAULT ' ',
  `status` int(10) unsigned NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 MAX_ROWS=100000;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hlf_host_info_profile`
--

LOCK TABLES `hlf_host_info_profile` WRITE;
/*!40000 ALTER TABLE `hlf_host_info_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `hlf_host_info_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_table`
--

DROP TABLE IF EXISTS `host_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `host_table` (
  `hostName` varchar(45) DEFAULT NULL,
  `service` varchar(45) DEFAULT NULL,
  `zone` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_table`
--

LOCK TABLES `host_table` WRITE;
/*!40000 ALTER TABLE `host_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hyperledger_table`
--

DROP TABLE IF EXISTS `hyperledger_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hyperledger_table` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `time_stamp` datetime DEFAULT NULL,
  `profile_name` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `orderer_cnt` int(11) DEFAULT NULL,
  `peer_org_cnt` int(11) DEFAULT NULL,
  `org_peer_cnt` int(11) DEFAULT NULL,
  `orderer_type` varchar(16) DEFAULT NULL,
  `batch_timeout` int(11) DEFAULT NULL,
  `max_message_cnt` int(11) DEFAULT NULL,
  `absolute_max_bytes` int(11) DEFAULT NULL,
  `preferred_max_bytes` int(11) DEFAULT NULL,
  `network_id` varchar(64) DEFAULT NULL,
  `network_name` varchar(64) DEFAULT NULL,
  `flavor_id` varchar(64) DEFAULT NULL,
  `flavor_name` varchar(64) DEFAULT NULL,
  `vnfd_id` varchar(64) DEFAULT NULL,
  `vnf_id` varchar(64) DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hyperledger_table`
--

LOCK TABLES `hyperledger_table` WRITE;
/*!40000 ALTER TABLE `hyperledger_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `hyperledger_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_table`
--

DROP TABLE IF EXISTS `image_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_table` (
  `name` varchar(64) DEFAULT NULL,
  `id` varchar(64) NOT NULL,
  `format` varchar(64) DEFAULT NULL,
  `os` varchar(64) DEFAULT NULL,
  `size` varchar(64) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `visibility` varchar(32) DEFAULT NULL,
  `_protected` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_table`
--

LOCK TABLES `image_table` WRITE;
/*!40000 ALTER TABLE `image_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `image_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instance_table`
--

DROP TABLE IF EXISTS `instance_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instance_table` (
  `diskConfig` varchar(64) NOT NULL,
  `availability_zone` varchar(64) DEFAULT NULL,
  `host` varchar(64) DEFAULT NULL,
  `hypervisor_hostname` varchar(64) DEFAULT NULL,
  `instance_name` varchar(64) DEFAULT NULL,
  `power_state` varchar(64) DEFAULT NULL,
  `task_state` varchar(64) DEFAULT NULL,
  `vm_state` varchar(64) DEFAULT NULL,
  `launched_at` varchar(64) DEFAULT NULL,
  `terminated_at` varchar(64) DEFAULT NULL,
  `accessIPv4` varchar(64) DEFAULT NULL,
  `accessIPv6` varchar(64) DEFAULT NULL,
  `addresses` varchar(64) DEFAULT NULL,
  `config_drive` varchar(64) DEFAULT NULL,
  `created` varchar(64) DEFAULT NULL,
  `flavor_name` varchar(64) DEFAULT NULL,
  `flavor_id` varchar(16) DEFAULT NULL,
  `hostId` varchar(64) DEFAULT NULL,
  `id` varchar(64) NOT NULL,
  `image_name` varchar(128) DEFAULT NULL,
  `image_id` varchar(64) DEFAULT NULL,
  `key_name` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `progress` varchar(16) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `properties` varchar(64) DEFAULT NULL,
  `security_groups` varchar(64) DEFAULT NULL,
  `status` varchar(64) DEFAULT NULL,
  `updated` varchar(64) DEFAULT NULL,
  `user_id` varchar(64) DEFAULT NULL,
  `volumes_attached` varchar(64) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instance_table`
--

LOCK TABLES `instance_table` WRITE;
/*!40000 ALTER TABLE `instance_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `instance_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `network_table`
--

DROP TABLE IF EXISTS `network_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `network_table` (
  `network_name` varchar(64) DEFAULT NULL,
  `network_id` varchar(64) NOT NULL,
  `subnet_id` varchar(64) DEFAULT NULL,
  `cidr` varchar(64) DEFAULT NULL,
  `gateway_ip` varchar(64) DEFAULT NULL,
  `subnet_name` varchar(64) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `allocation_pools` varchar(64) DEFAULT NULL,
  `dns_nameservers` varchar(32) DEFAULT NULL,
  `enable_dhcp` varchar(16) DEFAULT NULL,
  `ip_version` int(11) DEFAULT NULL,
  `external` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`network_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `network_table`
--

LOCK TABLES `network_table` WRITE;
/*!40000 ALTER TABLE `network_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `network_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_table`
--

DROP TABLE IF EXISTS `project_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_table` (
  `project_id` varchar(64) NOT NULL,
  `project_name` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `domain_id` varchar(64) DEFAULT NULL,
  `enabled` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_table`
--

LOCK TABLES `project_table` WRITE;
/*!40000 ALTER TABLE `project_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_table`
--

DROP TABLE IF EXISTS `resource_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_table` (
  `host_name` varchar(32) NOT NULL DEFAULT '',
  `total_cpu` int(10) unsigned NOT NULL DEFAULT '0',
  `used_cpu` int(10) unsigned NOT NULL DEFAULT '0',
  `total_mem` int(10) unsigned NOT NULL DEFAULT '0',
  `used_mem` int(10) unsigned NOT NULL DEFAULT '0',
  `total_disk` int(10) unsigned NOT NULL DEFAULT '0',
  `used_disk` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`host_name`)
) ENGINE=InnoDB DEFAULT CHARSET=euckr;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_table`
--

LOCK TABLES `resource_table` WRITE;
/*!40000 ALTER TABLE `resource_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `router_table`
--

DROP TABLE IF EXISTS `router_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `router_table` (
  `admin_state_up` varchar(64) DEFAULT NULL,
  `availability_zone_hints` varchar(64) DEFAULT NULL,
  `availability_zones` varchar(64) DEFAULT NULL,
  `created_at` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `distributed` varchar(64) DEFAULT NULL,
  `external_gateway_info` varchar(256) DEFAULT NULL,
  `flavor_id` varchar(64) DEFAULT NULL,
  `ha` varchar(64) DEFAULT NULL,
  `id` varchar(64) NOT NULL,
  `interfaces_info` varchar(256) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `revision_number` varchar(64) DEFAULT NULL,
  `routes` varchar(64) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `tags` varchar(64) DEFAULT NULL,
  `updated_at` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `router_table`
--

LOCK TABLES `router_table` WRITE;
/*!40000 ALTER TABLE `router_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `router_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subnet_table`
--

DROP TABLE IF EXISTS `subnet_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subnet_table` (
  `allocation_pools` varchar(64) NOT NULL,
  `cidr` varchar(64) DEFAULT NULL,
  `created_at` varchar(64) DEFAULT NULL,
  `description` varchar(64) DEFAULT NULL,
  `dns_nameservers` varchar(64) DEFAULT NULL,
  `enable_dhcp` varchar(64) DEFAULT NULL,
  `gateway_ip` varchar(64) DEFAULT NULL,
  `host_routes` varchar(64) DEFAULT NULL,
  `id` varchar(64) NOT NULL,
  `ip_version` varchar(64) DEFAULT NULL,
  `ipv6_address_mode` varchar(64) DEFAULT NULL,
  `ipv6_ra_mode` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `network_id` varchar(64) DEFAULT NULL,
  `project_id` varchar(64) DEFAULT NULL,
  `revision_number` varchar(64) DEFAULT NULL,
  `segment_id` varchar(64) DEFAULT NULL,
  `service_types` varchar(64) DEFAULT NULL,
  `subnetpool_id` varchar(64) DEFAULT NULL,
  `tags` varchar(64) DEFAULT NULL,
  `updated_at` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subnet_table`
--

LOCK TABLES `subnet_table` WRITE;
/*!40000 ALTER TABLE `subnet_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `subnet_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vcscf_info`
--

DROP TABLE IF EXISTS `vcscf_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vcscf_info` (
  `hss_ip` varchar(64) NOT NULL,
  `domain_name` varchar(64) DEFAULT NULL,
  `virtual_ip` varchar(64) DEFAULT NULL,
  `pcscf_domain` varchar(64) DEFAULT NULL,
  `icscf_domain` varchar(64) DEFAULT NULL,
  `scscf_domain` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vcscf_info`
--

LOCK TABLES `vcscf_info` WRITE;
/*!40000 ALTER TABLE `vcscf_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `vcscf_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnc_config_table`
--

DROP TABLE IF EXISTS `vnc_config_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vnc_config_table` (
  `ip_addr` varchar(16) NOT NULL,
  `port` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`ip_addr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnc_config_table`
--

LOCK TABLES `vnc_config_table` WRITE;
/*!40000 ALTER TABLE `vnc_config_table` DISABLE KEYS */;
INSERT INTO `vnc_config_table` VALUES ('129.254.194.67','6080');
/*!40000 ALTER TABLE `vnc_config_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnf_table`
--

DROP TABLE IF EXISTS `vnf_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vnf_table` (
  `vnf_name` varchar(64) NOT NULL,
  `vnf_id` varchar(64) DEFAULT NULL,
  `vnfd_name` varchar(64) DEFAULT NULL,
  `vnfd_id` varchar(64) DEFAULT NULL,
  `status` varchar(64) DEFAULT NULL,
  `desc` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnf_table`
--

LOCK TABLES `vnf_table` WRITE;
/*!40000 ALTER TABLE `vnf_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `vnf_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vnfd_table`
--

DROP TABLE IF EXISTS `vnfd_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vnfd_table` (
  `vnfd_name` varchar(64) NOT NULL,
  `vnfd_id` varchar(64) DEFAULT NULL,
  `image_name` varchar(64) DEFAULT NULL,
  `flavor_name` varchar(64) DEFAULT NULL,
  `package_name` varchar(64) DEFAULT NULL,
  `package_pathname` varchar(256) DEFAULT NULL,
  `desc` varchar(256) DEFAULT NULL,
  `vnfd` varchar(8192) DEFAULT NULL,
  `image_id` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vnfd_table`
--

LOCK TABLES `vnfd_table` WRITE;
/*!40000 ALTER TABLE `vnfd_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `vnfd_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-14 17:38:27
