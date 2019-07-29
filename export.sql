--------------------------------------------------------
--  File created - Monday-July-29-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BILL
--------------------------------------------------------

  CREATE TABLE "SCOTT"."BILL" 
   (	"ITEM_NAME" VARCHAR2(50 BYTE), 
	"BARCODE" NUMBER(15,0), 
	"DATEP" VARCHAR2(50 BYTE), 
	"ITEM_TYPE" VARCHAR2(50 BYTE), 
	"QUANTITY" NUMBER(3,0), 
	"PRICE" NUMBER(5,0), 
	"PROFIT" NUMBER(5,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CASE
--------------------------------------------------------

  CREATE TABLE "SCOTT"."CASE" 
   (	"BRAND" VARCHAR2(20 BYTE), 
	"COMBATIBILTY" VARCHAR2(20 BYTE), 
	"CASE_ID" NUMBER(10,0), 
	"SUPNUMBER" NUMBER(10,0), 
	"COLOR" VARCHAR2(50 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table CHARGER
--------------------------------------------------------

  CREATE TABLE "SCOTT"."CHARGER" 
   (	"CTYPE" VARCHAR2(20 BYTE), 
	"QUALITY" VARCHAR2(20 BYTE), 
	"C_ID" NUMBER(10,0), 
	"SUPNUMBER" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYEE
--------------------------------------------------------

  CREATE TABLE "SCOTT"."EMPLOYEE" 
   (	"FIRST_NAME" VARCHAR2(50 BYTE), 
	"LAST_NAME" VARCHAR2(50 BYTE), 
	"USERNAME" VARCHAR2(50 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"EPHONENUMBER" NUMBER(10,0), 
	"EMAIL" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table HEADPHONE
--------------------------------------------------------

  CREATE TABLE "SCOTT"."HEADPHONE" 
   (	"MANUFACTURE" VARCHAR2(20 BYTE), 
	"CONNECTIVITY" VARCHAR2(20 BYTE), 
	"H_ID" NUMBER(10,0), 
	"SUPNUMBER" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PHONE
--------------------------------------------------------

  CREATE TABLE "SCOTT"."PHONE" 
   (	"MANUFACTURE" VARCHAR2(50 BYTE), 
	"P_NAME" VARCHAR2(50 BYTE), 
	"P_ID" NUMBER(10,0), 
	"SUPNUMBER" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table POWERBANK
--------------------------------------------------------

  CREATE TABLE "SCOTT"."POWERBANK" 
   (	"MANUFACTURE" VARCHAR2(20 BYTE), 
	"P_CAPACITY" VARCHAR2(20 BYTE), 
	"POWER_ID" NUMBER(10,0), 
	"SUPNUMBER" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "SCOTT"."PRODUCT" 
   (	"PRODUCT_ID" NUMBER(10,0), 
	"SPRICE" NUMBER(10,0), 
	"PPRICE" NUMBER(10,0), 
	"QUANTITY" NUMBER(*,0), 
	"PROFIT" NUMBER(4,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table SUPPLIER
--------------------------------------------------------

  CREATE TABLE "SCOTT"."SUPPLIER" 
   (	"FIRSTNAME" VARCHAR2(50 BYTE), 
	"LASTNAME" VARCHAR2(50 BYTE), 
	"SPHONENUMBER" NUMBER(10,0), 
	"PRODUCT_ID" NUMBER(2,0), 
	"PRODUCT_ID_TY" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into SCOTT.BILL
SET DEFINE OFF;
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('Samsung note10',54,'2019-07-29','Phone',1,6000,1000);
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('Mi note9',44,'2019-07-29','Phone',1,3000,450);
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('Beats 3.5mmWired',50,'2019-07-29','Headphone',1,200,100);
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('First Copy MicroUSB',47,'2019-07-29','Charger',1,20,10);
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('IPhone XR',43,'2019-07-29','Phone',1,4000,500);
Insert into SCOTT.BILL (ITEM_NAME,BARCODE,DATEP,ITEM_TYPE,QUANTITY,PRICE,PROFIT) values ('Samsung s9',45,'2019-07-29','Phone',1,5000,800);
REM INSERTING into SCOTT.CASE
SET DEFINE OFF;
Insert into SCOTT.CASE (BRAND,COMBATIBILTY,CASE_ID,SUPNUMBER,COLOR) values ('Silicone','note9',49,55,'Black');
REM INSERTING into SCOTT.CHARGER
SET DEFINE OFF;
Insert into SCOTT.CHARGER (CTYPE,QUALITY,C_ID,SUPNUMBER) values ('Type-C','Original',46,32456);
Insert into SCOTT.CHARGER (CTYPE,QUALITY,C_ID,SUPNUMBER) values ('MicroUSB','First Copy',47,32435);
Insert into SCOTT.CHARGER (CTYPE,QUALITY,C_ID,SUPNUMBER) values ('iPhone','Commerial',48,123);
REM INSERTING into SCOTT.EMPLOYEE
SET DEFINE OFF;
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('hussein','salha','7ssSalha','987654321',52121,'hussein77795@gmail.com');
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('ER`EQ','FEWFEFWE','WWEW','jPasswordField1',4342,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('q','q','qq','jPasswordField1',12,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('w','w','w','jPasswordField1',3,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('fathi','mustafa','opr','jPasswordField1',123,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('majed','abuzant','m','mz',597592782,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('mohanned','abuzant','momo','asdfghjkl',598789,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('q','q','q','jPasswordField1',7,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('z','z','zz','jPasswordField1',4,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('1','1','1','jPasswordField1',1,null);
Insert into SCOTT.EMPLOYEE (FIRST_NAME,LAST_NAME,USERNAME,PASSWORD,EPHONENUMBER,EMAIL) values ('hossam','nah','hosam76','987654321',12747,'thanameri@gmail.com');
REM INSERTING into SCOTT.HEADPHONE
SET DEFINE OFF;
Insert into SCOTT.HEADPHONE (MANUFACTURE,CONNECTIVITY,H_ID,SUPNUMBER) values ('Beats','3.5mmWired',50,436721);
Insert into SCOTT.HEADPHONE (MANUFACTURE,CONNECTIVITY,H_ID,SUPNUMBER) values ('JBL','Type-C Wired',53,74135);
REM INSERTING into SCOTT.PHONE
SET DEFINE OFF;
Insert into SCOTT.PHONE (MANUFACTURE,P_NAME,P_ID,SUPNUMBER) values ('Samsung','s9',45,332);
Insert into SCOTT.PHONE (MANUFACTURE,P_NAME,P_ID,SUPNUMBER) values ('IPhone','XR',43,23244);
Insert into SCOTT.PHONE (MANUFACTURE,P_NAME,P_ID,SUPNUMBER) values ('Mi','note9',44,675);
Insert into SCOTT.PHONE (MANUFACTURE,P_NAME,P_ID,SUPNUMBER) values ('Samsung','note10',54,5656);
Insert into SCOTT.PHONE (MANUFACTURE,P_NAME,P_ID,SUPNUMBER) values ('IPhone','x',55,23244);
REM INSERTING into SCOTT.POWERBANK
SET DEFINE OFF;
Insert into SCOTT.POWERBANK (MANUFACTURE,P_CAPACITY,POWER_ID,SUPNUMBER) values ('Rock','7500',52,343);
Insert into SCOTT.POWERBANK (MANUFACTURE,P_CAPACITY,POWER_ID,SUPNUMBER) values ('Samsung','10000',51,343);
REM INSERTING into SCOTT.PRODUCT
SET DEFINE OFF;
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (45,5000,4200,3,800);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (46,50,40,4,10);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (49,15,12,10,3);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (50,200,100,6,100);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (52,70,50,3,20);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (54,6000,5000,1,1000);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (55,15,12,3,3);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (43,4000,3500,4,500);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (44,3000,2550,6,450);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (48,10,5,10,5);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (51,60,40,8,20);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (47,20,10,6,10);
Insert into SCOTT.PRODUCT (PRODUCT_ID,SPRICE,PPRICE,QUANTITY,PROFIT) values (53,150,120,4,30);
REM INSERTING into SCOTT.SUPPLIER
SET DEFINE OFF;
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('hussein','salha',675,1,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('abd','sama''na',32456,2,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('akram','hadi',92,3,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('rami','rami',436721,6,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('baha','qadi',74135,6,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('borhan','salah',5342,5,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('khaled','hennawi',1213,4,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('mohsen','alzaghlool',123,2,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('bader','hasan',542684,7,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('mohanned','abuzant',332,1,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('nihad','masri',32,4,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('samer','qamhia',55,5,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('radi','zidan',32435,2,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('ahmad','kayed',23244,1,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('hadi','hadi',343,7,null);
Insert into SCOTT.SUPPLIER (FIRSTNAME,LASTNAME,SPHONENUMBER,PRODUCT_ID,PRODUCT_ID_TY) values ('ahmad','ahmad',5656,1,null);
--------------------------------------------------------
--  DDL for Index SYS_C0011111
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SYS_C0011111" ON "SCOTT"."EMPLOYEE" ("USERNAME") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011141
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SYS_C0011141" ON "SCOTT"."SUPPLIER" ("SPHONENUMBER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0011145
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."SYS_C0011145" ON "SCOTT"."PRODUCT" ("PRODUCT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  Constraints for Table BILL
--------------------------------------------------------

  ALTER TABLE "SCOTT"."BILL" MODIFY ("ITEM_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."BILL" MODIFY ("BARCODE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CASE
--------------------------------------------------------

  ALTER TABLE "SCOTT"."CASE" MODIFY ("BRAND" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."CASE" MODIFY ("COMBATIBILTY" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."CASE" MODIFY ("CASE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CHARGER
--------------------------------------------------------

  ALTER TABLE "SCOTT"."CHARGER" MODIFY ("CTYPE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."CHARGER" MODIFY ("QUALITY" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."CHARGER" MODIFY ("C_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPLOYEE
--------------------------------------------------------

  ALTER TABLE "SCOTT"."EMPLOYEE" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."EMPLOYEE" ADD PRIMARY KEY ("USERNAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table HEADPHONE
--------------------------------------------------------

  ALTER TABLE "SCOTT"."HEADPHONE" MODIFY ("MANUFACTURE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."HEADPHONE" MODIFY ("CONNECTIVITY" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."HEADPHONE" MODIFY ("H_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PHONE
--------------------------------------------------------

  ALTER TABLE "SCOTT"."PHONE" MODIFY ("MANUFACTURE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."PHONE" MODIFY ("P_NAME" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."PHONE" MODIFY ("P_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table POWERBANK
--------------------------------------------------------

  ALTER TABLE "SCOTT"."POWERBANK" MODIFY ("MANUFACTURE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."POWERBANK" MODIFY ("P_CAPACITY" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."POWERBANK" MODIFY ("POWER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PRODUCT
--------------------------------------------------------

  ALTER TABLE "SCOTT"."PRODUCT" MODIFY ("SPRICE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."PRODUCT" MODIFY ("PPRICE" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."PRODUCT" MODIFY ("QUANTITY" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."PRODUCT" ADD PRIMARY KEY ("PRODUCT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table SUPPLIER
--------------------------------------------------------

  ALTER TABLE "SCOTT"."SUPPLIER" MODIFY ("FIRSTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."SUPPLIER" MODIFY ("LASTNAME" NOT NULL ENABLE);
 
  ALTER TABLE "SCOTT"."SUPPLIER" ADD PRIMARY KEY ("SPHONENUMBER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
