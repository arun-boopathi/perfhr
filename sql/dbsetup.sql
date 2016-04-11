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
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Definition of table `projects`
--

DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Project_name` varchar(45) NOT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `joindate` varchar(45) DEFAULT NULL,
  `superviser` int(10) unsigned NOT NULL,
  `department` varchar(45) DEFAULT NULL,
  `billable` int(1) unsigned DEFAULT NULL,
  `skills` varchar(255) DEFAULT NULL,
  `last_working_date` date DEFAULT NULL,
  `sbu` varchar(45) DEFAULT NULL,
  `dt_created` datetime DEFAULT NULL,
  `dt_modified` datetime DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `city` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_1` (`designation`),
  KEY `FK_employee_2` (`superviser`),
  CONSTRAINT `FK_employee_1` FOREIGN KEY (`designation`) REFERENCES `designations` (`pk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_employee_2` FOREIGN KEY (`superviser`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_designation_history`
--

DROP TABLE IF EXISTS `employee_designation_history`;
CREATE TABLE `employee_designation_history` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `designation_pk` int(10) unsigned NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_designation_history_1` (`employee_pk`),
  KEY `FK_employee_designation_history_2` (`designation_pk`),
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
  `date` datetime NOT NULL,
  `hours` int(1) unsigned NOT NULL,
  `type` varchar(45) NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_pk` (`employee_pk`),
  CONSTRAINT `employee_leaves_ibfk_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_leaves_maintainance_1` (`employee_pk`),
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
  `dt_created` datetime,
  `dt_modified` datetime,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_login_1` (`employee_pk`),
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
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_pk` (`employee_pk`),
  KEY `project_pk` (`project_pk`),
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
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `employee_pk` (`employee_pk`),
  KEY `role_pk` (`role_pk`),
  CONSTRAINT `employee_roles_ibfk_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`) ON DELETE CASCADE,
  CONSTRAINT `employee_roles_ibfk_2` FOREIGN KEY (`role_pk`) REFERENCES `roles` (`pk`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Definition of table `employee_skills`
--

DROP TABLE IF EXISTS `employee_skills`;
CREATE TABLE `employee_skills` (
  `pk` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee_pk` int(10) unsigned NOT NULL,
  `type` varchar(100) NOT NULL,
  `date` date NOT NULL,
  `version` varchar(10) NOT NULL,
  `dt_created` datetime NOT NULL,
  `dt_modified` datetime NOT NULL,
  PRIMARY KEY (`pk`),
  KEY `FK_employee_skills_1` (`employee_pk`),
  CONSTRAINT `FK_employee_skills_1` FOREIGN KEY (`employee_pk`) REFERENCES `employee` (`pk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO designations (pk, designation) VALUES 
 (1,'Technical Consultant'),
 (2,'Senior Technical Consultant'),
 (3,'Lead Technical Consultant'),
 (4,'Associate Technical Consultant'),
 (5,'Solution Architect'),
 (6,'Senior Solution Architect'),
 (7,'Senior Project Manager'),
 (8,'Technical Architect'),
 (9,'Project Manager');
 
 INSERT INTO employee (pk, employee_id, firstname,lastname,middlename,dob,contact_no,email,nationality,address,pincode,blood_group,pan_card_no,img_src,designation,joindate,superviser,department,billable,skills,last_working_date,sbu,dt_created,dt_modified,gender,city) VALUES 
 (1,'q201','admin','admin',NULL,NULL,NULL,NULL,NULL,'test',NULL,'o+ve','qw2456Az',NULL,1,'4/11/2012',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'male',NULL),
 (2,'PIN_1282','Victa ','Shiny','','1987-05-23','8106808603, 91 9566066526','','','206, Tilsh, Simon Colony Road, Nagercoil- 629 004',0,'B+ve','BXSPS2137N','',2,'Wed Apr 11 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (3,'PIN_1284','Suryakala','Selvaraj','','1988-01-31','9.663397648E9','','','Flat No:G7 ,Prabhavathi Plasma,Hongasandra Main Road,Garvebhavipalya,Bangalore-560068',0,'B+ve','CDPPS9489D','',1,'Wed May 09 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (4,'PIN_1288','Parvez ','Maideen','','1974-09-03','9.094041789E9','','','Plot No:3 Babu nagar, Medavakkam, Chennai - 600100',0,'B+ve','AHMPP1443R','',5,'Fri Jun 01 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (5,'PIN_1290','Manjeet ','Singh','','1984-06-28','9996630033; Alternate No - 8283820248','','','Permanent/Current  Address : \nC\\O Major Malkiat Singh, HNo-476 Babyal, Ambala Cantt\nHaryana – 133005, Phone – 0171-2669857 ; 0999-663-0033 \nPermanent Address 2:\nC\\O Ravinder Singh, H82 – (FF) Residency Green\nGreenwood City ( Opposite Unitech Cyber Park )\nSector-46 , Gurgaon, Haryana-122001\nPhone -0124-4048167 \nPreferred Mail Address : C\\O Dalip Kohli, A-91 Nizamuddin East, New Delhi - 110013',0,'B+ve','BIFPS1724K','',3,'Mon Jun 18 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (6,'PIN_1297','Sumantra','Nandi','','1975-12-05','8147101454, 08028041897','','','Prestige Shantiniketan,Flat - 18097, Whitefield road, Whitefield\nBangalore 560066',0,'O -ve','ABHPN4684P','',6,'Thu Aug 16 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (7,'PIN_1302','Hemang','Pant','','1975-02-05','09871436710, 9791162876, 9884552257','','','92/5, Park road, Lakshman Chowk, Deharadun - 248001',0,'O+ve','AIWPP7180N','',3,'Thu Nov 15 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (8,'PIN_1304','Kirupakaran','Baskaran','','1984-04-21','9.940490818E9','','','Mailing address: Flat No: F2 Plot No: 2D, Arasan Homes, Chakravarthi street, East Tambaram, Chennai - 59',0,'O+ ve','AKJPB0029K','',8,'Mon Dec 17 00:00:00 IST 2012',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (9,'PIN_1307','Rakesh','Kola','','1985-09-05','9665791437, 8939197009','','','Permanent address: Block No: M/18, Unit No: 1, M.K.T, Post Nimpura, Kharagpur, West Bengal - 721304',0,'O+ve','AYKPK5096Q','',1,'Mon Jan 07 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (10,'PIN_1308','Nareshkumar','Sultan','','1985-08-16','8056162530, 908724988, 9985118860, 7675815105','','','Q.No: SD 35, Naspur Colony, C.C.C Mancherial, Adilabad District, A.P - 504302',0,'B+ve','CBGPS6219C','',1,'Mon Jan 07 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (11,'PIN_1310','Muralikrishna','Ganni','','1982-07-20','9158530558, 09730377666','','','70/15/21, Suresh Nagar, East Godavari district, Kakinada, AP',0,'AB -ve','AMBPG5562A','',2,'Mon Jan 21 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (12,'PIN_1311','Gopi Krishna Reddi','Palli','','1978-08-13','9885735735, 9600017769','','','6-64, REDDY STREET, AKIVIDU, WEST GODAVARI DT, 534235, ANDHRA PRADESH, INDIA.',0,'B+ve','APYPP6908F','',1,'Tue Jan 29 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (13,'PIN_1312','Kasim','Veluturla','','1984-10-06','8600925369, 09677139512','','','S/O MEERAVALI,H.No:9-557,INDRA COLONY, KANIGIRI - 523230, PRAKASAM (DT), A.P',0,'A+ve','AKQPV8146A','',1,'Mon Feb 04 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (14,'PIN_1315','Sivaraman','Baskaran','','1987-07-17','9895478578, 9884545576','','','10-11 Periyar Street, Flat-G3 Regal Square, Gandhi Nagar, Dhasarathapuram, Saligramam, Chennai - 600093',0,'AB +ve','CNLPS5897R','',2,'Wed Feb 20 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (15,'PIN_1319','Rozario','Rajan','','1974-07-19','9.962081278E9','','','S238, Koil st,  Elathagiri P.O,  Krishnagiri (T.K) & (D.T)- 635108',0,'O+ve','ALCPR8782R','',3,'Mon Apr 15 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (16,'PIN_1320','Saraswathy Kalyani ','Elango','','1985-06-24','7.401170004E9','','','No: 66, Agasthiar Street, East Tambaram, Chennai - 59',0,'B+ve','BGUPS1487C','',2,'Thu Apr 25 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (17,'PIN_1321','Sivaparamesh','Ravindran Parameswaran','','1983-02-23','9.88407884E9','','','F-98 Door no - 4, 4th street, Anna Nagar East, Chennai- 600102',0,'O+ve','ANBPP5350N','',7,'Wed May 15 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (18,'PIN_1323','Rekha','Kandasamy','','1983-06-29','9.176865917E9','','','Door No: 5, Plot No: 59, \"Priya Illam\", 1st Floor,\nVijayaganapathy Nagar- 1st Street, Krishna Nagar, Ullagaram, Chennai - 91',0,'A1+ve','ANCPR8269F','',2,'Mon Jun 10 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (19,'PIN_1325','Abdul Hakeem','Mohammed ','','1981-03-07','9.543010569E9','','','No:12, C-Block, F-5, Singara Garden, 1st street, Errukanchery, Chennai - 118',0,'A+ve','AJFPA5503N','',3,'Fri Jun 21 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (20,'PIN_1326','Sowmya ','Jegannathan','','1981-07-08','7.708220333E9','','','G-2, Sri Narayanas Apts, 2nd St, IIT Colony, Narayanapuram, Pallikarnai, Chennai - 600100',0,'A1+ve','ASIPS3836N','',3,'Mon Jun 24 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (21,'PIN_1332','Kundan ','Maheshwar Singh','','1979-06-24','9.888895452E9','','','c/o Sangeeta Verma, 1706/830, Street Number 26, Preet Nagar, New Shimlapuri, Ludhiana – 141003',0,'O+ve','ARKPK8097Q','',8,'Mon Jun 24 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (22,'PIN_1333','Johnshi','Panneerselvam','','1988-01-26','8.93932805E9','','','No: 26, East Street, Melakuppam, Vriddhachalam Taluk, Cuddalore - 607802',0,'B+ ve','ANQPJ2207H','',4,'Mon Jun 24 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (23,'PIN_1339','Venkatesan','Jayaraman','','1990-06-25','9.865645669E9','','','Flat No:1, Sivasakthi Nagar, 1st cross street, Kolathur, Chennai - 99',0,'A1+ve','ANPPV6976N','',4,'Fri Jul 05 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (24,'PIN_1344','Shankar Ram','Ramamoorthy','','1986-04-14','9.884284874E9','','','Door No: 50 A, Ramaswamy Avenue, Sastri Nagar, Adayar, Chennai - 20',0,'O+ve','BQKPS9719K','',1,'Wed Jul 10 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (25,'PIN_1346','Vishnu Prasath','Pandikrishnan','','1983-07-10','9.962090093E9','','','35 JD Enclave, 1st floor A1, VGP Prabhu Nagar, 1st street, Permbakkam, Chennai - 600 100 ',0,'O+ ve','AGQPV8568G','',1,'Fri Jul 12 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (26,'PIN_1348','Noelpriya','Dhanapal','','1988-12-22','9.994255041E9','','','1-1/148A, Indira Nagar, Mohan Nagar, Salem Steel plant, Salem - 636030',0,'O+ve','AKJPN1927C','',1,'Fri Jul 12 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (27,'PIN_1349','SenthilKumar ','Shanmugam','','1973-05-10','9.500052304E9','','','No: 48, O-Block, Ganapathy Colony, Anna Nagar East, Chennai - 600102',0,'O+ve','AOPPS8474K','',5,'Mon Jul 15 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (28,'PIN_1351','Krithika ','Ganapathy Sundaram','','1987-10-30','9.941451765E9','','','New No:4, Old No:7, 8th Street, Nandanam extension, Chennai - 600 035',0,'O+ve','BJHPK5793H','',1,'Mon Jul 15 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (29,'PIN_1352','Prabaharan','Periyasamy','','1982-01-03','9715312928, 9500098854','','','2/12, Sammandhapuram, Villakkethi Post, Erode - 638 109',0,'O+ve','APFPP2643N','',3,'Wed Jul 17 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (30,'PIN_1353','Sudhakar','Sabapathy','','1976-06-11','9.09402084E9','','','28/48, Elango street, R- Block, MMDA, Arumbakkam, Chennai - 106',0,'O+ve','AYZPS0408D','',3,'Fri Jul 19 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (31,'PIN_1354','Harshavardhini ','Ramachandran','','1976-11-12','9.176647773E9','','','#.7, Velayudha Raja Street, Mandavelli, Chennai – 600028',0,'O +ve','ACDPH0122P','',7,'Mon Jul 22 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (32,'PIN_1361','Saviya shree ','Krishnamoorthy','','1989-10-14','9.994188389E9','','','10, Nagaluthu street, Kanchipuram - 631 501',0,'B+ ve','DRJPS0341P','',4,'Mon Aug 05 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (33,'PIN_1362','Preethi','Dhakshinamoorthy','','1989-06-12','9884803602, 8056158909','','','No: 10, 1st street, Nermai Nagar, Kolathur, Chennai - 99',0,'AB +ve','BBKPP1570L','',4,'Mon Aug 12 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (34,'PIN_1363','Srivathi Saranya ','Shanthakumar','','1989-02-11','9.600106319E9','','','No:11, GMM Street, thousand lights, Chennai -06',0,'B+ ve','CPIPS9047G','',1,'Wed Aug 14 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (35,'PIN_1364','Nitin ','Jain','','1982-09-30','9024112355, 9928285410, 9591541061','','','34, Adarsh Nagar Pali, Rajasthan - 306401; \nCurrent address: 201, Mahaveer Square Apt.\nKodichikkanahalli, Bangalore – 560076',0,'B +ve','AGOPJ7595H','',8,'Mon Aug 19 00:00:00 IST 2013',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (36,'PIN_1370','Manivel','Jaganathan','','1967-06-30','8220678507, 9566125296','','','Flat # 16, Rajeswari Apartments, 58, Warren Road, Mylapore,Chennai, 600004\nPermanent Address: 14, Jothi Nagar,Kondur (Post),Cuddalore, 600006',0,'A+ve','ALBPM0266A','',5,'Thu Jan 02 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (37,'PIN_1372','Mani Kandan','Vasudevan','','1989-04-01','9.789898356E9','','','Paul Illam, No: 3, Muthukaruppan Street, Pasumpon Nagar, Pammal, Chennai - 75',0,'B-ve','BNDPM6002M','',1,'Mon Feb 17 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (38,'PIN_1374','Chippy','Gopan','','1981-01-10','9.962942461E9','','','C6, Grassland Apartments, Mount Punnamalle Road, Mugalivakkam, PORUR\nPermanent Address: Kavungal Madhom, Devagiri P.O, Kangazha, Kottayam, Kerala',0,'A+ve','AJXPG1680J','',3,'Mon May 19 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (39,'15859.0','Aruna','Thiruvengadam','','1991-01-25','9176266727, 9884523528 (sisiter number)','','','Permanent Address: Thirumani village and Post, Vengiliar Thoppu, Latheri via, Katpadi TK, Vellore - 632202\nCurrent Address: S1, 2nd floor, No. 392/A, P.H.Road, Sandore Nagar (Opp to Anna Arch), Arumbakkam, Chennai - 600 106',0,'A+ ve','BNBPA5118E','',4,'Mon Jun 09 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (40,'15860.0','Dhivyamanohary','Ramakrishnan','','1985-11-07','9884703431, EMERGENCY: 9884323431','','','Permanent Address: K8, Swathi Towers, 2 and 3 Durgabhai Deshmukh Road, R.A.Puram, Chennai - 28',0,'O+ve','ALLPD0175H','',1,'Wed Jun 18 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (41,'15861.0','Ajan Srinivas','Rengarajan','','1989-10-07','9566230471, Emergency: 044-28443429','','','Permanent Address: Old No: 112, New No: 24/11, T.Pkoil street, Triplicane, Chennai - 600 005\nCurrent Address: Old No: 120, New No: 6, T.Pkoil street, Triplicane, Chennai - 600 005',0,'A+ve','CPBPS6088A','',1,'Thu Jun 12 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (42,'15862.0','Venkata Siva','Dasari','','1978-03-24','9.962558999E9','','','',0,'','AHAPD8014C','',3,'Wed Jun 25 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (43,'PINI_0002','Adeena','Ashokan','','1991-02-19','9940021807, Emergency Number: 044-26449697','','','Permanent Address: No: 332, 10th Cross street, TP Chathiram, Kilpauk, Chennai - 10',0,'A1+ ve','BJGPA1893R','',4,'Mon Jun 30 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (44,'15863.0','Vidya Teja','Guddati','','1990-11-04','9052034644, Emergency No: 9291251818','','','Permanent Address: Plot No: 156, Sector 5, MVP Colony, Vishakapattinam - 17\nCurrent Address: C5H, CEE DEE YES apartments, Velachery, Chennai',0,'O +ve','BMTPG9723N','',4,'Mon Jun 30 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (45,'15864.0','Deepa','Nair','','1989-01-26','9176629810, Emergency No: 9176679810','','','Permanent Address: TC-37 / 1766, Kottayaril Veedu, Fort PO, Trivandrum - 23\nCurrent Address: Plot # 1, Survey # 63/4, 65 - Sahaj Enclave, Phase iv, Krishnaveni Nagar, Mugalivakkam, Chennai - 600 116',0,'O+ ve','AKTPN2718B','',1,'Mon Jun 30 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (46,'15865.0','Gayathri','Narayanan','','1980-12-10','9445708391; Emergency Contact: 9444026396','','','Permanent Address: F1; Venkatraman Apartments, A.P.Kovil 1st cross street, Pallikaranai, Chennai - 100',0,'O +ve','AEBPN0359J','',2,'Wed Jul 02 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (47,'15866.0','Ranjith','Rajendran','','1987-12-02','0.0','','','410/E, Malar colony, 19th Main road, Anna nagar west, Chennai- 600 040',0,'A1 +ve','AXNPR9402K','',1,'Wed Jul 23 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (48,'16490.0','Padma deepika','Narayanaswamy','','1990-05-06','8807194443 Emergency Number : 9944198770','','','No 75, Jeevan Nagar, 1st street, Adambakkam, Chennai 600 088',0,'B+ve','BMYPT3488A','',4,'Mon Sep 01 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (49,'16510.0','Neelakantan','Seshagirinathan','','1993-07-17','9940318086  Emergency Contact: same','','','35B, C COLONY, SAI KRISHNA NIVAS APTS, SOUTH WATER TANK STREET, PERUMALPURAM, TIRUNALVELI-627007',0,'O+ ve','AWHPN4431B','',4,'Wed Sep 03 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (50,'16509.0','Madhumita','Majumdar','','1991-07-14','9884685234  Emergency Contact: 9042419101','','','UEB, MTTI Airforce station, Avadi, chennai- 600055.',0,'AB+ve','BTQPM3053H','',4,'Wed Sep 03 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (51,'16549.0','Praveen ','Muthusamy','','1989-09-08','9940635165 Emergency Contact: 9840526346','','','1/10, CDN Nagar, 4th street, Nerkundram, Chennai- 600107',0,'B+ve','BLAPP5493G','',1,'Thu Sep 04 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (52,'16550.0','Kousikan ','Narendran','','1982-01-26','9.840303405E9','','','Address: FR1, 4th Floor, Sai Ishwarya Enclave, 2nd Main road, Telephone Colony, Adambakkam, Chennai - 88',0,'O+ve','ATWPK9268P','',1,'Mon Sep 01 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (53,'17009.0','Saryu','Sukumar','','1989-02-11','8939942210  Emergency Contact: 9444440441','','','No. B3, Cascade, New Giri Road I Street, T Nagar, Chennai - 600017',0,'O+ve','CTGPS2033A','',1,'Wed Sep 24 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (54,'17629.0','Parthiban','Ramakrishnamurthy','','1981-05-14','9361567789 Emergency Contact: 9442901283','','','No.8C, Sri Selva Rathina Vinayagar Avenue, Cart Track road, Chennai-600042',0,'B+ve','AKGPR1923B','',3,'Mon Oct 20 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (55,'17689.0','Balaji','Jaganathan','','1980-05-05','9940030827 Emergency Contact: 9500129386','','','3B, Green peace Orion flats,plot no.2, P.T.Rajan Salai, K.K.Nagar, Chennai-78 /                                                                                                                       No 9, ARS Garden, cutcherry street, Gopi Chettipalyam, Erode—638 452',0,'O+ve','ALCPB6584B','',3,'Mon Oct 20 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (56,'17690.0','Abhilash','Ranga','','1988-06-15','9666353333 Emergency Contact:','','','8-4-678, Hanuman nagar, Karim nagar, Telugana – 505 001',0,'B+ve','BHJPA1063J','',1,'Mon Oct 20 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (57,'17810.0','Madhankumar','Santhanam','','1985-08-16','9994092222 Emergency Contact: 9790880949','','','11/14, Mathangi Apartments, Brindhavan Street Extn., West Mamnalam, Chennai-600033',0,'A1-ve','APOPM3346J','',2,'Mon Oct 27 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (58,'17869.0','Ramya','Mani','','1991-10-09','9176371653 Emergency contact: 7871172772','','','No.26 D, SBM school street, Kollathur, Chennai-600099',0,'A1+ve','BXDPR4537C','',4,'Thu Oct 30 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (59,'17909.0','Sangeetha','Kesavan','','1987-07-27','8056902030 Emergency contact: 9842693426','','','2/1, Mahalakshmi gardens, Pasupathipalayam po, Gandhi gramam south, Karur, 639004',0,'O+ve','BMTPS4047M','',2,'Mon Nov 03 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (60,'17910.0','Senthil Kumar ','Ellappan','','1986-05-10','9600043266 Emergency contact: 044- 25651969','','','19/33, Kamarj street, new Lakshmipuram, Chennai-99',0,'O-ve','CMRPS9179K','',3,'Mon Nov 03 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (61,'18032.0','Kavitha ','Shanmugam','','1979-08-15','9962529035 Emergency contact: 9884530221','','','132, Marunteeswarar Nagar, Thiruvanmiyur, Chennai- 600 041',0,'AB+ve','AWSPK3311Q','',1,'Thu Nov 13 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (62,'18082.0','Athaur Rahman','Gudiyatham Salt','','1986-01-14','9790964280 Emergency contact: 9841057280','','','NO.14/16, 2nd floor, MV Badran street, Periyamet, Chennai-600003.                                                                                                                                                                                                                         No 13, Fakir Mohammed Kulam street, Arcot, Vellore- 632 503',0,'AB+ve','ALQPG4123k','',3,'Mon Nov 17 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (63,'18172.0','Parthiban','Thangaraj','','1991-04-26','9840208450 Emergency contact: 9445299142','','','No 391, 41st street, Shankar nagar, Bammal, Chennai -600 075',0,'B+ve','BZEPP2925D','',4,'Mon Nov 24 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (64,'18245.0','Girish Kumar','Pallela','','1989-01-25','8297030987 Emergency contact:','','','2-36/4, Aganampudi, gajuwaka, Visakapatnam – 530 046',0,'AB-ve','BGEPP6049M','',1,'Mon Dec 01 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (65,'18289.0','Siva kumar Reddy ','Velagala','','1980-04-14','9500070040 Emergency contact: 9789024345','','','12-2-420/4, Alapati Nagar, Mehdipatnam, Hyderabad 500 028',0,'A+ve','AEQPV7675C','',8,'Mon Dec 08 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (66,'18352.0','Daniel vivek ','Raman','','1983-04-30','9840799691 Emergency contact: 9159282828','','','3/255, Thinniyoor village, Aravenu Post 643 201, Nilgiris',0,'B+ve','AINPR9817M','',1,'Mon Dec 15 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (67,'18350.0','Monisha','Adhiyanur Ramadoss','','1988-10-07','9994692769 Emergency contact: 9894835329','','','No22, 3rd street, Sakthi Nagar, Chengalpet 603 001',0,'O+ve','ARSPM8641A','',1,'Mon Dec 15 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (68,'18351.0','Bhavatharini','Muthuvijayan','','1988-05-17','7667667368 Emergency contact: 9500022292','','','No. 6, Flat G6, Kamakotti Apartments, 3rd Cross Street, 1st Main Road, New Colony, Chrompet, Chennai-600044',0,'B+ve','BBJPB2291P','',1,'Mon Dec 15 00:00:00 IST 2014',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (69,'18956.0','Dhanalakshmi','Chandrasekar','','1986-07-18','8754462961; Emergency Number: 8754462951','','','Address: No: 12/1, Abhirami Nagar, Villivakkam, Chennai - 49',0,'','AKZPC3510A','',1,'Fri Jan 02 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (70,'18850.0','Yogasri','Rajkumar','','1992-12-24','9789060050 Emergency contact: 9840239900','','','B 9, PWD quarters, TOD hunter nagar,  Saidhapet, Chennai 600 015',0,'AB+ve','AJQPY6000M','',4,'Mon Jan 05 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (71,'18851.0','Aneesh','Seshadri','','1983-10-08',' Emergency contact: 044-24487963','','','DF3, SVS Anandha nilayam apartments, Ramamurthy Nagar, Bangalore- 560 016',0,'A1+ve','AHQPA9554Q','',3,'Mon Jan 05 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (72,'18871.0','Arthika','Chandrasekar','','1991-04-23','8973849849 Emergency contact: 8526575929','','',' 296, Vasantha Nagar, Paper Mill Road, Pallipalayam, Erode- 630 008',0,'O+ve ','BKZPA3467P','',4,'Wed Jan 07 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (73,'18852.0','Suganya','Venkatachalam','','1989-10-12','9944776924 Emergency contact: 9443423068','','','B623, Bhel Township, Ranipet – 632 406',0,'O+ve','DHWPS7363L','',1,'Mon Jan 19 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (74,'19010.0','Lavanya ','Venkatachalapathy','','1989-07-25','9940063130 Emergency contact: 9962666818','','','5/13, SouthMada Street, Kovur, Chennai -600 122',0,'B+ve','AGAPL6915F','',1,'Thu Jan 22 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (75,'19011.0','Santhosh','Anandan','','1977-03-28','9791777040 Emergency contact: 044 26261623','','','S Block, Old No 73, New no 2, 18th street, Anna nagar, Chennai – 600 040',0,'B+ve',' BUXPS7297F','',3,'Fri Jan 23 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (76,'19070.0','Sumalatha','Poolavari','','1985-07-16','8056166204 Emergency contact: ','','','kapupalli village, Kurmoi Post, Palamaner Mandal, Chittoor district, AP 517 408',0,'O+ve','BGZPP8985P','',1,'Thu Jan 29 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (77,'19230.0','Balasubramaniam','Muthaiah','','1983-11-12','9790949557 Emergency contact: 9790705930','','','32c, Veerabagu nagar pettai, Tirunelveli – 627 004',0,'B+ve','AKSPB7505R','',8,'Mon Feb 16 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (78,'19270.0','Manju','Kumaravel','','1992-10-21','8754334199 Emergency contact: 9363036244','','','4/121, Mchettipaliyam, Mangalam road, Iduvai Post..Thirupur…641 687',0,'O+ve',' BVYPM0670E','',4,'Mon Feb 23 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (79,'19271.0','Shankar','Menon','','1989-08-09','99624355838 Emergency contact: 7299042771','','','Suresam F7, Shabari garden, Thamarakulangara road, Nadama Thrippunithura, cochin -682301.',0,'B+ve','BBBPM8548L','',1,'Mon Feb 23 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (80,'19290.0','Gorumuchu Shoban ','Babu','','1991-03-19','9884742161 Emergency contact: 9176253993','','','No.6, Amudham Nagar, 3rd Street, Chinna Kodungaiyur, Chennai - 600118',0,'A1-ve','AYHTG5761H','',4,'Wed Feb 25 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (81,'19388.0','Venkata Lakshman Kumar ','Konagalla','','1989-08-09','95515 05105','','','12-24-33, Sai lakshmi apartments, GF 1, Gadhiyaram vari street, Kothapet, Guntur 522 001',0,'B +ve','CBWPK1723P','',1,'Mon Mar 09 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (82,'19467.0','Rajasekar','Thirugnanam','','1989-01-08','9.902415991E9','','','24B, Type 2 Quarters, Block 6, Neyvelli – 607803',0,'B+ve','AWZPR6429H','',1,'Thu Mar 12 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (83,'19468.0','Santha Kumar','Nammalavar','','1987-05-10','9.643581986E9','','','5/538, Thiruvalluvar street, Anna nagar, Madurai 625 020',0,'O+ve','CRIPS5197M','',1,'Thu Mar 12 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (84,'19668.0','Sujitha Sai','Selvaraju','','1988-06-09','9.962078727E9','','','4, Golden villa, 3rd floor, Balaji nagar, 2nd street, Royapettah, Chennai -600 014',0,'B +ve','BWXPF3161E','',1,'Mon Mar 30 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (85,'19669.0','Naveen','Sridharan','','1991-04-04','9.952090225E9','','','No 3/937, F3, Iyappa flats, Bajanai Kovil street, Madhanandapuram, Porur, Chennai 600 125',0,'A+ ve','AJDPN0509P','',1,'Mon Mar 30 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (86,'19748.0','Dinesh Kumar','Balu','','1987-06-10','8.608264143E9','','','14/12/13, Melamandhai street, Batlagundu, Dindukal 624 202',0,'B +ve','AQTPD3886A','',1,'Thu Apr 09 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (87,'19767.0','Sarmistha ','Panda','','1986-05-10','7.504539085E9','','','Plot no EA180, BDA Colony, Stage 2, Lakshmi sagar, Bhuvaneshwar 751 006',0,'B +ve','BAJPP6919H','',1,'Mon Apr 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (88,'19787.0','Gaddala','Crispy Salonika','','1991-02-11','7.845264659E9','','','6-8-10/A, Srungarapuram, CPZ church, bapatla, Guntoor district- 522 101',0,'B +ve','BEFPG7526H','',1,'Tue Apr 14 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (89,'19818.0','Ragupathy','Selvaraj','','1983-01-01','8.884294499E9','','','2/57, TP Palayam post, Rasipuram, Namakal - 637 406',0,'A1 +ve','AJMPR0207R','',3,'Mon Apr 20 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (90,'19947.0','Surendhar','Palani','','1989-09-14','9.940313677E9','','','44/27, Reddy kuppam street, Temple tower apartment, Saidhapet, Chennai-600 015',0,'A1+ve','DGOPS5893G','',1,'Mon May 04 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (91,'19948.0','Vishnupriya','Palani','','1993-03-01','9.940274008E9','','','44/27, Reddy kuppam street, Temple tower apartment, Saidhapet, Chennai-600 015',0,'A1+ve','AQLPV4239K','',4,'Mon May 04 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (92,'20046.0','Dilip','Raghavan','','1984-07-27','9.445973349E9','','','A-2, Alsa Regency 16 Eldams road, Chennai – 600 018',0,'O +ve','AIZPR4137N','',1,'Wed May 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (93,'20047.0','Bagieswari','Umapathy','','1981-10-05','9.962011812E9','','','45, SRP colony, 7th Street, Jawahar nagar, Chennai -600 082',0,'O +ve','AAPPU9174J','',2,'Wed May 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (94,'20066.0','Venkatraman','Janarthanan','','1983-05-15','7829242777 / 9566288295','','','No 1, Rajagopalan street, G2, Kaverimalligai flats, kadaperi, Tambaram, chennai-600 45',0,'O +ve','AFHPV5996C','',9,'Thu May 14 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (95,'20107.0','Ruchi','Rani','','1991-11-01','7.838566624E9','','','Near Chhota Gurudwara, Karnali gate, Taraori, Karnal, Harayana- 132 116',0,'O +ve','BEOPR2000A','',1,'Mon May 18 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (96,'20188.0','Siddarth','Ramakumar','','1989-12-30','9.789092491E9','','','No4, 4th street, Padmanaba nagar, Adyar, chennai 600 020',0,'O +ve','CRIPS9019L','',1,'Thu May 28 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (97,'20466.0','Jayanth','Kaliappan','','1985-01-20','9.962008974E9','','','22W/4, Kamarajar street, Kulalarpalayan, Bodi nayakanur 625 513',0,'O+ve','AIBPJ8173A','',2,'Mon Jun 15 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (98,'20526.0','Durai','Perugopanapalli Narasimhan','','1988-07-04','9003153547; Emergency Number: 9790793464','','','Old No. 3, New No. 12, Saraswathy Nagar, 7th Street, AGS Colony, Adambakkam, Chennai - 600088',0,'B+ve','AKJPN9523G','',1,'Thu Jun 18 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (99,'20597.0','Saurabh','Kumar','','1993-06-11','9.962879929E9','','','No.5/11, Old No. 3/11, Swetamber Apts, Bazullah Road, T Nagar, Chennai – 600017',0,'','DMHPS5941G','',4,'Wed Jun 24 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (100,'20687.0','Chinthala','Raghavendra Nivas','','1992-05-15','9.032236881E9','','','No.8-614, Kothapetta, SriKalahasti, Andhra Pradesh - 517644',0,'B+','ATSPC7503R','',4,'Wed Jul 01 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (101,'20688.0','Adityen','Krishnan','','1993-10-05','9.940265984E9','','','F2, B Block, Narayanathri Appartments, 112-A, Choolaimedu High Road, Choolaimedu, Chennai – 600094',0,'O+','BIRPA9436G','',4,'Wed Jul 01 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (102,'20726.0','Parthiban','Balraj','','1989-07-18','9.790408494E9','','','1/22, SS Koil Street, Melachindamani , Trichy - 620002',0,'O+ve','BQPPP8384J','',1,'Mon Jul 06 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (103,'20727.0','Jeyanth Raja ','Selvan','','1990-09-14','9.788241307E9','','','35/21, Bharathiyar Street, Tiruchendhur, Tuticorin District- 628216',0,'O +ve','AQAPJ0175B','',1,'Mon Jul 06 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (104,'20728.0','Jaspreet','Kaur','','1990-10-17','9.840391963E9','','','No C - 43 , 1st floor, Fatehnagar, Jail Road, New Delhi - 110018',0,'B+ve','CKCPK4390K','',4,'Mon Jul 06 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (105,'20778.0','Vignesh','Vijayarajan','','1990-06-26','9.600540125E9','','','No.176, Class B, 9th Cross Street, Gandhi Nagar, Virupachipuram, Vellore – 632001.\n651, 5th main road, ram nagar south, Madipakkam, Chennai - 600091',0,'','ANTPV9176A','',1,'Fri Jul 10 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (106,'20831.0','Sneha','Jain','','1990-10-26','9.791186239E9','','','42, Pana street, Purusawakam, Chennai- 600 007',0,'B+ve','AWRPJ8034G','',1,'Mon Jul 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (107,'20832.0','Thariq','Mahmood','','1983-03-12','8.939548239E9','','','AP -721, Ground floor, H block, 12th main road, Anna nagar west, Chennai 600040',0,'A+ ve','AFLPT5321B ','',3,'Mon Jul 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (108,'20833.0','Nalini','Balakrishnamurthy','','1984-05-16','9.962012411E9','','','No.33/88A, 15th cross street, Senthil Nagar, Kolathur, Chennai -99',0,'O -ve','ALHPB1872B','',1,'Mon Jul 13 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (109,'21247.0','Subramanian','Nachiappan','','1987-04-15','9.840454376E9','','','No 50, 85th Street, Ashok Nagar, Chennai -600 083',0,'A1 +ve','DBWPS3085R','',1,'Mon Aug 10 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (110,'21248.0','Nikunj','Dilip Bokadia','','1991-07-21','9.791192196E9','','','No.5, Perumal 2nd Street, Purasaivakkam , Chennai-6000 09',0,'O+ve','BLNPB0486N','',4,'Mon Aug 10 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (111,'21646.0','Sowmya ','Krishnan','','1990-04-27','9.840598313E9','','','N0:2, AG4, Sri Hari Hara Aprtments, K.K.Nagar, 2nd street, Thirumullaivoyal, Chennai - 62',0,'O+ve','BYBPP4614H','',4,'Mon Aug 31 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (112,'16649.0','Sanjay Keerthi','Mani','','1990-09-13','9.566199367E9','','','Address: no.25B, IIT Colony, Narayanapuram, Pallikaranai, chennai - 600100    ',0,'A-ve','CIQPK0427F','',4,'Fri Sep 11 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (113,'22416.0','Senthil Kumar ','Sundara Vinayagam','','1976-10-01','9.841404212E9','','','Plot No: 10 A, 1st street, Thirumalai Nagar, Madambakkam, Chennai - 126',0,'A1B+ve','AWEPS0342M','',3,'Mon Sep 21 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (114,'22217.0','Prabakar','Murali','','1988-08-01','9.943786369E9','','','Current Address: 72/47, Alandur Road, Saidapet, Chennai -15\nPermanent Address: 8/30, Dabeer New street, Kumbakonam, Tamilnadu - 612001',0,'B+ve','CMJPM0420R','',4,'Wed Sep 23 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (115,'22252.0','Bhagyalakshmi','Mohana Sundaram','','1990-06-29','9.845644377E9','','','N0:13, Kuill Pattu street, Sree Sakthi Nagar, Annanur, Chennai - 109',0,'A+ve','BBFPB9372K','',1,'Mon Sep 28 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (116,'22352.0','Usha Deepthi','Mogillapalli','','1989-06-19','8.015319455E9','','','Flat no 207, Pulipati apartments, Ratham Bazaar, Nandigama, Krishna district, Andra Pradesh Pin code: 521185',0,'B+ve','BCGPM7747F','',2,'Thu Oct 01 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (117,'22472.0','Arthy','Sivasamy','','1990-05-15','9.994747333E9','','','Current Address: Blooming ladies hostel, R.K.Complex, Madipakkam, Chennai\nPermanent Address: Plot:33, EAST, E-Block, MVM Nagar, Dindigual, Tamilnadu',0,'O+ve','','',1,'Tue Oct 06 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (118,'22552.0','Srinivasan','Ramasamy','','1989-03-19','9.994061071E9','','','Permanent Address: No:156, Pechiamman Padithurai road, Madurai-625001\nCurrent Address: 16/23, 2nd street, SBA Officers colony, Poonamallee High road, Arumbakkam, Chennnai - 106',0,'A1+ve','CNWPS0561D','',1,'Mon Oct 12 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (119,'22553.0','Hemanth Kumar','Sanjeevi Raman','','1991-06-15','9.003091765E9','','','2/145, 3rd Main road, Raghava Nagar, Moovarasampet, Chennai - 600091',0,'B+ve','AGSPH3609L','',1,'Mon Oct 12 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (120,'22673.0','Senthamizhan','Senthamarai','','1987-05-23','9.44511982E9','','','',0,'','','',2,'Wed Oct 14 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (121,'22674.0','Dinesh ','Adhinarayanan','','1987-05-23','9.840025072E9','','','',0,'','','',1,'Thu Oct 15 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (122,'22675.0','Sandeep','Padmanabha','','1985-10-07','0.0','','','S/o K Padmanabha Reddy, Agaramangalam, Chittoor (Dist), Andhra Pradesh – 517167',0,'','BIMPS3852F','',3,'Thu Oct 15 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (123,'23552.0','Sundararajan','Neelakandan','','1971-07-11','97909 58322 Emergency contact: 8754540465','','','AF2, Sakthi Flats, No 10, Nagappan street, Jafferkhanpet, Chennai-600 083 ',0,'O+ve','ADLPN3181B','',3,'Sun Nov 01 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (124,'23192.0','Ramya','Jayakrishnan','','1990-07-11','8.220249877E9','','','PA14, Tarangini Complex, Mogappair West, Ambattur Estate Post, Chennai – 600058',0,'O +ve','BHSPR3605J','',1,'Mon Nov 02 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (125,'23193.0','Prasanth ','Gunasekaran','','1990-08-03','9.952216946E9','','','Permanent Address: 3-24/3-34, Bazaar Street, Yethapur, Attur, Salem – 636117\nCurrent Address: Blossom Apartment, Plot No: 184/A, 9th main road, 1st Link, Ram Nagar, Madipakkam, Chennai - 91',0,'B+ve','BSNPP8540C','',1,'Mon Nov 02 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (126,'23312.0','Prabhu','Mohanarangam','','1990-01-16','9.840345377E9','','','No:12A, 28th Avenue, Narmada Flats, Banu Nagar, Pudur Ambattur, Chennai -600053',0,'O+ ve','BLCPP8254K','',1,'Thu Nov 05 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (127,'23872.0','Roja Roshini','Krishnamurthy','','1991-11-06','8.344279348E9','','','Permanent Address: No: 13/44, Venkatachalapathy layout, street 2, poochiyur via, nsn palayam, Coimbatore-641031.\nCurrent Address: No:4, Block D, Vajra Apartments, Village high road, Backside of Infosys, Sholinganallur, Chennai - 119',0,'O+ve','BIAPR8256E','',1,'Tue Nov 17 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (128,'24032.0','Faisul Ihshan','Ajmalkhan','','1989-12-29','9.0031327E9','','','No: 37/A, Elumalai street, Ayanavaram, Chennai- 600023',0,'A+ve','ACAPF6624R','',1,'Mon Nov 30 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (129,'24033.0','Ramalakshmi','Venugopalakrishnan','','1994-06-05','7.708463391E9','','','No:1/43 North Street, Keelaambur, Ambasamudram Taluk, Tirunelveli district- 627418',0,'A+ve','','',4,'Mon Nov 30 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (130,'24552.0','Dinesh','Venkatasubramanian','','1990-09-14','9.840051227E9','','','No: 378, S1, Gurubalaji Apartments, Medavakkam Main Road, Puzhudhivakkam, Chennai - 600091',0,'A2B -ve','BJGPD0406F','',1,'Wed Dec 16 00:00:00 IST 2015',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (131,'24832.0','Rahul','Vangara','','1988-12-26','9.884441388E9','','','No:8, Flat E, Ananda nilayam Street, Nehru Nagar, Chrompet, Chennai – 600 044',0,'A1 +ve','ALDPV8225E','',2,'Mon Jan 04 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (132,'25072.0','Vigneshpandi','Marimuthu','','1994-07-27','8883879920 / 9024209557','','','No.24, Iruthayarajya Puram, North 3rd Street, Sellur, Madurai, Tamilnadu – 625 002',0,'A1+ve','AWHPV7132M','',4,'Wed Jan 13 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (133,'25092.0','Naveenprabu','VellodePeriyasamy','','1987-05-20','9.840305565E9','','','No.3, Odakkadu CHML Road, Vellode, Erode District – 638112\nCurrent Address: K-F3 Block, Janani Raghav Enclaves, Chemmencherry, OMR, Chennai - 600 119',0,'A1+ve','AIDPN2817K','',1,'Thu Jan 14 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (134,'25172.0','Sriram','Rajagopalam','','1992-05-19','9.686555136E9','','','No.68, Yasodha Nagar, 5th Street, Keelkatalai, Chennai -600117',0,'O+ve','FBHPS2971D','',1,'Mon Jan 18 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male',''),
 (135,'25272.0','Saranya','Rajeshwaran','','1987-05-10','8.870688075E9','','','F2B, Ist Floor, Sri Bhavani Illam,  Sri Narayana Nagar, Poompuhar Street(south), Vettuvankeni, Chennai -600 041',0,'O+ve','BGFPR0755M','',1,'Wed Jan 20 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Female',''),
 (136,'25273.0','Gnanasambandhan','Angeerasa','','1992-08-18','9.840931131E9','','','5/627, Nehru Street, Senthil Nagar, Otteri, Vandalur, Chennai – 600048',0,'B+ve','BACPG7096R','',1,'Wed Jan 20 00:00:00 IST 2016',1,'',0,'',NULL,'','2016-03-15 00:00:00','2016-03-15 00:00:00','Male','');
 
 insert into employee_login(pk, employee_pk, email_id, pwd, emp_lock) values (1, 1, 'admin', 'admin', 0);