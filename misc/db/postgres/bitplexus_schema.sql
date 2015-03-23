
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DDL & DML statements for reconstructing the application's database (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-03-21 17:40:47*/


/*1. DDL - Root-level database objects (databases, tablespaces etc.)*/
/*1.1.1 Creation statements (platform dependent)*/
/*(Option #1: for Linux environments)*/
CREATE DATABASE bitplexus
WITH
OWNER daedalus_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'en_US.UTF-8'
LC_CTYPE 'en_US.UTF-8'
CONNECTION LIMIT 100;

/*(Option #2: for Windows environments)*/
CREATE DATABASE bitplexus
WITH
OWNER daedalus_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'English_United states.1252'
LC_CTYPE 'English_United states.1252'
CONNECTION LIMIT 100;

/*1.1.2 Creation statements (platform independent)*/


/*1.2 Removal statements*/
DROP DATABASE IF EXISTS bitplexus;


/*2. DDL - Database-level objects (schemas, extensions etc.)*/
/*2.1 Creation statements*/

/*2.2 Removal statements*/


/*3. DDL - Tables*/
/*3.1 Creation statements*/
CREATE TABLE member (
    member_id,
    username,
    password,
    password_salt,
    failed_login_count,
    is_active,
    created_at,
    created_by,
    updated_at,
    updated_by,
);

CREATE TABLE person (
    person_id              SERIAL,
    first_name              VARCHAR(50)     NOT NULL,
    last_name               VARCHAR(50)     NOT NULL,
    created_at
    created_by
    updated_at
    updated_by
);

CREATE TABLE person_email (
    person_email_id       SERIAL,
    email,
    is_active,
    created_at,
    created_by,
);

CREATE TABLE person_phone (
    person_phone_id,
    country_code            VARCHAR(3),
    subscriber_number       VARCHAR(14),
    is_active               BOOLEAN,
    created_at,
    created_by,
);

CREATE TABLE customer (
    customer_id,
    created_at,
    created_by,
);

CREATE TABLE employee (
    employee_id,
    iban,
    born_on,
    employed_from,
    employed_to,
    is_active,
    created_at,
    created_by,
    updated_at,
    updated_by
);

CREATE TABLE employee_role (
    employee_role_id,
    employee_id,
    role_type_id,
    is_active,
    created_at,
    created_by,
    updated_at,
    updated_by
);

CREATE TABLE role_type (
    role_type_id,
    code,
    name
);

CREATE TABLE visit (
    visit_id                BIGSERIAL,
    customer_id             INTEGER         NOT NULL,
    ip_address              VARCHAR(45)     NOT NULL,
    visited_at
);

CREATE TABLE currency (
    currency_id             SERIAL,
    name                    VARCHAR(60)     NOT NULL,
    abbreviation            VARCHAR(10)     NOT NULL,
    symbol                  VARCHAR(5)      NOT NULL,
    supply_limit            BIGINT,
    website_url             VARCHAR(100),
    launched_on,
    
    
    CONSTRAINT pk_currency PRIMARY KEY (currency_id),
    CONSTRAINT ak_currency_name UNIQUE (name),
    CONSTRAINT ak_currency_abbreviation UNIQUE (abbreviation),
    CONSTRAINT ak_currency_symbol UNIQUE (symbol)
);

CREATE TABLE chain (
    chain_id,
    currency_id,
    code,
    name
);

CREATE TABLE wallet (
    wallet_id,
    chain_id,
    name,
    address_count,
    created_at,
    created_by,
    updated_at,
    updated_by
);

CREATE TABLE address (
    address_id,
    wallet_id,
    address_type_id,
    address_hash,
    balance,
    is_used,
    created_on,
    created_by,
    updated_on,
    updated_by,
);

CREATE TABLE address_type (
    address_type_id,
    code,
    name,
    first_symbol
);

CREATE TABLE address_book_entry (
    address_book_entry_id,
    customer_id,
    address_id,
    label,
    created_on,
    created_by,
    updated_on,
    updated_by
);

CREATE TABLE transactions (
    transaction_id,
    customer_id,
    from_address_id,
    to_address_id,
    transaction_type_id,
    local_uid, 
    network_uid,
    amount,
    received_at,
);

CREATE TABLE transaction_type (
    transaction_type_id,
    code,
    name
);

CREATE TABLE payment_request (
    payment_request_id,
    label,
    amount,
    message,
    created_at,
    created_by,
    updated_at,
    updated_by
);

CREATE TABLE message (
    message_id,
    address_id,
    content,
    signature,
    signed_at,
    signed_by
);

/*3.2 Removal statements*/
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS person_phone CASCADE;
DROP TABLE IF EXISTS person_email CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS employee_role CASCADE;
DROP TABLE IF EXISTS role_type CASCADE;
DROP TABLE IF EXISTS visit CASCADE;
DROP TABLE IF EXISTS currency CASCADE;
DROP TABLE IF EXISTS chain CASCADE;
DROP TABLE IF EXISTS wallet CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS address_type CASCADE;
DROP TABLE IF EXISTS contact CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS transaction_type CASCADE;
DROP TABLE IF EXISTS payment_request CASCADE;
DROP TABLE IF EXISTS message CASCADE;


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
