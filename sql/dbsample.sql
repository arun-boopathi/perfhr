USE perficient;

INSERT INTO designations (pk, designation,sbu,dt_created,dt_modified,created_by, modified_by) VALUES 
 (1,'Technical Consultant','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (2,'Senior Technical Consultant','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (3,'Lead Technical Consultant','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (4,'Associate Technical Consultant','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (5,'Solution Architect','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (6,'Senior Solution Architect','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (7,'Senior Project Manager','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (8,'Technical Architect','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (9,'Project Manager','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (10,'INTERN CONSULTING','CHENNAI CONSULTING','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (11,'INTERN ADMIN','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (12,'Operations Assistant','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (13,'Coordinator Talent Acquisition','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (14,'Talent Acquisition Specialist','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (15,'Sr. Talent Acquisition Specialist','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (16,'Talent Acquisition Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (17,'Resource Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (18,'HR Generalist','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (19,'Senior HR Generalist','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (20,'HCM Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (21,'Senior HCM Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (22,'Business Operations Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (23,'Senior Business Operations Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (24,'IT Admin','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (25,'Senior IT Admin','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (26,'General Manager','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1),
 (27,'Director','CHENNAI ADMIN','2016-03-15 00:00:00','2016-03-15 00:00:00',1,1);
 
 
 INSERT INTO `employee` (`pk`,`employee_id`,`firstname`,`lastname`,`middlename`,`dob`,`contact_no`,`email`,`nationality`,`address`,`pincode`,`blood_group`,`pan_card_no`,`img_src`,`designation`,`joindate`,`supervisor`,`department`,`billable`,`skills`,`last_working_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`,`gender`,`city`) VALUES 
 (1,'q201','admin','admin',NULL,NULL,NULL,NULL,NULL,'test',NULL,'o+ve','qw2456Az',NULL,1,'2012-04-11',1,NULL,NULL,NULL,NULL,1,NULL,NULL,1,1,'male',NULL),
 (137,'PIN_1282','Victa ','Shiny','','1987-05-23','8106808603, 91 9566066526','','','206, Tilsh, Simon Colony Road, Nagercoil- 629 004',0,'B+ve','BXSPS2137N','',2,'2012-04-11',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (138,'PIN_1284','Suryakala','Selvaraj','','1988-01-31','9663397648','','','Flat No:G7 ,Prabhavathi Plasma,Hongasandra Main Road,Garvebhavipalya,Bangalore-560068',0,'B+ve','CDPPS9489D','',1,'2012-05-09',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (139,'PIN_1288','Parvez ','Maideen','','1974-09-03','9094041789','','','Plot No:3 Babu nagar, Medavakkam, Chennai - 600100',0,'B+ve','AHMPP1443R','',5,'2012-06-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (140,'PIN_1290','Manjeet ','Singh','','1984-06-28','9996630033; Alternate No - 8283820248','','','Permanent/Current  Address : \nC\\O Major Malkiat Singh, HNo-476 Babyal, Ambala Cantt\nHaryana – 133005, Phone – 0171-2669857 ; 0999-663-0033 \nPermanent Address 2:\nC\\O Ravinder Singh, H82 – (FF) Residency Green\nGreenwood City ( Opposite Unitech Cyber Park )\nSector-46 , Gurgaon, Haryana-122001\nPhone -0124-4048167 \nPreferred Mail Address : C\\O Dalip Kohli, A-91 Nizamuddin East, New Delhi - 110013',0,'B+ve','BIFPS1724K','',3,'2012-06-18',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (141,'PIN_1297','Sumantra','Nandi','','1975-12-05','8147101454, 08028041897','','','Prestige Shantiniketan,Flat - 18097, Whitefield road, Whitefield\nBangalore 560066',0,'O -ve','ABHPN4684P','',6,'2012-08-16',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (142,'PIN_1302','Hemang','Pant','','1975-02-05','09871436710, 9791162876, 9884552257','','','92/5, Park road, Lakshman Chowk, Deharadun - 248001',0,'O+ve','AIWPP7180N','',3,'2012-11-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (143,'PIN_1304','Kirupakaran','Baskaran','','1984-04-21','9940490818','','','Mailing address: Flat No: F2 Plot No: 2D, Arasan Homes, Chakravarthi street, East Tambaram, Chennai - 59',0,'O+ ve','AKJPB0029K','',8,'2012-12-17',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (144,'PIN_1307','Rakesh','Kola','','1985-09-05','9665791437, 8939197009','','','Permanent address: Block No: M/18, Unit No: 1, M.K.T, Post Nimpura, Kharagpur, West Bengal - 721304',0,'O+ve','AYKPK5096Q','',1,'2013-01-07',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (145,'PIN_1308','Nareshkumar','Sultan','','1985-08-16','8056162530, 908724988, 9985118860, 7675815105','','','Q.No: SD 35, Naspur Colony, C.C.C Mancherial, Adilabad District, A.P - 504302',0,'B+ve','CBGPS6219C','',1,'2013-01-07',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (146,'PIN_1310','Muralikrishna','Ganni','','1982-07-20','9158530558, 09730377666','','','70/15/21, Suresh Nagar, East Godavari district, Kakinada, AP',0,'AB -ve','AMBPG5562A','',2,'2013-01-21',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (147,'PIN_1311','Gopi Krishna Reddi','Palli','','1978-08-13','9885735735, 9600017769','','','6-64, REDDY STREET, AKIVIDU, WEST GODAVARI DT, 534235, ANDHRA PRADESH, INDIA.',0,'B+ve','APYPP6908F','',1,'2013-01-29',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (148,'PIN_1312','Kasim','Veluturla','','1984-10-06','8600925369, 09677139512','','','S/O MEERAVALI,H.No:9-557,INDRA COLONY, KANIGIRI - 523230, PRAKASAM (DT), A.P',0,'A+ve','AKQPV8146A','',1,'2013-02-04',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (149,'PIN_1315','Sivaraman','Baskaran','','1987-07-17','9895478578, 9884545576','','','10-11 Periyar Street, Flat-G3 Regal Square, Gandhi Nagar, Dhasarathapuram, Saligramam, Chennai - 600093',0,'AB +ve','CNLPS5897R','',2,'2013-02-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (150,'PIN_1319','Rozario','Rajan','','1974-07-19','9962081278','','','S238, Koil st,  Elathagiri P.O,  Krishnagiri (T.K) & (D.T)- 635108',0,'O+ve','ALCPR8782R','',3,'2013-04-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (151,'PIN_1320','Saraswathy Kalyani ','Elango','','1985-06-24','7401170004','','','No: 66, Agasthiar Street, East Tambaram, Chennai - 59',0,'B+ve','BGUPS1487C','',2,'2013-04-25',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (152,'PIN_1321','Sivaparamesh','Ravindran Parameswaran','','1983-02-23','9884078840','','','F-98 Door no - 4, 4th street, Anna Nagar East, Chennai- 600102',0,'O+ve','ANBPP5350N','',7,'2013-05-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (153,'PIN_1323','Rekha','Kandasamy','','1983-06-29','9176865917','','','Door No: 5, Plot No: 59, \"Priya Illam\", 1st Floor,\nVijayaganapathy Nagar- 1st Street, Krishna Nagar, Ullagaram, Chennai - 91',0,'A1+ve','ANCPR8269F','',2,'2013-06-10',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (154,'PIN_1325','Abdul Hakeem','Mohammed ','','1981-03-07','9543010569','','','No:12, C-Block, F-5, Singara Garden, 1st street, Errukanchery, Chennai - 118',0,'A+ve','AJFPA5503N','',3,'2013-06-21',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (155,'PIN_1326','Sowmya ','Jegannathan','','1981-07-08','7708220333','','','G-2, Sri Narayanas Apts, 2nd St, IIT Colony, Narayanapuram, Pallikarnai, Chennai - 600100',0,'A1+ve','ASIPS3836N','',3,'2013-06-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (156,'PIN_1332','Kundan ','Maheshwar Singh','','1979-06-24','9888895452','','','c/o Sangeeta Verma, 1706/830, Street Number 26, Preet Nagar, New Shimlapuri, Ludhiana – 141003',0,'O+ve','ARKPK8097Q','',8,'2013-06-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (157,'PIN_1333','Johnshi','Panneerselvam','','1988-01-26','8939328050','','','No: 26, East Street, Melakuppam, Vriddhachalam Taluk, Cuddalore - 607802',0,'B+ ve','ANQPJ2207H','',4,'2013-06-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (158,'PIN_1339','Venkatesan','Jayaraman','','1990-06-25','9865645669','','','Flat No:1, Sivasakthi Nagar, 1st cross street, Kolathur, Chennai - 99',0,'A1+ve','ANPPV6976N','',4,'2013-07-05',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (159,'PIN_1344','Shankar Ram','Ramamoorthy','','1986-04-14','9884284874','','','Door No: 50 A, Ramaswamy Avenue, Sastri Nagar, Adayar, Chennai - 20',0,'O+ve','BQKPS9719K','',1,'2013-07-10',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (160,'PIN_1346','Vishnu Prasath','Pandikrishnan','','1983-07-10','9962090093','','','35 JD Enclave, 1st floor A1, VGP Prabhu Nagar, 1st street, Permbakkam, Chennai - 600 100 ',0,'O+ ve','AGQPV8568G','',1,'2013-07-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (161,'PIN_1348','Noelpriya','Dhanapal','','1988-12-22','9994255041','','','1-1/148A, Indira Nagar, Mohan Nagar, Salem Steel plant, Salem - 636030',0,'O+ve','AKJPN1927C','',1,'2013-07-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (162,'PIN_1349','SenthilKumar ','Shanmugam','','1973-05-10','9500052304','','','No: 48, O-Block, Ganapathy Colony, Anna Nagar East, Chennai - 600102',0,'O+ve','AOPPS8474K','',5,'2013-07-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (163,'PIN_1351','Krithika ','Ganapathy Sundaram','','1987-10-30','9941451765','','','New No:4, Old No:7, 8th Street, Nandanam extension, Chennai - 600 035',0,'O+ve','BJHPK5793H','',1,'2013-07-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (164,'PIN_1352','Prabaharan','Periyasamy','','1982-01-03','9715312928, 9500098854','','','2/12, Sammandhapuram, Villakkethi Post, Erode - 638 109',0,'O+ve','APFPP2643N','',3,'2013-07-17',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (165,'PIN_1353','Sudhakar','Sabapathy','','1976-06-11','9094020840','','','28/48, Elango street, R- Block, MMDA, Arumbakkam, Chennai - 106',0,'O+ve','AYZPS0408D','',3,'2013-07-19',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (166,'PIN_1354','Harshavardhini ','Ramachandran','','1976-11-12','9176647773','','','#.7, Velayudha Raja Street, Mandavelli, Chennai – 600028',0,'O +ve','ACDPH0122P','',7,'2013-07-22',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (167,'PIN_1361','Saviya shree ','Krishnamoorthy','','1989-10-14','9994188389','','','10, Nagaluthu street, Kanchipuram - 631 501',0,'B+ ve','DRJPS0341P','',4,'2013-08-05',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (168,'PIN_1362','Preethi','Dhakshinamoorthy','','1989-06-12','9884803602, 8056158909','','','No: 10, 1st street, Nermai Nagar, Kolathur, Chennai - 99',0,'AB +ve','BBKPP1570L','',4,'2013-08-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (169,'PIN_1363','Srivathi Saranya ','Shanthakumar','','1989-02-11','9600106319','','','No:11, GMM Street, thousand lights, Chennai -06',0,'B+ ve','CPIPS9047G','',1,'2013-08-14',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (170,'PIN_1364','Nitin ','Jain','','1982-09-30','9024112355, 9928285410, 9591541061','','','34, Adarsh Nagar Pali, Rajasthan - 306401; \nCurrent address: 201, Mahaveer Square Apt.\nKodichikkanahalli, Bangalore – 560076',0,'B +ve','AGOPJ7595H','',8,'2013-08-19',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (171,'PIN_1370','Manivel','Jaganathan','','1967-06-30','8220678507, 9566125296','','','Flat # 16, Rajeswari Apartments, 58, Warren Road, Mylapore,Chennai, 600004\nPermanent Address: 14, Jothi Nagar,Kondur (Post),Cuddalore, 600006',0,'A+ve','ALBPM0266A','',5,'2014-01-02',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (172,'PIN_1372','Mani Kandan','Vasudevan','','1989-04-01','9789898356','','','Paul Illam, No: 3, Muthukaruppan Street, Pasumpon Nagar, Pammal, Chennai - 75',0,'B-ve','BNDPM6002M','',1,'2014-02-17',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (173,'PIN_1374','Chippy','Gopan','','1981-01-10','9962942461','','','C6, Grassland Apartments, Mount Punnamalle Road, Mugalivakkam, PORUR\nPermanent Address: Kavungal Madhom, Devagiri P.O, Kangazha, Kottayam, Kerala',0,'A+ve','AJXPG1680J','',3,'2014-05-19',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (174,'15859','Aruna','Thiruvengadam','','1991-01-25','9176266727, 9884523528 (sisiter number)','','','Permanent Address: Thirumani village and Post, Vengiliar Thoppu, Latheri via, Katpadi TK, Vellore - 632202\nCurrent Address: S1, 2nd floor, No. 392/A, P.H.Road, Sandore Nagar (Opp to Anna Arch), Arumbakkam, Chennai - 600 106',0,'A+ ve','BNBPA5118E','',4,'2014-06-09',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (175,'15860','Dhivyamanohary','Ramakrishnan','','1985-11-07','9884703431, EMERGENCY: 9884323431','','','Permanent Address: K8, Swathi Towers, 2 and 3 Durgabhai Deshmukh Road, R.A.Puram, Chennai - 28',0,'O+ve','ALLPD0175H','',1,'2014-06-18',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (176,'15861','Ajan Srinivas','Rengarajan','','1989-10-07','9566230471, Emergency: 044-28443429','','','Permanent Address: Old No: 112, New No: 24/11, T.Pkoil street, Triplicane, Chennai - 600 005\nCurrent Address: Old No: 120, New No: 6, T.Pkoil street, Triplicane, Chennai - 600 005',0,'A+ve','CPBPS6088A','',1,'2014-06-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (177,'15862','Venkata Siva','Dasari','','1978-03-24','9962558999','','','',0,'','AHAPD8014C','',3,'2014-06-25',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (178,'PINI_0002','Adeena','Ashokan','','1991-02-19','9940021807, Emergency Number: 044-26449697','','','Permanent Address: No: 332, 10th Cross street, TP Chathiram, Kilpauk, Chennai - 10',0,'A1+ ve','BJGPA1893R','',4,'2014-06-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (179,'15863','Vidya Teja','Guddati','','1990-11-04','9052034644, Emergency No: 9291251818','','','Permanent Address: Plot No: 156, Sector 5, MVP Colony, Vishakapattinam - 17\nCurrent Address: C5H, CEE DEE YES apartments, Velachery, Chennai',0,'O +ve','BMTPG9723N','',4,'2014-06-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (180,'15864','Deepa','Nair','','1989-01-26','9176629810, Emergency No: 9176679810','','','Permanent Address: TC-37 / 1766, Kottayaril Veedu, Fort PO, Trivandrum - 23\nCurrent Address: Plot # 1, Survey # 63/4, 65 - Sahaj Enclave, Phase iv, Krishnaveni Nagar, Mugalivakkam, Chennai - 600 116',0,'O+ ve','AKTPN2718B','',1,'2014-06-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (181,'15865','Gayathri','Narayanan','','1980-12-10','9445708391; Emergency Contact: 9444026396','','','Permanent Address: F1; Venkatraman Apartments, A.P.Kovil 1st cross street, Pallikaranai, Chennai - 100',0,'O +ve','AEBPN0359J','',2,'2014-07-02',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (182,'15866','Ranjith','Rajendran','','1987-12-02','','','','410/E, Malar colony, 19th Main road, Anna nagar west, Chennai- 600 040',0,'A1 +ve','AXNPR9402K','',1,'2014-07-23',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (183,'16490','Padma deepika','Narayanaswamy','','1990-05-06','8807194443 Emergency Number : 9944198770','','','No 75, Jeevan Nagar, 1st street, Adambakkam, Chennai 600 088',0,'B+ve','BMYPT3488A','',4,'2014-09-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (184,'16510','Neelakantan','Seshagirinathan','','1993-07-17','9940318086  Emergency Contact: same','','','35B, C COLONY, SAI KRISHNA NIVAS APTS, SOUTH WATER TANK STREET, PERUMALPURAM, TIRUNALVELI-627007',0,'O+ ve','AWHPN4431B','',4,'2014-09-03',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (185,'16509','Madhumita','Majumdar','','1991-07-14','9884685234  Emergency Contact: 9042419101','','','UEB, MTTI Airforce station, Avadi, chennai- 600055.',0,'AB+ve','BTQPM3053H','',4,'2014-09-03',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (186,'16549','Praveen ','Muthusamy','','1989-09-08','9940635165 Emergency Contact: 9840526346','','','1/10, CDN Nagar, 4th street, Nerkundram, Chennai- 600107',0,'B+ve','BLAPP5493G','',1,'2014-09-04',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (187,'16550','Kousikan ','Narendran','','1982-01-26','9840303405','','','Address: FR1, 4th Floor, Sai Ishwarya Enclave, 2nd Main road, Telephone Colony, Adambakkam, Chennai - 88',0,'O+ve','ATWPK9268P','',1,'2014-09-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (188,'17009','Saryu','Sukumar','','1989-02-11','8939942210  Emergency Contact: 9444440441','','','No. B3, Cascade, New Giri Road I Street, T Nagar, Chennai - 600017',0,'O+ve','CTGPS2033A','',1,'2014-09-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (189,'17629','Parthiban','Ramakrishnamurthy','','1981-05-14','9361567789                                             Emergency Contact: 9442901283','','','No.8C, Sri Selva Rathina Vinayagar Avenue, Cart Track road, Chennai-600042',0,'B+ve','AKGPR1923B','',3,'2014-10-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (190,'17689','Balaji','Jaganathan','','1980-05-05','9940030827                                                              Emergency Contact: 9500129386','','','3B, Green peace Orion flats,plot no.2, P.T.Rajan Salai, K.K.Nagar, Chennai-78 /                                                                                                                       No 9, ARS Garden, cutcherry street, Gopi Chettipalyam, Erode—638 452',0,'O+ve','ALCPB6584B','',3,'2014-10-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (191,'17690','Abhilash','Ranga','','1988-06-15','9666353333                                                                Emergency Contact:','','','8-4-678, Hanuman nagar, Karim nagar, Telugana – 505 001',0,'B+ve','BHJPA1063J','',1,'2014-10-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (192,'17810','Madhankumar','Santhanam','','1985-08-16','9994092222                                                                   Emergency Contact: 9790880949','','','11/14, Mathangi Apartments, Brindhavan Street Extn., West Mamnalam, Chennai-600033',0,'A1-ve','APOPM3346J','',2,'2014-10-27',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (193,'17869','Ramya','Mani','','1991-10-09','9176371653                                Emergency contact: 7871172772','','','No.26 D, SBM school street, Kollathur, Chennai-600099',0,'A1+ve','BXDPR4537C','',4,'2014-10-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (194,'17909','Sangeetha','Kesavan','','1987-07-27','8056902030                                                                     Emergency contact: 9842693426','','','2/1, Mahalakshmi gardens, Pasupathipalayam po, Gandhi gramam south, Karur, 639004',0,'O+ve','BMTPS4047M','',2,'2014-11-03',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (195,'17910','Senthil Kumar ','Ellappan','','1986-05-10','9600043266                                                      Emergency contact: 044- 25651969','','','19/33, Kamarj street, new Lakshmipuram, Chennai-99',0,'O-ve','CMRPS9179K','',3,'2014-11-03',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (196,'18032','Kavitha ','Shanmugam','','1979-08-15','9962529035                                                       Emergency contact: 9884530221        ','','','132, Marunteeswarar Nagar, Thiruvanmiyur, Chennai- 600 041',0,'AB+ve','AWSPK3311Q','',1,'2014-11-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (197,'18082','Athaur Rahman','Gudiyatham Salt','','1986-01-14','9790964280                                           Emergency contact: 9841057280','','','NO.14/16, 2nd floor, MV Badran street, Periyamet, Chennai-600003.                                                                                                                                                                                                                         No 13, Fakir Mohammed Kulam street, Arcot, Vellore- 632 503',0,'AB+ve','ALQPG4123K','',3,'2014-11-17',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (198,'18172','Parthiban','Thangaraj','','1991-04-26','9840208450                                        Emergency contact: 9445299142','','','No 391, 41st street, Shankar nagar, Bammal, Chennai -600 075',0,'B+ve','BZEPP2925D','',4,'2014-11-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (199,'18245','Girish Kumar','Pallela','','1989-01-25','8297030987                                    Emergency contact:','','','2-36/4, Aganampudi, gajuwaka, Visakapatnam – 530 046',0,'AB-ve','BGEPP6049M','',1,'2014-12-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (200,'18289','Siva kumar Reddy ','Velagala','','1980-04-14','9500070040                                             Emergency contact: 9789024345','','','12-2-420/4, Alapati Nagar, Mehdipatnam, Hyderabad 500 028',0,'A+ve','AEQPV7675C','',8,'2014-12-08',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (201,'18352','Daniel vivek ','Raman','','1983-04-30','9840799691                                                   Emergency contact: 9159282828','','','3/255, Thinniyoor village, Aravenu Post 643 201, Nilgiris',0,'B+ve','AINPR9817M','',1,'2014-12-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (202,'18350','Monisha','Adhiyanur Ramadoss','','1988-10-07','9994692769                                                         Emergency contact: 9894835329','','','No22, 3rd street, Sakthi Nagar, Chengalpet 603 001',0,'O+ve','ARSPM8641A','',1,'2014-12-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (203,'18351','Bhavatharini','Muthuvijayan','','1988-05-17','7667667368                                                            Emergency contact: 9500022292','','','No. 6, Flat G6, Kamakotti Apartments, 3rd Cross Street, 1st Main Road, New Colony, Chrompet, Chennai-600044',0,'B+ve','BBJPB2291P','',1,'2014-12-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (204,'18956','Dhanalakshmi','Chandrasekar','','1986-07-18','8754462961; Emergency Number: 8754462951','','','Address: No: 12/1, Abhirami Nagar, Villivakkam, Chennai - 49',0,'','AKZPC3510A','',1,'2015-01-02',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (205,'18850','Yogasri','Rajkumar','','1992-12-24','9789060050                                                           Emergency contact: 9840239900','','','B 9, PWD quarters, TOD hunter nagar,  Saidhapet, Chennai 600 015',0,'AB+ve','AJQPY6000M','',4,'2015-01-05',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (206,'18851','Aneesh','Seshadri','','1983-10-08','                                                             Emergency contact: 044-24487963','','','DF3, SVS Anandha nilayam apartments, Ramamurthy Nagar, Bangalore- 560 016',0,'A1+ve','AHQPA9554Q','',3,'2015-01-05',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (207,'18871','Arthika','Chandrasekar','','1991-04-23','8973849849                                                              Emergency contact: 8526575929','','',' 296, Vasantha Nagar, Paper Mill Road, Pallipalayam, Erode- 630 008',0,'O+ve ','BKZPA3467P','',4,'2015-01-07',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (208,'18852','Suganya','Venkatachalam','','1989-10-12','9944776924                                                                  Emergency contact: 9443423068','','','B623, Bhel Township, Ranipet – 632 406',0,'O+ve','DHWPS7363L','',1,'2015-01-19',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (209,'19010','Lavanya ','Venkatachalapathy','','1989-07-25','9940063130                                                               Emergency contact: 9962666818','','','5/13, SouthMada Street, Kovur, Chennai -600 122',0,'B+ve','AGAPL6915F','',1,'2015-01-22',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (210,'19011','Santhosh','Anandan','','1977-03-28','9791777040                                                                 Emergency contact: 044 26261623                   ','','','S Block, Old No 73, New no 2, 18th street, Anna nagar, Chennai – 600 040',0,'B+ve',' BUXPS7297F','',3,'2015-01-23',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (211,'19070','Sumalatha','Poolavari','','1985-07-16','8056166204                                                  Emergency contact: ','','','kapupalli village, Kurmoi Post, Palamaner Mandal, Chittoor district, AP 517 408',0,'O+ve','BGZPP8985P','',1,'2015-01-29',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (212,'19230','Balasubramaniam','Muthaiah','','1983-11-12','9790949557                                  Emergency contact: 9790705930','','','32c, Veerabagu nagar pettai, Tirunelveli – 627 004',0,'B+ve','AKSPB7505R','',8,'2015-02-16',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (213,'19270','Manju','Kumaravel','','1992-10-21','8754334199                              Emergency contact: 9363036244','','','4/121, Mchettipaliyam, Mangalam road, Iduvai Post..Thirupur…641 687',0,'O+ve',' BVYPM0670E','',4,'2015-02-23',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (214,'19271','Shankar','Menon','','1989-08-09','99624355838                                          Emergency contact: 7299042771','','','Suresam F7, Shabari garden, Thamarakulangara road, Nadama Thrippunithura, cochin -682301.',0,'B+ve','BBBPM8548L','',1,'2015-02-23',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (215,'19290','Gorumuchu Shoban ','Babu','','1991-03-19','9884742161                                Emergency contact: 9176253993','','','No.6, Amudham Nagar, 3rd Street, Chinna Kodungaiyur, Chennai - 600118',0,'A1-ve','AYHTG5761H','',4,'2015-02-25',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (216,'19388','Venkata Lakshman Kumar ','Konagalla','','1989-08-09','95515 05105','','','12-24-33, Sai lakshmi apartments, GF 1, Gadhiyaram vari street, Kothapet, Guntur 522 001',0,'B +ve','CBWPK1723P','',1,'2015-03-09',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (217,'19467','Rajasekar','Thirugnanam','','1989-01-08','9902415991','','','24B, Type 2 Quarters, Block 6, Neyvelli – 607803',0,'B+ve','AWZPR6429H','',1,'2015-03-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (218,'19468','Santha Kumar','Nammalavar','','1987-05-10','9643581986','','','5/538, Thiruvalluvar street, Anna nagar, Madurai 625 020',0,'O+ve','CRIPS5197M','',1,'2015-03-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (219,'19668','Sujitha Sai','Selvaraju','','1988-06-09','9962078727','','','4, Golden villa, 3rd floor, Balaji nagar, 2nd street, Royapettah, Chennai -600 014',0,'B +ve','BWXPF3161E','',1,'2015-03-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (220,'19669','Naveen','Sridharan','','1991-04-04','9952090225','','','No 3/937, F3, Iyappa flats, Bajanai Kovil street, Madhanandapuram, Porur, Chennai 600 125',0,'A+ ve','AJDPN0509P','',1,'2015-03-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (221,'19748','Dinesh Kumar','Balu','','1987-06-10','8608264143','','','14/12/13, Melamandhai street, Batlagundu, Dindukal 624 202',0,'B +ve','AQTPD3886A','',1,'2015-04-09',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (222,'19767','Sarmistha ','Panda','','1986-05-10','7504539085','','','Plot no EA180, BDA Colony, Stage 2, Lakshmi sagar, Bhuvaneshwar 751 006',0,'B +ve','BAJPP6919H','',1,'2015-04-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (223,'19787','Gaddala','Crispy Salonika','','1991-02-11','7845264659','','','6-8-10/A, Srungarapuram, CPZ church, bapatla, Guntoor district- 522 101',0,'B +ve','BEFPG7526H','',1,'2015-04-14',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (224,'19818','Ragupathy','Selvaraj','','1983-01-01','8884294499','','','2/57, TP Palayam post, Rasipuram, Namakal - 637 406',0,'A1 +ve','AJMPR0207R','',3,'2015-04-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (225,'19947','Surendhar','Palani','','1989-09-14','9940313677','','','44/27, Reddy kuppam street, Temple tower apartment, Saidhapet, Chennai-600 015',0,'A1+ve','DGOPS5893G','',1,'2015-05-04',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (226,'19948','Vishnupriya','Palani','','1993-03-01','9940274008','','','44/27, Reddy kuppam street, Temple tower apartment, Saidhapet, Chennai-600 015',0,'A1+ve','AQLPV4239K','',4,'2015-05-04',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (227,'20046','Dilip','Raghavan','','1984-07-27','9445973349','','','A-2, Alsa Regency 16 Eldams road, Chennai – 600 018',0,'O +ve','AIZPR4137N','',1,'2015-05-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (228,'20047','Bagieswari','Umapathy','','1981-10-05','9962011812','','','45, SRP colony, 7th Street, Jawahar nagar, Chennai -600 082',0,'O +ve','AAPPU9174J','',2,'2015-05-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (229,'20066','Venkatraman','Janarthanan','','1983-05-15','7829242777 / 9566288295','','','No 1, Rajagopalan street, G2, Kaverimalligai flats, kadaperi, Tambaram, chennai-600 45',0,'O +ve','AFHPV5996C','',9,'2015-05-14',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (230,'20107','Ruchi','Rani','','1991-11-01','7838566624','','','Near Chhota Gurudwara, Karnali gate, Taraori, Karnal, Harayana- 132 116',0,'O +ve','BEOPR2000A','',1,'2015-05-18',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (231,'20188','Siddarth','Ramakumar','','1989-12-30','9789092491','','','No4, 4th street, Padmanaba nagar, Adyar, chennai 600 020',0,'O +ve','CRIPS9019L','',1,'2015-05-28',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (232,'20466','Jayanth','Kaliappan','','1985-01-20','9962008974','','','22W/4, Kamarajar street, Kulalarpalayan, Bodi nayakanur 625 513',0,'O+ve','AIBPJ8173A','',2,'2015-06-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (233,'20526','Durai','Perugopanapalli Narasimhan','','1988-07-04','9003153547; Emergency Number: 9790793464','','','Old No. 3, New No. 12, Saraswathy Nagar, 7th Street, AGS Colony, Adambakkam, Chennai - 600088',0,'B+ve','AKJPN9523G','',1,'2015-06-18',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (234,'20597','Saurabh','Kumar','','1993-06-11','9962879929','','','No.5/11, Old No. 3/11, Swetamber Apts, Bazullah Road, T Nagar, Chennai – 600017',0,'','DMHPS5941G','',4,'2015-06-24',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (235,'20687','Chinthala','Raghavendra Nivas','','1992-05-15','9032236881','','','No.8-614, Kothapetta, SriKalahasti, Andhra Pradesh - 517644',0,'B+','ATSPC7503R','',4,'2015-07-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (236,'20688','Adityen','Krishnan','','1993-10-05','9940265984','','','F2, B Block, Narayanathri Appartments, 112-A, Choolaimedu High Road, Choolaimedu, Chennai – 600094',0,'O+','BIRPA9436G','',4,'2015-07-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (237,'20726','Parthiban','Balraj','','1989-07-18','9790408494','','','1/22, SS Koil Street, Melachindamani , Trichy - 620002',0,'O+ve','BQPPP8384J','',1,'2015-07-06',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (238,'20727','Jeyanth Raja ','Selvan','','1990-09-14','9788241307','','','35/21, Bharathiyar Street, Tiruchendhur, Tuticorin District- 628216',0,'O +ve','AQAPJ0175B','',1,'2015-07-06',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (239,'20728','Jaspreet','Kaur','','1990-10-17','9840391963','','','No C - 43 , 1st floor, Fatehnagar, Jail Road, New Delhi - 110018',0,'B+ve','CKCPK4390K','',4,'2015-07-06',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (240,'20778','Vignesh','Vijayarajan','','1990-06-26','9600540125','','','No.176, Class B, 9th Cross Street, Gandhi Nagar, Virupachipuram, Vellore – 632001.\n651, 5th main road, ram nagar south, Madipakkam, Chennai - 600091',0,'','ANTPV9176A','',1,'2015-07-10',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (241,'20831','Sneha','Jain','','1990-10-26','9791186239','','','42, Pana street, Purusawakam, Chennai- 600 007',0,'B+ve','AWRPJ8034G','',1,'2015-07-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (242,'20832','Thariq','Mahmood','','1983-03-12','8939548239','','','AP -721, Ground floor, H block, 12th main road, Anna nagar west, Chennai 600040',0,'A+ ve','AFLPT5321B ','',3,'2015-07-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (243,'20833','Nalini','Balakrishnamurthy','','1984-05-16','9962012411','','','No.33/88A, 15th cross street, Senthil Nagar, Kolathur, Chennai -99',0,'O -ve','ALHPB1872B','',1,'2015-07-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (244,'21247','Subramanian','Nachiappan','','1987-04-15','9840454376','','','No 50, 85th Street, Ashok Nagar, Chennai -600 083',0,'A1 +ve','DBWPS3085R','',1,'2015-08-10',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (245,'21248','Nikunj','Dilip Bokadia','','1991-07-21','9791192196','','','No.5, Perumal 2nd Street, Purasaivakkam , Chennai-6000 09',0,'O+ve','BLNPB0486N','',4,'2015-08-10',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (246,'21646','Sowmya ','Krishnan','','1990-04-27','9840598313','','','N0:2, AG4, Sri Hari Hara Aprtments, K.K.Nagar, 2nd street, Thirumullaivoyal, Chennai - 62',0,'O+ve','BYBPP4614H','',4,'2015-08-31',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (247,'16649','Sanjay Keerthi','Mani','','1990-09-13','9566199367','','','Address: no.25B, IIT Colony, Narayanapuram, Pallikaranai, chennai - 600100    ',0,'A-ve','CIQPK0427F','',4,'2015-09-11',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (248,'22416','Senthil Kumar ','Sundara Vinayagam','','1976-10-01','9841404212','','','Plot No: 10 A, 1st street, Thirumalai Nagar, Madambakkam, Chennai - 126',0,'A1B+ve','AWEPS0342M','',3,'2015-09-21',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (249,'22217','Prabakar','Murali','','1988-08-01','9943786369','','','Current Address: 72/47, Alandur Road, Saidapet, Chennai -15\nPermanent Address: 8/30, Dabeer New street, Kumbakonam, Tamilnadu - 612001',0,'B+ve','CMJPM0420R','',4,'2015-09-23',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (250,'22252','Bhagyalakshmi','Mohana Sundaram','','1990-06-29','9845644377','','','N0:13, Kuill Pattu street, Sree Sakthi Nagar, Annanur, Chennai - 109',0,'A+ve','BBFPB9372K','',1,'2015-09-28',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (251,'22352','Usha Deepthi','Mogillapalli','','1989-06-19','8015319455','','','Flat no 207, Pulipati apartments, Ratham Bazaar, Nandigama, Krishna district, Andra Pradesh Pin code: 521185',0,'B+ve','BCGPM7747F','',2,'2015-10-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (252,'22472','Arthy','Sivasamy','','1990-05-15','9994747333','','','Current Address: Blooming ladies hostel, R.K.Complex, Madipakkam, Chennai\nPermanent Address: Plot:33, EAST, E-Block, MVM Nagar, Dindigual, Tamilnadu',0,'O+ve','','',1,'2015-10-06',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (253,'22552','Srinivasan','Ramasamy','','1989-03-19','9994061071','','','Permanent Address: No:156, Pechiamman Padithurai road, Madurai-625001\nCurrent Address: 16/23, 2nd street, SBA Officers colony, Poonamallee High road, Arumbakkam, Chennnai - 106',0,'A1+ve','CNWPS0561D','',1,'2015-10-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (254,'22553','Hemanth Kumar','Sanjeevi Raman','','1991-06-15','9003091765','','','2/145, 3rd Main road, Raghava Nagar, Moovarasampet, Chennai - 600091',0,'B+ve','AGSPH3609L','',1,'2015-10-12',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (255,'22673','Senthamizhan','Senthamarai','','1987-05-23','9445119820','','','',0,'','','',2,'2015-10-14',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (256,'22674','Dinesh ','Adhinarayanan','','1987-05-23','9840025072','','','',0,'','','',1,'2015-10-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (257,'22675','Sandeep','Padmanabha','','1985-10-07','','','','S/o K Padmanabha Reddy, Agaramangalam, Chittoor (Dist), Andhra Pradesh – 517167',0,'','BIMPS3852F','',3,'2015-10-15',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (258,'23552','Sundararajan','Neelakandan','','1971-07-11','97909 58322 Emergency contact: 8754540465','','','AF2, Sakthi Flats, No 10, Nagappan street, Jafferkhanpet, Chennai-600 083 ',0,'O+ve','ADLPN3181B','',3,'2015-11-01',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (259,'23192','Ramya','Jayakrishnan','','1990-07-11','8220249877','','','PA14, Tarangini Complex, Mogappair West, Ambattur Estate Post, Chennai – 600058',0,'O +ve','BHSPR3605J','',1,'2015-11-02',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (260,'23193','Prasanth ','Gunasekaran','','1990-08-03','9952216946','','','Permanent Address: 3-24/3-34, Bazaar Street, Yethapur, Attur, Salem – 636117\nCurrent Address: Blossom Apartment, Plot No: 184/A, 9th main road, 1st Link, Ram Nagar, Madipakkam, Chennai - 91',0,'B+ve','BSNPP8540C','',1,'2015-11-02',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (261,'23312','Prabhu','Mohanarangam','','1990-01-16','9840345377','','','No:12A, 28th Avenue, Narmada Flats, Banu Nagar, Pudur Ambattur, Chennai -600053',0,'O+ ve','BLCPP8254K','',1,'2015-11-05',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (262,'23872','Roja Roshini','Krishnamurthy','','1991-11-06','8344279348','','','Permanent Address: No: 13/44, Venkatachalapathy layout, street 2, poochiyur via, nsn palayam, Coimbatore-641031.\nCurrent Address: No:4, Block D, Vajra Apartments, Village high road, Backside of Infosys, Sholinganallur, Chennai - 119',0,'O+ve','BIAPR8256E','',1,'2015-11-17',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (263,'24032','Faisul Ihshan','Ajmalkhan','','1989-12-29','9003132700','','','No: 37/A, Elumalai street, Ayanavaram, Chennai- 600023',0,'A+ve','ACAPF6624R','',1,'2015-11-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (264,'24033','Ramalakshmi','Venugopalakrishnan','','1994-06-05','7708463391','','','No:1/43 North Street, Keelaambur, Ambasamudram Taluk, Tirunelveli district- 627418',0,'A+ve','','',4,'2015-11-30',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (265,'24552','Dinesh','Venkatasubramanian','','1990-09-14','9840051227','','','No: 378, S1, Gurubalaji Apartments, Medavakkam Main Road, Puzhudhivakkam, Chennai - 600091',0,'A2B -ve','BJGPD0406F','',1,'2015-12-16',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (266,'24832','Rahul','Vangara','','1988-12-26','9884441388','','','No:8, Flat E, Ananda nilayam Street, Nehru Nagar, Chrompet, Chennai – 600 044',0,'A1 +ve','ALDPV8225E','',2,'2016-01-04',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (267,'25072','Vigneshpandi','Marimuthu','','1994-07-27','8883879920 / 9024209557','','','No.24, Iruthayarajya Puram, North 3rd Street, Sellur, Madurai, Tamilnadu – 625 002',0,'A1+ve','AWHPV7132M','',4,'2016-01-13',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (268,'25092','Naveenprabu','VellodePeriyasamy','','1987-05-20','9840305565','','','No.3, Odakkadu CHML Road, Vellode, Erode District – 638112\nCurrent Address: K-F3 Block, Janani Raghav Enclaves, Chemmencherry, OMR, Chennai - 600 119',0,'A1+ve','AIDPN2817K','',1,'2016-01-14',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (269,'25172','Sriram','Rajagopalam','','1992-05-19','9686555136','','','No.68, Yasodha Nagar, 5th Street, Keelkatalai, Chennai -600117',0,'O+ve','FBHPS2971D','',1,'2016-01-18',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male',''),
 (270,'25272','Saranya','Rajeshwaran','','1987-05-10','8870688075','','','F2B, Ist Floor, Sri Bhavani Illam,  Sri Narayana Nagar, Poompuhar Street(south), Vettuvankeni, Chennai -600 041',0,'O+ve','BGFPR0755M','',1,'2016-01-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Female',''),
 (271,'25273','Gnanasambandhan','Angeerasa','','1992-08-18','9840931131','','','5/627, Nehru Street, Senthil Nagar, Otteri, Vandalur, Chennai – 600048',0,'B+ve','BACPG7096R','',1,'2016-01-20',1,'',0,'',NULL,1,'2016-06-08 00:00:00','2016-06-08 00:00:00',1,2,'Male','');
 
 insert into employee_login(pk, employee_pk, email_id, pwd, emp_lock,dt_created,dt_modified,created_by, modified_by) values 
 (1, 1, 'admin', 'admin', 0,'2016-03-15 00:00:00','2016-03-15 00:00:00',1,1);
 
 
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (162,4,'2010-06-01 00:00:00','2011-08-20 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (162,1,'2011-08-21 00:00:00','2012-05-15 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (162,2,'2012-05-16 00:00:00','2013-11-01 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (162,3,'2013-11-02 00:00:00','2015-05-31 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (162,5,'2015-06-01 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);

INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (171,4,'2010-02-01 00:00:00','2011-06-20 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (171,1,'2011-06-21 00:00:00','2012-01-15 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (171,2,'2012-01-16 00:00:00','2013-07-01 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`end_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (171,3,'2013-07-02 00:00:00','2015-01-31 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);
INSERT INTO `perficient`.`employee_designation_history`
(`employee_pk`,`designation_pk`,`start_date`,`active`,`dt_created`,`dt_modified`,`created_by`,`modified_by`)
VALUES (171,5,'2015-02-01 00:00:00',1,'2016-06-10 00:00:00','2016-06-10 00:00:00',1,1);


INSERT INTO `perficient`.`roles`
(`pk`,`rolename`,`active`,`dt_created`,`created_by`,`dt_modified`,`modified_by`) VALUES
(1,'admin',1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(2,'manager',1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(3,'team lead',1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(4, 'team member',1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1);

INSERT INTO `perficient`.`roles_access`
(`pk`, `accessname`, `active`, `dt_created`, `created_by`, `dt_modified`, `modified_by`) VALUES
(1, 'superadmin', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(2, 'readandwrite', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(3, 'read', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1);

INSERT INTO `perficient`.`component`
(`pk`, `pagename`,`page_display_name`, `menuname`, `menu_display_name`, `active`, `dt_created`, `created_by`, `dt_modified`, `modified_by`) VALUES
(1, 'page1', 'page1', 'menu1', 'menu1', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(2, 'page2', 'page2', 'menu1', 'menu1', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(3, 'page3', 'page3', 'menu1', 'menu1', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(4, 'page4', 'page4', 'menu1', 'menu1', 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1);

INSERT INTO `perficient`.`role_page_access`
(`roles_pk`, `roles_access_pk`, `component_pk`, `active`, `dt_created`,`created_by`, `dt_modified`, `modified_by`) VALUES
(1, 1 ,1 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(1, 1 ,2 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(1, 1 ,3 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(1, 1 ,4 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(2, 2 ,1 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(2, 2 ,2 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(3, 2 ,1 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(3, 3 ,2 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(4, 3 ,1 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(4, 3 ,2 , 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1);


INSERT INTO `perficient`.`employee_roles`
(`employee_pk`, `role_pk`,`active`, `dt_created`, `created_by`, `dt_modified`, `modified_by`) VALUES
(139, 2, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(139, 4, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(141, 1, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(141, 2, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(141, 4, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(250, 3, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(250, 4, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1),
(239, 4, 1,'2016-07-30 00:00:00',1,'2016-07-30 00:00:00',1);

update perficient.designations set active = 0 where pk > 0; 
update perficient.employee set billable = 1 where pk >0;
