DROP DATABASE IF EXISTS `perficient`;

CREATE DATABASE perficient;

USE perficient;

GRANT ALL PRIVILEGES ON perficient.* TO perfuser@localhost IDENTIFIED BY 'perfpwd' WITH GRANT OPTION;



--
-- Definition of table `designations`
--

DROP TABLE IF EXISTS `designations`;
CREATE TABLE `designations` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `designation` varchar(45) NOT NULL,
  `sbu` varchar(45) NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Definition of table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_id` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `middlename` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `contact_no` varchar(500) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `address` varchar(500) NOT NULL,
  `pincode` int(6) unsigned DEFAULT NULL,
  `blood_group` varchar(10) DEFAULT NULL,
  `pan_card_no` varchar(20) DEFAULT NULL,
  `img_src` varchar(45) DEFAULT NULL,
  `designation` int(10) unsigned NOT NULL,
  `joindate` date NOT NULL,
  `supervisor` int(10) unsigned NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  `billable` int(1) unsigned DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `last_working_date` date DEFAULT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime DEFAULT NULL,
  `dt_modified` datetime DEFAULT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  `gender` varchar(10) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_1` (`designation`),
  KEY `FK_employee_2` (`supervisor`),
  CONSTRAINT `FK_employee_1` FOREIGN KEY (`designation`) REFERENCES `designations` (`pk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_employee_2` FOREIGN KEY (`supervisor`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=latin1;


--
-- Definition of table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) NOT NULL,
  `st_date` datetime NOT NULL,
  `end_date` datetime ,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_projects` (`created_by`),
  KEY `FK_modified_by_projects` (`modified_by`),
  CONSTRAINT `FK_created_by_projects` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_projects` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `status`
--

DROP TABLE IF EXISTS `notification_status`;
CREATE TABLE `notification_status` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status_value` varchar(45) NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_status` (`created_by`),
  KEY `FK_modified_by_status` (`modified_by`),
  CONSTRAINT `FK_created_by_status` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_status` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `notification_type`
--

DROP TABLE IF EXISTS `notification_type`;
CREATE TABLE `notification_type` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type_value` varchar(45) NOT NULL,
  `category` varchar(45) ,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_type` (`created_by`),
  KEY `FK_modified_by_type` (`modified_by`),
  CONSTRAINT `FK_created_by_type` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_type` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Definition of table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_roles` (`created_by`),
  KEY `FK_modified_by_roles` (`modified_by`),
  CONSTRAINT `FK_created_by_roles` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_roles` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_designation_history`
--

DROP TABLE IF EXISTS `employee_designation_history`;
CREATE TABLE `employee_designation_history` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `designation_pk` int(10) unsigned NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date ,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_designation_history_1` (`employee_pk`),
  KEY `FK_employee_designation_history_2` (`designation_pk`),
  KEY `FK_created_by_edh` (`created_by`),
  KEY `FK_modified_by_edh` (`modified_by`),
  CONSTRAINT `FK_created_by_edh` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_edh` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_designation_history_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_designation_history_2` FOREIGN KEY (`designation_pk`) REFERENCES `designations` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_leaves`
--

DROP TABLE IF EXISTS `employee_leaves`;
CREATE TABLE `employee_leaves` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `applied_by_pk` int(10) unsigned NOT NULL,
  `request_type` varchar(45) NOT NULL,
  `request_title` varchar(100) NOT NULL,
  `comments` varchar(100) NOT NULL,
  `dt_from` datetime NOT NULL,
  `dt_from_half` varchar(10),
  `dt_end` datetime NOT NULL,
  `dt_end_half` varchar(10),
  `hours` int(5) NOT NULL,  
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_pk` (`employee_pk`),
  KEY `FK_created_by_el` (`created_by`),
  KEY `FK_modified_by_el` (`modified_by`),
  CONSTRAINT `FK_created_by_el` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_el` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `employee_leaves_ibfk_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Definition of table `employee_leaves_details`
--

--
-- Definition of table `employee_leaves_supervisor_status`
--

/*DROP TABLE IF EXISTS `employee_leaves_supervisor_response`;
CREATE TABLE `employee_leaves_supervisor_status` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_leaves_details_pk` int(10) unsigned NOT NULL,
  `supervisor_pk` int(10) unsigned NOT NULL,
  `status_pk` int(10) unsigned NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_leaves_details_pk` (`employee_leaves_details_pk`),
  KEY `FK_created_by_elsr` (`created_by`),
  KEY `FK_modified_by_elsr` (`modified_by`),
  CONSTRAINT `FK_created_by_elsr` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_elsr` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_leaves__details` FOREIGN KEY (`employee_leaves_details_pk`) REFERENCES `employee_leaves_details` (`pk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
*/


--
-- Definition of table `employee_leaves_maintainance`
--

DROP TABLE IF EXISTS `employee_leaves_maintainance`;
CREATE TABLE `employee_leaves_maintainance` (
  `pk` int(10) unsigned NOT NULL,
  `employee_pk` int(10) unsigned NOT NULL,
  `year` int(4) unsigned NOT NULL,
  `carried_on` int(1) unsigned DEFAULT NULL,
  `bye_out` int(1) DEFAULT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_leaves_maintainance_1` (`employee_pk`),
  KEY `FK_created_by_elm` (`created_by`),
  KEY `FK_modified_by_elm` (`modified_by`),
  CONSTRAINT `FK_created_by_elm` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_elm` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_leaves_maintainance_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--
-- Definition of table `employee_login`
--

DROP TABLE IF EXISTS `employee_login`;
CREATE TABLE `employee_login` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `email_id` varchar(45) NOT NULL,
  `pwd` varchar(255) NOT NULL,
  `emp_lock` tinyint(1) NOT NULL,
  `last_login` datetime,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime,
  `dt_modified` datetime,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_login_1` (`employee_pk`),
  KEY `FK_created_by_emplog` (`created_by`),
  KEY `FK_modified_by_emplog` (`modified_by`),
  CONSTRAINT `FK_created_by_emplog` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_emplog` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_login_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_projects`
--

DROP TABLE IF EXISTS `employee_projects`;
CREATE TABLE `employee_projects` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `project_pk` int(10) unsigned NOT NULL,
  `dt_started` date NOT NULL,
  `dt_ended` date NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_pk` (`employee_pk`),
  KEY `project_pk` (`project_pk`),
  KEY `FK_created_by_empproj` (`created_by`),
  KEY `FK_modified_by_empproj` (`modified_by`),
  CONSTRAINT `FK_created_by_empproj` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_empproj` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_pk` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_project_pk` FOREIGN KEY (`project_pk`) REFERENCES `projects` (`pk`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

--
-- Definition of table `employee_roles`
--

DROP TABLE IF EXISTS `employee_roles`;
CREATE TABLE `employee_roles` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `role_pk` int(10) unsigned NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_pk` (`employee_pk`),
  KEY `role_pk` (`role_pk`),
  KEY `FK_created_by_emprol` (`created_by`),
  KEY `FK_modified_by_emprol` (`modified_by`),
  CONSTRAINT `FK_created_by_emprol` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_emprol` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `employee_roles_ibfk_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`) ON DELETE CASCADE,
  CONSTRAINT `employee_roles_ibfk_2` FOREIGN KEY (`role_pk`) REFERENCES `roles` (`pk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `technology`
--
DROP TABLE IF EXISTS `technology`;
CREATE TABLE `technology` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `technical_skill` varchar(45) NOT NULL,
  `base_language` varchar(45) ,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_technology` (`created_by`),
  KEY `FK_modified_by_technology` (`modified_by`),
  CONSTRAINT `FK_created_by_technology` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_technology` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `expertise_level`
--
DROP TABLE IF EXISTS `expertise_level`;
CREATE TABLE `expertise_level` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expertise` varchar(45) NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_skills`
--

DROP TABLE IF EXISTS `employee_skills`;
CREATE TABLE `employee_skills` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `id_technology` int(10) unsigned NOT NULL,
  `id_expertise_level` int(10) unsigned NOT NULL,
  `certified` BOOLEAN,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_skills_1` (`employee_pk`),
  KEY `FK_created_by_empskil` (`created_by`),
  KEY `FK_modified_by_empskil` (`modified_by`),
  KEY `FK_technology_empskil` (`id_technology`),
  KEY `FK_expertise_level_empskil` (`id_expertise_level`),
  CONSTRAINT `FK_technology_empskil` FOREIGN KEY (`id_technology`) REFERENCES `technology` (`pk`),
  CONSTRAINT `FK_expertise_level_empskil` FOREIGN KEY (`id_expertise_level`) REFERENCES `expertise_level` (`pk`),
  CONSTRAINT `FK_created_by_empskil` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_empskil` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_employee_skills_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_generic` int(10) unsigned NOT NULL,
  `notification_type` varchar(45) NOT NULL,
  `notification_to` int(10) unsigned NOT NULL, -- Shall we maintain Mailids or Employee Ids?
  `notification_status` varchar(45) NOT NULL,
  `comments` varchar(256),
  `flag` BOOLEAN default false NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `dt_modified` datetime NOT NULL,
  `modified_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_notification_to_notify` (`notification_to`),
  KEY `FK_created_by_notify` (`created_by`),
  KEY `FK_modified_by_notify` (`modified_by`),
  CONSTRAINT `FK_notification_to_notify` FOREIGN KEY (`notification_to`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_created_by_notify` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`),
  CONSTRAINT `FK_modified_by_notify` FOREIGN KEY (`modified_by`) REFERENCES `employee` (`pk`)
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `login track`
--

DROP TABLE IF EXISTS `login_track`;
CREATE TABLE `login_track` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `user_agent` varchar(45) NOT NULL,
  `system_ip` varchar(45) NOT NULL,
  `login_time` datetime NOT NULL,
  `active` BOOLEAN default true NOT NULL,
  `dt_created` datetime NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_created_by_lt` (`created_by`),
  CONSTRAINT `FK_created_by_lt` FOREIGN KEY (`created_by`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- VIEW for Employee tale
create or replace view perficient.vw_employee_supervisor as
select e.*, s.employee_id as sup_employee_id, s.firstname as sup_firstname, s.lastname as sup_lastname 
from perficient.employee e 
inner join perficient.employee s on s.pk = e.supervisor;