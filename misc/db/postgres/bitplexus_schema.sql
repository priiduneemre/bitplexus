
/*Project:          Bitplexus - a proof-of-concept online wallet service for multiple cryptocurrencies (Bitcoin, Litecoin etc.)*/
/*File description: DDL & DML statements for recreating the application's database structure (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-03-19 18:39:47*/


/*1. DDL - Root-level database objects (databases, tablespaces etc.)*/
/*1.1.1 Creation statements (platform-dependent)*/
/*(Option #1: for Linux environments)*/
CREATE DATABASE bitplexus
WITH
OWNER priidu_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'en_US.UTF-8'
LC_CTYPE 'en_US.UTF-8'
CONNECTION LIMIT 100;

/*(Option #2: for Windows environments)*/
CREATE DATABASE bitplexus
WITH
OWNER priidu_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'English_United states.1252'
LC_CTYPE 'English_United states.1252'
CONNECTION LIMIT 100;

/*1.1.2 Creation statements (platform-independent)*/

/*1.2 Removal statements*/
DROP DATABASE IF EXISTS bitplexus;


/*2. DDL - Database-level objects (schemas, extensions etc.)*/
/*2.1 Creation statements*/

/*2.2 Removal statements*/


/*3. DDL - Tables*/
/*3.1 Creation statements*/
CREATE TABLE currency (
    currency_id             SERIAL,
    name                    VARCHAR(60)     NOT NULL,
    abbreviation            VARCHAR(10)     NOT NULL,
    symbol                  VARCHAR(5)      NOT NULL,
    
    CONSTRAINT pk_currency PRIMARY KEY (currency_id),
    CONSTRAINT ak_currency_name UNIQUE (name),
    CONSTRAINT ak_currency_abbreviation UNIQUE (abbreviation),
    CONSTRAINT ak_currency_symbol UNIQUE (symbol)
);

/*3.2 Removal statements*/
DROP TABLE IF EXISTS currency;


/*4. DDL - Indices*/
/*4.1 Secondary indices (needs arising from business logic etc.)*/
/*4.1.1 Creation statements*/

/*4.1.2 Removal statements*/



/*5. DDL - Views*/
/*5.1 Creation statements*/

/*5.2 Removal statements*/



/*6. DDL - Functions*/
/*6.1 Regular functions*/
/*6.1.1 Creation statements*/

/*6.1.2 Removal statements*/


/*7. DML - Management of initial data*/
/*7.1 Regular tables*/
/*7.1.1 Insertion statements*/

/*7.1.4 Deletion statements*/

/*7.2 Reference tables*/
/*7.2.1 Insertion statements*/

/*7.2.2 Deletion statements*/

/*8. Miscellaneous objects & operations*/
/*8.1 Anonymous code blocks*/
