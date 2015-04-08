
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-04-07 14:35:28*/


/*1. DML - Regular tables*/
/*1.1 Insertion statements*/
INSERT INTO member (member_id, username, password, registered_at) VALUES (1, 'james.bradford', '$2a$04$xBGWfmsud2aCjG1cXe8FEu8q4e2ZYBb4wigyJHlx/JC1bC9Cdg39q', '2015-03-19 09:15:37'); -- password (plain text): 'flattire' 
INSERT INTO member (member_id, username, password, registered_at) VALUES (2, 'ryan.hopkins', '$2a$04$DSkFW1csg6XKQD0Hmhqodu78sSmJOi/Uwi527roE9p5JOYqbgYjqy', '2015-03-19 16:52:03'); -- password (plain text): 'burnttoast'
INSERT INTO member (member_id, username, password, registered_at) VALUES (3, 'adam.wright', '$2a$04$NWxWWDw3kcp6Hfzzuz4WCeEpQ.I7ED4KLh03BBM/dDCvtrX/MxQn2', '2015-03-19 23:19:57'); -- password (plain text): 'redsharpie'
INSERT INTO member (member_id, username, password, registered_at) VALUES (4, 'garden_gnome', '$2a$04$hG8zh1OTw8vlcV5NbiTBAuCk.CrxDazF92vSXxt90YpLZ.xR2bvlO', '2015-03-24 13:06:51'); -- password (plain text): 'rustyfork'
INSERT INTO member (member_id, username, password, registered_at) VALUES (5, 'rebel_sloth', '$2a$04$Mi0tWLM34FwntMj6qovDn.WuzvVSeeLoEKXzaaJo1aCJrlwLFCOIS', '2015-03-26 18:42:39'); -- password (plain text): 'mouldyham'

INSERT INTO email_address (email_address_id, address, created_at) VALUES (1, 'james.bradford@bitplexus.com', '2015-03-19 09:15:37');
INSERT INTO email_address (email_address_id, address, created_at) VALUES (2, 'ryan.hopkins@bitplexus.com', '2015-03-19 16:52:03');
INSERT INTO email_address (email_address_id, address, created_at) VALUES (3, 'adam.wright@bitplexus.com', '2015-03-19 23:19:57');
INSERT INTO email_address (email_address_id, address, created_at) VALUES (4, 'g_gnome@mailinator.com', '2015-03-24 13:06:51');
INSERT INTO email_address (email_address_id, address, created_at) VALUES (5, 'rebelsloth@hotmail.com', '2015-03-26 18:42:39');
INSERT INTO email_address (email_address_id, address, created_at) VALUES (6, 'milosz.szczepanski@wp.pl', '2015-04-02 06:57:41');

INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (1, '44', '1415836225', '2015-03-19 09:33:47');
INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (2, '1', '2025479512', '2015-03-19 17:07:19');
INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (3, '44', '1415193781', '2015-03-19 23:31:05');
INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (4, '507', '3854091', '2015-03-24 13:52:38');
INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (5, '501', '7225196', '2015-04-17 15:22:46');
INSERT INTO phone_number (phone_number_id, country_code, subscriber_number, created_at) VALUES (6, '48', '220862914', '2015-04-02 08:07:53');

INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (1, 1, 1, TRUE, TRUE, '2015-03-19 09:15:37', '2015-03-19 09:29:59');
INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (2, 2, 2, TRUE, TRUE, '2015-03-19 16:52:03', '2015-03-19 17:01:25');
INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (3, 3, 3, TRUE, TRUE, '2015-03-19 23:19:57', '2015-03-19 23:26:31');
INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (4, 4, 4, FALSE, TRUE, '2015-03-24 13:06:51', NULL);
INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (5, 5, 5, FALSE, FALSE, '2015-03-26 18:42:39', '2015-04-02 06:57:41');
INSERT INTO member_email_address (member_email_address_id, member_id, email_address_id, is_verified, is_active, linked_at, updated_at) VALUES (6, 5, 6, TRUE, TRUE, '2015-04-02 06:57:41', '2015-04-02 07:25:02');

INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (1, 1, 1, TRUE, TRUE, '2015-03-19 09:33:47', '2015-03-19 09:42:20');
INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (2, 2, 2, TRUE, TRUE, '2015-03-19 17:07:19', '2015-03-19 17:15:33');
INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (3, 3, 3, TRUE, TRUE, '2015-03-19 23:31:05', '2015-03-19 23:39:18');
INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (4, 4, 4, FALSE, FALSE, '2015-03-24 13:52:38', '2015-04-17 15:22:46');
INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (5, 4, 5, FALSE, TRUE, '2015-04-17 15:22:46', NULL);
INSERT INTO member_phone_number (member_phone_number_id, member_id, phone_number_id, is_verified, is_active, linked_at, updated_at) VALUES (6, 5, 6, TRUE, TRUE, '2015-04-02 08:07:53', '2015-04-02 08:24:05');

INSERT INTO person (person_id, first_name, last_name, created_at) VALUES (1, 'James', 'Bradford', '2015-03-19 09:15:37');
INSERT INTO person (person_id, first_name, last_name, created_at) VALUES (2, 'Ryan', 'Hopkins', '2015-03-19 16:52:03');
INSERT INTO person (person_id, first_name, last_name, created_at) VALUES (3, 'Adam', 'Wright', '2015-03-19 23:19:57');
INSERT INTO person (person_id, first_name, last_name, created_at) VALUES (4, 'John', 'Doe', '2015-03-24 13:06:51');
INSERT INTO person (person_id, first_name, last_name, created_at) VALUES (5, 'Miłosz', 'Szczepański', '2015-03-26 18:42:39');

INSERT INTO customer (customer_id, created_at) VALUES (4, '2015-03-24 13:06:51');
INSERT INTO customer (customer_id, created_at) VALUES (5, '2015-03-26 18:42:39');

INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (1, '1976-10-29', '', '2015-03-02', '2015-03-19 09:15:37');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (2, '1982-03-12', '', '2015-03-13', '2015-03-19 16:52:03');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (3, '1978-09-05', '', '2015-03-02', '2015-03-19 23:19:57');

INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (1, 1, 4, '');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (2, 1, 3, '');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (3, 2, 2, '');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (4, 3, 1, '');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (5, 3, 2, '');

INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Bitcoin', 'BTC', '฿', 600, f_bitcoin_calc_supply(), 'https://bitcoin.org/', 2009-01-03, '', 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Litecoin', 'LTC', 'Ł', 150, f_litecoin_calc_supply(), 'https://litecoin.org/', 2011-10-08, '', 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Dash', 'DASH', NULL, 150, 5261810, 'https://www.dashpay.io/', 2014-01-19, '', 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Peercoin', 'PPC', 'Ᵽ', 600, 22212010, 'http://peercoin.net/', 2012-08-20, '', 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Monero', 'XMR', NULL, 60, 7079487, 'https://getmonero.org/', 2014-04-18, '', 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) 
    VALUES ('Namecoin', 'NMC', 'ℕ', 600, 11269000, 'https://namecoin.info/', 2011-04-17, '', 3);

INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (1, 'BITCOIN_MAIN', 'Mainnet chain', '2009-01-03', TRUE, '', 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (1, 'BITCOIN_TEST1', 'Testnet1 chain', '2010-10-19', FALSE, '', 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (1, 'BITCOIN_TEST2', 'Testnet2 chain', '2011-02-02', FALSE, '', 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (1, 'BITCOIN_TEST3', 'Testnet3 chain', '2012-04-13', TRUE, '', 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (2, 'LITECOIN_MAIN', 'Mainnet chain', '2011-10-08', TRUE, '', 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_at, created_by) 
    VALUES (2, 'LITECOIN_TEST1', 'Testnet1 chain', '2011-10-05', TRUE, '', 3);

INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);

INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (1, 'BITCOIN_P2PKH_MAIN', 'P2PKH address', '1', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (4, 'BITCOIN_P2PKH_TEST3_1', 'P2PKH address', 'm', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (4, 'BITCOIN_P2PKH_TEST3_2', 'P2PKH address', 'n', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (1, 'BITCOIN_P2SH_MAIN', 'P2SH address', '3', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (4, 'BITCOIN_P2SH_TEST3', 'P2SH address', '2', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (5, 'LITECOIN_P2PKH_MAIN', 'P2PKH address', 'L', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (6, 'LITECOIN_P2PKH_TEST1_1', 'P2PKH address', 'm', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (6, 'LITECOIN_P2PKH_TEST1_2', 'P2PKH address', 'n', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (5, 'LITECOIN_P2SH_MAIN', 'P2SH address', '3', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_at, created_by) 
    VALUES (6, 'LITECOIN_P2SH_TEST1', 'P2SH address', '2', 3);

INSERT INTO address (, , ,) VALUES (, , ,);
INSERT INTO address (, , ,) VALUES (, , ,);
INSERT INTO address (, , ,) VALUES (, , ,);
INSERT INTO address (, , ,) VALUES (, , ,);
INSERT INTO address (, , ,) VALUES (, , ,);

INSERT INTO address_book_entry (, , ,) VALUES (, , ,);
INSERT INTO address_book_entry (, , ,) VALUES (, , ,);
INSERT INTO address_book_entry (, , ,) VALUES (, , ,);
INSERT INTO address_book_entry (, , ,) VALUES (, , ,);
INSERT INTO address_book_entry (, , ,) VALUES (, , ,);

INSERT INTO transactions (, , ,) VALUES (, , ,);
INSERT INTO transactions (, , ,) VALUES (, , ,);
INSERT INTO transactions (, , ,) VALUES (, , ,);
INSERT INTO transactions (, , ,) VALUES (, , ,);
INSERT INTO transactions (, , ,) VALUES (, , ,);

INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);
INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);
INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);
INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);
INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);

INSERT INTO payment_request (, , ,) VALUES (, , ,);
INSERT INTO payment_request (, , ,) VALUES (, , ,);
INSERT INTO payment_request (, , ,) VALUES (, , ,);
INSERT INTO payment_request (, , ,) VALUES (, , ,);
INSERT INTO payment_request (, , ,) VALUES (, , ,);

INSERT INTO visit (, , ,) VALUES (, , ,);
INSERT INTO visit (, , ,) VALUES (, , ,);
INSERT INTO visit (, , ,) VALUES (, , ,);
INSERT INTO visit (, , ,) VALUES (, , ,);
INSERT INTO visit (, , ,) VALUES (, , ,);

/*1.2 Deletion statements*/
TRUNCATE TABLE member CASCADE;
TRUNCATE TABLE email_address CASCADE;
TRUNCATE TABLE phone_number CASCADE;
TRUNCATE TABLE member_email_address CASCADE;
TRUNCATE TABLE member_phone_number CASCADE;
TRUNCATE TABLE person CASCADE;
TRUNCATE TABLE customer CASCADE;
TRUNCATE TABLE employee CASCADE;
TRUNCATE TABLE employee_role CASCADE;
TRUNCATE TABLE currency CASCADE;
TRUNCATE TABLE chain CASCADE;
TRUNCATE TABLE wallet CASCADE;
TRUNCATE TABLE address_type CASCADE;
TRUNCATE TABLE address CASCADE;
TRUNCATE TABLE address_book_entry CASCADE;
TRUNCATE TABLE transactions CASCADE;
TRUNCATE TABLE transaction_endpoint CASCADE;
TRUNCATE TABLE payment_request CASCADE;
TRUNCATE TABLE visit CASCADE;


/*2. DML - Reference tables*/
/*2.1 Insertion statements*/
INSERT INTO role (role_id, code, name) VALUES (1, 'WEB_EDITOR', 'Web Editor');
INSERT INTO role (role_id, code, name) VALUES (2, 'SUPPORT_ENGINEER', 'Customer Support Engineer');
INSERT INTO role (role_id, code, name) VALUES (3, 'DATABASE_MANAGER', 'Database Manager');
INSERT INTO role (role_id, code, name) VALUES (4, 'DATABASE_ADMIN', 'Database Administrator');

INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (1, 'INITIALIZED', 'Initialized');
INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (2, 'ACTIVE', 'Active');
INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (3, 'ARCHIVED', 'Archived');
INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (4, 'DELETED', 'Deleted');

INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (1, 'ALLOCATED', 'Allocated');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (2, 'ACTIVE', 'Active');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (3, 'USED', 'Used');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (4, 'HIDDEN', 'Hidden');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (5, 'DELETED', 'Deleted');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (6, 'NOT_APPLICABLE', 'n/a');

INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (1, 'ASSEMBLED', 'Assembled');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (2, 'UNCONFIRMED', 'Unconfirmed');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (3, 'CONFIRMED', 'Confirmed');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (4, 'COMPLETED', 'Completed');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (5, 'DETACHED', 'Detached');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (6, 'FAILED', 'Failed');

INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (1, 'INPUT', 'Input');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (2, 'OUTPUT_MAIN', 'Output');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (3, 'OUTPUT_CHANGE', 'Output (change)');

/*2.2 Deletion statements*/
TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE wallet_state_type CASCADE;
TRUNCATE TABLE address_state_type CASCADE;
TRUNCATE TABLE transaction_status_type CASCADE;
TRUNCATE TABLE transaction_endpoint_type CASCADE;