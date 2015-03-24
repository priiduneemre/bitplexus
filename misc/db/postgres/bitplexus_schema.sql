
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DDL & DML statements for reconstructing the application's database (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-03-24 21:02:42*/


/*1. DDL - Root-level database objects (databases, roles etc.)*/
/*1.1.1 Creation statements (platform independent)*/
CREATE USER bitplexus_customer;
CREATE USER bitplexus_support;
CREATE USER bitplexus_dba;

/*1.1.1 Creation statements (platform dependent)*/
/*(Option #1: for Linux environments)*/
CREATE DATABASE bitplexus
WITH
OWNER bitplexus_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'en_US.UTF-8'
LC_CTYPE 'en_US.UTF-8'
CONNECTION LIMIT 100;

/*(Option #2: for Windows environments)*/
CREATE DATABASE bitplexus
WITH
OWNER bitplexus_dba
TEMPLATE template0
ENCODING 'UTF8'
LC_COLLATE 'English_United states.1252'
LC_CTYPE 'English_United states.1252'
CONNECTION LIMIT 100;

/*1.2 Removal statements*/
DROP USER bitplexus_customer;
DROP USER bitplexus_support;
DROP USER bitplexus_dba;
DROP DATABASE IF EXISTS bitplexus;

/*2. DDL - Database-level objects (schemas, extensions etc.)*/
/*2.1 Creation statements*/
/*2.2 Removal statements*/


/*3. DDL - Tables*/
/*3.1 Creation statements*/
CREATE TABLE member (
    member_id       SERIAL,
    username        VARCHAR(30)     NOT NULL,
    password        VARCHAR(60)     NOT NULL,
    failed_logins   INTEGER         NOT NULL    DEFAULT 0,
    is_active       BOOLEAN         NOT NULL    DEFAULT TRUE,
    registered_at   TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at      TIMESTAMP,
    updated_by      TIMESTAMP,
    
    CONSTRAINT pk_member PRIMARY KEY (member_id),
);

CREATE TABLE person (
    person_id   INTEGER         NOT NULL,
    first_name  VARCHAR(50)     NOT NULL,
    last_name   VARCHAR(50)     NOT NULL,
    created_at  TIMESTAMP       NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at  TIMESTAMP,
    
    CONSTRAINT pk_person PRIMARY KEY (person_id),
);

CREATE TABLE email_address (
    email_address_id,
    address,
    created_at
    
    CONSTRAINT pk_email_address PRIMARY KEY (email_address_id),
);

CREATE TABLE phone_number (
    phone_number_id,
    country_code        VARCHAR(3),
    subscriber_number   VARCHAR(14),
    created_at
    
    CONSTRAINT pk_phone_number PRIMARY KEY (phone_number_id),
);

CREATE TABLE person_email_address (
    person_id,
    email_address_id,
    is_verified,
    is_active,
    linked_at,
    updated_at
    
    CONSTRAINT pk_person_email_address PRIMARY KEY (person_id, email_address_id),
);

CREATE TABLE person_phone_number (
    person_id,
    phone_number_id,
    is_verified,
    is_active,
    linked_at,
    updated_at,
    
    CONSTRAINT pk_person_phone_number PRIMARY KEY (person_id, phone_number_id),
);

CREATE TABLE customer (
    customer_id,
    created_at,
    
    CONSTRAINT pk_customer PRIMARY KEY (customer_id),
);

CREATE TABLE employee (
    employee_id,
    born_on,
    iban,    
    employed_on,
    resigned_on,
    is_active,
    created_at
    
    CONSTRAINT pk_employee PRIMARY KEY (employee_id),
);

CREATE TABLE role (
    role_id,
    code,
    name

    CONSTRAINT pk_role PRIMARY KEY (role_id),
);

CREATE TABLE employee_role (
    employee_id,
    role_id,
    is_active,
    assigned_at
    
    CONSTRAINT pk_employee_role PRIMARY KEY (employee_id, role_id)
);

CREATE TABLE currency (
    currency_id     SERIAL,
    name            VARCHAR(60)     NOT NULL,
    abbreviation    VARCHAR(10)     NOT NULL,
    symbol          VARCHAR(5)      NOT NULL,
    supply_limit    BIGINT,
    website_url     VARCHAR(100),
    launched_on,
    created_at,
    created_by,
    updated_at,
    updated_by
    
    CONSTRAINT pk_currency PRIMARY KEY (currency_id),
    CONSTRAINT ak_currency_name UNIQUE (name),
    CONSTRAINT ak_currency_abbreviation UNIQUE (abbreviation),
    CONSTRAINT ak_currency_symbol UNIQUE (symbol)
);

CREATE TABLE chain (
    chain_id,
    currency_id,
    code,
    name,
    started_on,
    created_at,
    created_by,
    updated_at,
    updated_by,
    
    CONSTRAINT pk_chain PRIMARY KEY (chain_id),
);

CREATE TABLE wallet (
    wallet_id,
    customer_id,
    chain_id,
    name,
    address_count,
    created_at,
    updated_at,
);

CREATE TABLE address_type (
    address_type_id,
    currency_id,
    chain_id,
    code,
    name,
    leading_symbol,
    created_at,
    created_by,
    updated_at,
    updated_by
);

CREATE TABLE address (
    address_id,
    wallet_id,
    address_type_id,
    base58_code,
    balance,
    is_used,
    generated_at,
    updated_at,
);

CREATE TABLE address_book_entry (
    address_book_entry_id,
    customer_id,
    address_id,
    label,
    created_at,
    updated_at,
);

CREATE TABLE transaction_type (
    transaction_type_id,
    code,
    name
);

CREATE TABLE transaction_status_type (
    transaction_status_type_id,
    code,
    name
);

CREATE TABLE transactions (
    transaction_id,
    customer_id,
    from_address_id,
    to_address_id,
    transaction_type_id,
    transaction_status_type_id,
    local_uid, 
    network_uid,
    amount,
    fee,
    unit_price,
    confirmations,
    block_height,
    hex_size,
    note,
    detected_at,
    updated_at,
);

CREATE TABLE payment_request (
    payment_request_id,
    address_id,
    label,
    amount,
    note,
    requested_at,
);

CREATE TABLE message (
    message_id,
    address_id,
    content,
    signature,
    signed_at,
);

CREATE TABLE visit (
    visit_id                BIGSERIAL,
    customer_id             INTEGER         NOT NULL,
    ip_address              VARCHAR(45)     NOT NULL,
    visited_at
);

/*3.2 Removal statements*/
DROP TABLE IF EXISTS member CASCADE;
DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS email_address CASCADE;
DROP TABLE IF EXISTS phone_number CASCADE;
DROP TABLE IF EXISTS person_email_address CASCADE;
DROP TABLE IF EXISTS person_phone_number CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS role CASCADE;
DROP TABLE IF EXISTS employee_role CASCADE;
DROP TABLE IF EXISTS currency CASCADE;
DROP TABLE IF EXISTS chain CASCADE;
DROP TABLE IF EXISTS wallet CASCADE;
DROP TABLE IF EXISTS address_type CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS address_book_entry CASCADE;
DROP TABLE IF EXISTS transaction_type CASCADE;
DROP TABLE IF EXISTS transaction_status_type CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS payment_request CASCADE;
DROP TABLE IF EXISTS message CASCADE;
DROP TABLE IF EXISTS visit CASCADE;

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
