
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DDL & DML statements for reconstructing the application's database (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-03-26 19:35:13*/


/*1. DDL - Root-level objects (databases, roles etc.)*/
/*1.1.1 Creation statements (platform independent)*/
CREATE ROLE bitplexus_customer WITH LOGIN ENCRYPTED PASSWORD '5rNiXDiD1L2MJw7trF2V';
CREATE ROLE bitplexus_employee WITH LOGIN ENCRYPTED PASSWORD 'H8Jw68doiJzUxJUR4jHU';
CREATE ROLE bitplexus_dba WITH LOGIN ENCRYPTED PASSWORD 'C4VEammTWUrWmUMddBQZ';

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
DROP ROLE IF EXISTS bitplexus_customer;
DROP ROLE IF EXISTS bitplexus_employee;
DROP ROLE IF EXISTS bitplexus_dba;
DROP DATABASE IF EXISTS bitplexus;


/*2. DDL - Database-level objects (schemas, extensions etc.)*/
/*2.1 Creation statements*/
/*2.2 Removal statements*/


/*3. DDL - Tables*/
/*3.1 Creation statements*/
CREATE TABLE member (
    member_id       SERIAL,
    username        VARCHAR(15)     NOT NULL,
    password        CHAR(60)        NOT NULL,
    failed_logins   SMALLINT        NOT NULL    DEFAULT 0,
    is_active       BOOLEAN         NOT NULL    DEFAULT TRUE,
    registered_at   TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at      TIMESTAMP(0),
    updated_by      INTEGER,

    CONSTRAINT pk_member PRIMARY KEY (member_id),
    CONSTRAINT ak_member_username UNIQUE (username),
    CONSTRAINT fk_member_updated_by FOREIGN KEY (updated_by) REFERENCES employee (employee_id)

    CONSTRAINT chk_member_failed_logins_in_range CHECK (failed_logins > 0),
    CONSTRAINT chk_member_is_active_valid CHECK (!(failed_logins > 2 AND is_active = TRUE)),
    CONSTRAINT chk_member_registered_at_in_range CHECK (registered_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_member_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE person (
    person_id   INTEGER         NOT NULL,
    first_name  VARCHAR(50)     NOT NULL,
    last_name   VARCHAR(50)     NOT NULL,
    created_at  TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at  TIMESTAMP(0),
    
    CONSTRAINT pk_person PRIMARY KEY (person_id),
    CONSTRAINT fk_person_person_id FOREIGN KEY (person_id) REFERENCES member (member_id) ON DELETE CASCADE,
    
    CONSTRAINT chk_person_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_person_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE email_address (
    email_address_id    SERIAL,
    address             VARCHAR(60)     NOT NULL,
    created_at          TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_email_address PRIMARY KEY (email_address_id),
    CONSTRAINT ak_email_address_address UNIQUE (address),
    
    CONSTRAINT chk_email_address_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
);

CREATE TABLE phone_number (
    phone_number_id     SERIAL,
    country_code        VARCHAR(3)      NOT NULL,
    subscriber_number   VARCHAR(14)     NOT NULL,
    created_at,         TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_phone_number PRIMARY KEY (phone_number_id),
    CONSTRAINT ak_phone_number_country_code_subscriber_number UNIQUE (country_code, subscriber_number),
    
    CONSTRAINT chk_phone_number_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
);

CREATE TABLE person_email_address (
    person_id           INTEGER         NOT NULL,
    email_address_id    INTEGER         NOT NULL,
    is_verified         BOOLEAN         NOT NULL    DEFAULT FALSE,
    is_active           BOOLEAN         NOT NULL    DEFAULT TRUE,
    linked_at,          TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at          TIMESTAMP(0),
    
    CONSTRAINT pk_person_email_address PRIMARY KEY (person_id, email_address_id),
    CONSTRAINT fk_person_email_address_person_id FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE,
    CONSTRAINT fk_person_email_address_email_address_id FOREIGN KEY (email_address_id) REFERENCES email_address (email_address_id),
    
    CONSTRAINT chk_person_email_address_linked_at_in_range CHECK (linked_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
    CONSTRAINT chk_person_email_address_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
);

CREATE TABLE person_phone_number (
    person_id           INTEGER         NOT NULL,
    phone_number_id     INTEGER         NOT NULL,
    is_verified         BOOLEAN         NOT NULL    DEFAULT FALSE,
    is_active           BOOLEAN         NOT NULL    DEFAULT TRUE,
    linked_at           TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at          TIMESTAMP(0),
    
    CONSTRAINT pk_person_phone_number PRIMARY KEY (person_id, phone_number_id),
    CONSTRAINT fk_person_phone_number_person_id FOREIGN KEY (person_id) REFERENCES person (person_id) ON DELETE CASCADE,
    CONSTRAINT fk_person_phone_number_phone_number_id FOREIGN KEY (phone_number_id) REFERENCES phone_number (phone_number_id),
    
    CONSTRAINT chk_person_phone_number_linked_at_in_range CHECK (linked_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
    CONSTRAINT chk_person_phone_number_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),    
    
);

CREATE TABLE customer (
    customer_id     INTEGER         NOT NULL,
    created_at      TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_customer PRIMARY KEY (customer_id),
    CONSTRAINT fk_customer_customer_id FOREIGN KEY (customer_id) REFERENCES person (person_id) ON DELETE CASCADE,
    
    CONSTRAINT chk_customer_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE employee (
    employee_id     INTEGER         NOT NULL,
    born_on         DATE            NOT NULL,
    iban            VARCHAR(34)     NOT NULL,
    employed_on     DATE            NOT NULL,
    resigned_on     DATE,
    is_active       BOOLEAN         NOT NULL    DEFAULT TRUE,
    created_at      TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_employee PRIMARY KEY (employee_id),
    CONSTRAINT fk_employee_employee_id FOREIGN KEY (employee_id) REFERENCES person (person_id) ON DELETE CASCADE,
    
    CONSTRAINT chk_employee_born_on_in_range CHECK (born_on BETWEEN '1900-01-01' AND '2099-12-31'),
    CONSTRAINT chk_employee_employed_on_in_range CHECK (employed_on BETWEEN '1900-01-01' AND '2099-12-31'),
    CONSTRAINT chk_employee_resigned_on_in_range CHECK (resigned_on BETWEEN '1900-01-01' AND '2099-12-31'),
    CONSTRAINT chk_employee_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE role (
    role_id     SMALLSERIAL,
    code        VARCHAR(30)     NOT NULL,
    name        VARCHAR(60)     NOT NULL,
    
    CONSTRAINT pk_role PRIMARY KEY (role_id),
    CONSTRAINT ak_role_code UNIQUE (code),
    CONSTRAINT ak_role_name UNIQUE (name)
);

CREATE TABLE employee_role (
    employee_id     INTEGER         NOT NULL,
    role_id         SMALLINT        NOT NULL,
    is_active       BOOLEAN         NOT NULL    DEFAULT TRUE,
    assigned_at     TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_employee_role PRIMARY KEY (employee_id, role_id),
    CONSTRAINT fk_employee_role_employee_id FOREIGN KEY (employee_id) ON DELETE CASCADE,
    CONSTRAINT fk_employee_role_role_id FOREIGN KEY (role_id) ON UPDATE CASCADE,
    
    CONSTRAINT chk_employee_role_assigned_at_in_range CHECK (assigned_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')    
);

CREATE TABLE currency (
    currency_id     SMALLSERIAL,
    name            VARCHAR(25)     NOT NULL,
    abbreviation    VARCHAR(8)      NOT NULL,
    symbol          VARCHAR(3)      NOT NULL,
    supply_limit    BIGINT          NOT NULL,
    website_url     VARCHAR(100)    NOT NULL,
    launched_on     DATE            NOT NULL,
    created_at      TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    created_by      INTEGER         NOT NULL,
    updated_at      TIMESTAMP(0),
    updated_by      INTEGER,
    
    CONSTRAINT pk_currency PRIMARY KEY (currency_id),
    CONSTRAINT ak_currency_name UNIQUE (name),
    CONSTRAINT ak_currency_abbreviation UNIQUE (abbreviation),
    CONSTRAINT ak_currency_symbol UNIQUE (symbol),
    CONSTRAINT ak_currency_website_url UNIQUE (website_url),
    CONSTRAINT fk_currency_created_by FOREIGN KEY (created_by) REFERENCES employee (employee_id),
    CONSTRAINT fk_currency_updated_by FOREIGN KEY (updated_by) REFERENCES employee (employee_id),
    
    CONSTRAINT chk_currency_supply_limit_in_range CHECK (supply_limit > 0),
    CONSTRAINT chk_currency_launched_on_in_range CHECK (launched_on BETWEEN '1900-01-01' AND '2099-12-31'),
    CONSTRAINT chk_currency_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59') 
    CONSTRAINT chk_currency_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE chain (
    chain_id        SMALLSERIAL,
    currency_id     SMALLINT        NOT NULL,
    code            VARCHAR(30)     NOT NULL,
    name            VARCHAR(60)     NOT NULL,
    started_on      DATE            NOT NULL,
    created_at      TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    created_by      INTEGER         NOT NULL,
    updated_at      TIMESTAMP(0),
    updated_by      INTEGER,
    
    CONSTRAINT pk_chain PRIMARY KEY (chain_id),
    CONSTRAINT ak_chain_code UNIQUE (code),
    CONSTRAINT ak_chain_name UNIQUE (name),
    CONSTRAINT fk_chain_currency_id FOREIGN KEY (currency_id) REFERENCES currency (currency_id) ON DELETE CASCADE,
    CONSTRAINT fk_chain_created_by FOREIGN KEY (created_by) REFERENCES employee (employee_id),
    CONSTRAINT fk_chain_updated_by FOREIGN KEY (updated_by) REFERENCES employee (employee_id),
    
    CONSTRAINT chk_chain_started_on_in_range CHECK (started_on BETWEEN '1900-01-01' AND '2099-12-31'),
    CONSTRAINT chk_chain_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_chain_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE wallet_state_type (
    wallet_state_type_id    SMALLSERIAL,
    code                    VARCHAR(30)     NOT NULL,
    name                    VARCHAR(60)     NOT NULL,
    
    CONSTRAINT pk_wallet_state_type PRIMARY KEY (wallet_state_type_id),
    CONSTRAINT ak_wallet_state_type_code UNIQUE (code),
    CONSTRAINT ak_wallet_state_type_name UNIQUE (name)
);

CREATE TABLE wallet (
    wallet_id               SERIAL,
    customer_id             INTEGER         NOT NULL,
    wallet_state_type_id    SMALLINT        NOT NULL,
    name                    VARCHAR(50)     NOT NULL,
    created_at              TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at              TIMESTAMP(0),
    
    CONSTRAINT pk_wallet PRIMARY KEY (wallet_id),
    CONSTRAINT fk_wallet_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE,
    CONSTRAINT fk_wallet_wallet_state_type_id FOREIGN KEY (wallet_state_type_id) REFERENCES wallet_state_type (wallet_state_type_id) ON UPDATE CASCADE,
    
    CONSTRAINT chk_wallet_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_wallet_updated_at_in_range CHECK (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')    
);

CREATE TABLE address_type (
    address_type_id     SMALLSERIAL,
    chain_id            SMALLINT        NOT NULL,
    code                VARCHAR(30)     NOT NULL,
    name                VARCHAR(60)     NOT NULL,
    leading_symbol      CHAR(1)         NOT NULL,
    created_at          TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    created_by          INTEGER         NOT NULL,
    updated_at          TIMESTAMP(0),
    updated_by          INTEGER,
    
    CONSTRAINT pk_address_type PRIMARY KEY (address_type_id),
    CONSTRAINT ak_address_type_code UNIQUE (code),
    CONSTRAINT ak_address_type_name UNIQUE (name),
    CONSTRAINT fk_address_type_chain_id FOREIGN KEY (chain_id) REFERENCES chain (chain_id),
    CONSTRAINT fk_address_type_created_by FOREIGN KEY (created_by) REFERENCES employee (employee_id),
    CONSTRAINT fk_address_type_updated_by FOREIGN KEY (updated_by) REFERENCES employee (employee_id),
    
    CONSTRAINT chk_address_type_created_at_in_range (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_address_type_updated_at_in_range (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE address_state_type (
    address_state_type_id   SMALLSERIAL,
    code                    VARCHAR(30)     NOT NULL,
    name                    VARCHAR(60)     NOT NULL,
    
    CONSTRAINT pk_address_state_type PRIMARY KEY (address_state_type_id),
    CONSTRAINT ak_address_state_type_code UNIQUE (code),
    CONSTRAINT ak_address_state_type_name UNIQUE (name)
);

CREATE TABLE address (
    address_id              BIGSERIAL,
    wallet_id               INTEGER,
    address_type_id         SMALLINT            NOT NULL,
    address_state_type_id   SMALLINT            NOT NULL,
    label                   VARCHAR(60),
    encoded_form            VARCHAR(35)         NOT NULL,
    balance                 NUMERIC(19, 8),
    created_at              TIMESTAMP(0)        NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at              TIMESTAMP(0),
    
    CONSTRAINT pk_address PRIMARY KEY (address_id),
    CONSTRAINT ak_address_encoded_form UNIQUE (encoded_form),
    CONSTRAINT fk_address_wallet_id FOREIGN KEY (wallet_id) REFERENCES wallet (wallet_id),
    CONSTRAINT fk_address_address_type_id FOREIGN KEY (address_type_id) REFERENCES address_type (address_type_id),
    CONSTRAINT fk_address_address_state_type_id FOREIGN KEY (address_state_type_id) REFERENCES address_state_type (address_state_type_id),
    
    CONSTRAINT chk_address_balance_in_range CHECK (balance >= 0),
    CONSTRAINT chk_address_created_at_in_range (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_address_updated_at_in_range (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE address_book_entry (
    address_book_entry_id   BIGSERIAL,
    customer_id             INTEGER         NOT NULL,
    address_id              BIGINT          NOT NULL,
    label                   VARCHAR(60)     NOT NULL,
    created_at              TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    updated_at              TIMESTAMP(0),
    
    CONSTRAINT pk_address_book_entry PRIMARY KEY (address_book_entry_id),
    CONSTRAINT fk_address_book_entry_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
    CONSTRAINT fk_address_book_entry_address_id FOREIGN KEY (address_id) REFERENCES address (address_id),
    
    CONSTRAINT chk_address_book_entry_created_at_in_range (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_address_book_entry_updated_at_in_range (updated_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE transaction_status_type (
    transaction_status_type_id  SMALLSERIAL,
    code                        VARCHAR(30)     NOT NULL,
    name                        VARCHAR(60)     NOT NULL,
    
    CONSTRAINT pk_transaction_status_type PRIMARY KEY (transaction_status_type_id),
    CONSTRAINT ak_transaction_status_type_code UNIQUE (code),
    CONSTRAINT ak_transaction_status_type_name UNIQUE (name)
);

CREATE TABLE transactions (
    transaction_id              BIGSERIAL,
    transaction_status_type_id  SMALLINT        NOT NULL, 
    local_uid                   CHAR(36)        NOT NULL, 
    network_uid                 CHAR(64)        NOT NULL,
    received_at                 TIMESTAMP(0)    NOT NULL,
    block_height                INTEGER         NOT NULL,
    hex_size                    INTEGER         NOT NULL,
    fee                         NUMERIC(19, 8)  NOT NULL,
    unit_price                  NUMERIC(19, 8)  NOT NULL,
    note                        VARCHAR(255),
    created_at                  TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_transactions PRIMARY KEY (transaction_id),
    CONSTRAINT ak_transactions_local_uid UNIQUE (local_uid),
    CONSTRAINT ak_transactions_network_uid UNIQUE (network_uid),
    CONSTRAINT fk_transactions_transaction_status_type_id FOREIGN KEY (transactions_status_type_id) REFERENCES transactions_status_type (transactions_status_type_id),
        
    CONSTRAINT chk_transactions_block_height_in_range CHECK (block_height > 0),
    CONSTRAINT chk_transactions_hex_size_in_range CHECK (hex_size > 0),
    CONSTRAINT chk_transactions_fee_in_range CHECK (fee >= 0),
    CONSTRAINT chk_transactions_unit_price_in_range CHECK (unit_price > 0),
    CONSTRAINT chk_transactions_received_at_in_range CHECK (received_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
    CONSTRAINT chk_transactions_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
);

CREATE TABLE transaction_endpoint_type (
    transaction_endpoint_type_id    SMALLSERIAL,
    code                            VARCHAR(30)     NOT NULL,
    name                            VARCHAR(60)     NOT NULL,
    
    CONSTRAINT pk_transaction_endpoint_type PRIMARY KEY (transaction_endpoint_type_id),
    CONSTRAINT ak_transaction_endpoint_type_code UNIQUE (code),
    CONSTRAINT ak_transaction_endpoint_type_name UNIQUE (name)
);

CREATE TABLE transaction_endpoint (
    transaction_endpoint_id         BIGSERIAL,
    transaction_id                  BIGINT          NOT NULL,
    address_id                      BIGINT          NOT NULL,
    transaction_endpoint_type_id    SMALLINT        NOT NULL,
    amount                          NUMERIC(19, 8)  NOT NULL,
    created_at                      TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_transaction_endpoint PRIMARY KEY (transaction_endpoint_id),
    CONSTRAINT fk_transaction_endpoint_transaction_id FOREIGN KEY (transaction_id) REFERENCES transactions (transaction_id),
    CONSTRAINT fk_transaction_endpoint_address_id FOREIGN KEY (address_id) REFERENCES address (address_id),
    CONSTRAINT fk_transaction_endpoint_transaction_endpoint_type_id FOREIGN KEY (transaction_endpoint_type_id) REFERENCES transaction_endpoint_type (transaction_endpoint_type_id) ON UPDATE CASCADE,
    
    CONSTRAINT chk_transactions_amount_in_range CHECK (amount > 0),
    CONSTRAINT chk_transaction_endpoint_created_at_in_range CHECK (created_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
);

CREATE TABLE payment_request (
    payment_request_id  SERIAL,
    address_id          BIGINT          NOT NULL,
    amount              NUMERIC(19, 8)  NOT NULL,
    note                VARCHAR(255),
    requested_at        TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),
    
    CONSTRAINT pk_payment_request PRIMARY KEY (payment_request_id),
    CONSTRAINT fk_payment_request_address_id FOREIGN KEY (address_id) REFERENCES address (address_id),
    
    CONSTRAINT chk_payment_request_amount_in_range CHECK (amount > 0),
    CONSTRAINT chk_payment_request_requested_at_in_range CHECK (requested_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59'),
);

CREATE TABLE visit (
    visit_id    BIGSERIAL,
    member_id   INTEGER         NOT NULL,
    ip_address  VARCHAR(45)     NOT NULL,
    visited_at  TIMESTAMP(0)    NOT NULL    DEFAULT CURRENT_TIMESTAMP(0),

    CONSTRAINT pk_visit PRIMARY KEY (visit_id),
    CONSTRAINT ak_visit_ip_address UNIQUE (ip_address),
    CONSTRAINT fk_visit_member_id FOREIGN KEY (member_id) REFERENCES member (member_id),
    
    CONSTRAINT chk_visit_visited_at_in_range CHECK (visisted_at BETWEEN '1900-01-01 00:00:00' AND '2099-12-31 23:59:59')
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
DROP TABLE IF EXISTS wallet_state_type CASCADE;
DROP TABLE IF EXISTS wallet CASCADE;
DROP TABLE IF EXISTS address_type CASCADE;
DROP TABLE IF EXISTS address_state_type CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS address_book_entry CASCADE;
DROP TABLE IF EXISTS transaction_status_type CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS transaction_endpoint_type CASCADE;
DROP TABLE IF EXISTS transaction_endpoint CASCADE;
DROP TABLE IF EXISTS payment_request CASCADE;
DROP TABLE IF EXISTS visit CASCADE;


/*4. DDL - Indices*/
/*4.1 Primary indices (foreign keys etc.)*/
/*4.1.1 Creation statements*/
CREATE INDEX idx_member_updated_by ON member USING btree (updated_by);
CREATE INDEX idx_person_person_id ON person USING btree (person_id);
CREATE INDEX idx_person_email_address_email_address_id ON person_email_address USING btree (email_address_id);
CREATE INDEX idx_person_phone_number_phone_number_id ON perosn_phone_number USING btree (phone_number_id);
CREATE INDEX idx_customer_customer_id ON customer USING btree (customer_id);
CREATE INDEX idx_employee_employee_id ON employee USING btree (employee_id);
CREATE INDEX idx_employee_role_role_id ON employee_role USING btree (role_id);
CREATE INDEX idx_currency_created_by ON currency USING btree (created_by);
CREATE INDEX idx_currency_updated_by ON currency USING btree (updated_by);
CREATE INDEX idx_chain_created_by ON chain USING btree (created_by);
CREATE INDEX idx_chain_updated_by ON chain USING btree (updated_by);
CREATE INDEX idx_wallet_customer_id ON wallet USING btree (customer_id);
CREATE INDEX idx_wallet_wallet_state_type_id ON wallet USING btree (wallet_state_type_id);
CREATE INDEX idx_address_type_chain_id ON address_type USING btree (chain_id);
CREATE INDEX idx_address_type_created_by ON address_type USING btree (created_by);
CREATE INDEX idx_address_type_updated_by ON address_type USING btree (updated_by);
CREATE INDEX idx_address_wallet_id ON address USING btree (wallet_id);
CREATE INDEX idx_address_address_type_id ON address USING btree (address_type_id);
CREATE INDEX idx_address_address_state_type_id ON address USING btree (address_state_type_id);
CREATE INDEX idx_address_book_entry_customer_id ON address_book_entry USING btree (customer_id);
CREATE INDEX idx_address_book_entry_address_id ON address_book_entry USING btree (address_id);
CREATE INDEX idx_transactions_transaction_status_type_id ON transactions USING btree (transaction_status_type_id);
CREATE INDEX idx_transaction_endpoint_transaction_id ON transaction_endpoint USING btree (transaction_id);
CREATE INDEX idx_transaction_endpoint_address_id ON transaction_endpoint USING btree (address_id);
CREATE INDEX idx_transaction_endpoint_transaction_endpoint_type_id ON transaction_endpoint USING btree (transaction_endpoint_type_id);
CREATE INDEX idx_payment_request_address_id ON payment_request USING btree (address_id);
CREATE INDEX idx_visit_member_id ON visit USING btree (member_id);

/*4.1.2 Removal statements*/
DROP INDEX IF EXISTS idx_member_updated_by;
DROP INDEX IF EXISTS idx_person_person_id;
DROP INDEX IF EXISTS idx_person_email_address_email_address_id;
DROP INDEX IF EXISTS idx_person_phone_number_phone_number_id;
DROP INDEX IF EXISTS idx_customer_customer_id;
DROP INDEX IF EXISTS idx_employee_employee_id;
DROP INDEX IF EXISTS idx_employee_role_role_id;
DROP INDEX IF EXISTS idx_currency_created_by;
DROP INDEX IF EXISTS idx_currency_updated_by;
DROP INDEX IF EXISTS idx_chain_created_by;
DROP INDEX IF EXISTS idx_chain_updated_by;
DROP INDEX IF EXISTS idx_wallet_customer_id;
DROP INDEX IF EXISTS idx_wallet_wallet_state_type_id;
DROP INDEX IF EXISTS idx_address_type_chain_id;
DROP INDEX IF EXISTS idx_address_type_created_by;
DROP INDEX IF EXISTS idx_address_type_updated_by;
DROP INDEX IF EXISTS idx_address_wallet_id;
DROP INDEX IF EXISTS idx_address_address_type_id;
DROP INDEX IF EXISTS idx_address_address_state_type_id;
DROP INDEX IF EXISTS idx_address_book_entry_customer_id;
DROP INDEX IF EXISTS idx_address_book_entry_address_id;
DROP INDEX IF EXISTS idx_transactions_transaction_status_type_id;
DROP INDEX IF EXISTS idx_transaction_endpoint_transaction_id;
DROP INDEX IF EXISTS idx_transaction_endpoint_address_id;
DROP INDEX IF EXISTS idx_transaction_endpoint_transaction_endpoint_type_id;
DROP INDEX IF EXISTS idx_payment_request_address_id;
DROP INDEX IF EXISTS idx_visit_member_id;

/*4.2 Secondary indices (application-specific needs etc.)*/
/*4.2.1 Creation statements*/
CREATE INDEX idx_tabeli_nimi_veeru_nimi ON tabeli_nimi USING btree (veeru_nimi);

/*4.2.2 Removal statements*/
DROP INDEX IF EXISTS idx_tabeli_nimi_veeru_nimi;


/*5. DDL - Views*/
/*5.1 Creation statements*/
/*5.2 Removal statements*/

/*6. DDL - Functions*/
/*6.1 Regular functions*/
/*6.1.1 Creation statements*/
/*6.1.2 Removal statements*/
/*6.2 Trigger functions*/
/*6.2.1 Creation statements*/
/*6.2.2 Removal statements*/

/*7. DCL - Privileges*/
/*7.1 Precautionary statements*/
REVOKE ALL PRIVILEGES ON DATABASE bitplexus FROM public;
REVOKE ALL PRIVILEGES ON SCHEMA public FROM public;

/*7.2 Assignation statements*/
/*7.3 Revocation statements*/

/*8. DML - Management of initial data*/
/*8.1 Regular tables*/
/*8.1.1 Insertion statements*/
/*8.1.2 Deletion statements*/

/*8.2 Reference tables*/
/*8.2.1 Insertion statements*/
INSERT INTO role (role_id, code, name) VALUES (, , );

/*8.2.2 Deletion statements*/
TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE wallet_state_type CASCADE;
TRUNCATE TABLE address_type CASCADE;
TRUNCATE TABLE address_state_type CASCADE;
TRUNCATE TABLE transaction_status_type CASCADE;
TRUNCATE TABLE transaction_endpoint_type CASCADE;


/*9. Miscellaneous objects & operations*/
/*9.1 Anonymous code blocks*/
