
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-04-07 14:35:28*/


/*1. DML - Regular tables*/
/*1.1 Insertion statements*/
INSERT INTO member (, , ,) VALUES (, , ,);
INSERT INTO member (, , ,) VALUES (, , ,);
INSERT INTO member (, , ,) VALUES (, , ,);
INSERT INTO member (, , ,) VALUES (, , ,);
INSERT INTO member (, , ,) VALUES (, , ,);

INSERT INTO email_address (, , ,) VALUES (, , ,);
INSERT INTO email_address (, , ,) VALUES (, , ,);
INSERT INTO email_address (, , ,) VALUES (, , ,);
INSERT INTO email_address (, , ,) VALUES (, , ,);
INSERT INTO email_address (, , ,) VALUES (, , ,);

INSERT INTO phone_number (, , ,) VALUES (, , ,);
INSERT INTO phone_number (, , ,) VALUES (, , ,);
INSERT INTO phone_number (, , ,) VALUES (, , ,);
INSERT INTO phone_number (, , ,) VALUES (, , ,);
INSERT INTO phone_number (, , ,) VALUES (, , ,);

INSERT INTO member_email_address (, , ,) VALUES (, , ,);
INSERT INTO member_email_address (, , ,) VALUES (, , ,);
INSERT INTO member_email_address (, , ,) VALUES (, , ,);
INSERT INTO member_email_address (, , ,) VALUES (, , ,);
INSERT INTO member_email_address (, , ,) VALUES (, , ,);

INSERT INTO member_phone_number (, , ,) VALUES (, , ,);
INSERT INTO member_phone_number (, , ,) VALUES (, , ,);
INSERT INTO member_phone_number (, , ,) VALUES (, , ,);
INSERT INTO member_phone_number (, , ,) VALUES (, , ,);
INSERT INTO member_phone_number (, , ,) VALUES (, , ,);

INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);

INSERT INTO customer (, , ,) VALUES (, , ,);
INSERT INTO customer (, , ,) VALUES (, , ,);
INSERT INTO customer (, , ,) VALUES (, , ,);
INSERT INTO customer (, , ,) VALUES (, , ,);
INSERT INTO customer (, , ,) VALUES (, , ,);

INSERT INTO employee (, , ,) VALUES (, , ,);
INSERT INTO employee (, , ,) VALUES (, , ,);
INSERT INTO employee (, , ,) VALUES (, , ,);
INSERT INTO employee (, , ,) VALUES (, , ,);
INSERT INTO employee (, , ,) VALUES (, , ,);

INSERT INTO employee_role (, , ,) VALUES (, , ,);
INSERT INTO employee_role (, , ,) VALUES (, , ,);
INSERT INTO employee_role (, , ,) VALUES (, , ,);
INSERT INTO employee_role (, , ,) VALUES (, , ,);
INSERT INTO employee_role (, , ,) VALUES (, , ,);

INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Bitcoin', 'BTC', '฿', 600, f_bitcoin_calc_supply(), 'https://bitcoin.org/', 2009-01-03, 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Litecoin', 'LTC', 'Ł', 150, f_litecoin_calc_supply(), 'https://litecoin.org/', 2011-10-08, 3);
INSERT INTO currency (name, abbreviation, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Dash', 'DASH', 150, 5261810, 'https://www.dashpay.io/', 2014-01-19, 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Peercoin', 'PPC', 'Ᵽ', 600, 22212010, 'http://peercoin.net/', 2012-08-20, 3);
INSERT INTO currency (name, abbreviation, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Monero', 'XMR', 60, 7079487, 'https://getmonero.org/', 2014-04-18, 3);
INSERT INTO currency (name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_by) 
    VALUES ('Namecoin', 'NMC', 'ℕ', 600, 11269000, 'https://namecoin.info/', 2011-04-17, 3);

INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (1, 'BITCOIN_MAIN', 'Mainnet chain', '2009-01-03', true, 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (1, 'BITCOIN_TEST1', 'Testnet1 chain', '2010-10-19', false, 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (1, 'BITCOIN_TEST2', 'Testnet2 chain', '2011-02-02', false, 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (1, 'BITCOIN_TEST3', 'Testnet3 chain', '2012-04-13', true, 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (2, 'LITECOIN_MAIN', 'Mainnet chain', '2011-10-08', true, 3);
INSERT INTO chain (currency_id, code, name, started_on, is_operational, created_by) 
    VALUES (2, 'LITECOIN_TEST1', 'Testnet1 chain', '2011-10-05', true, 3);

INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);

INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (1, 'BITCOIN_P2PKH_MAIN', 'P2PKH address', '1', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (4, 'BITCOIN_P2PKH_TEST3_1', 'P2PKH address', 'm', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (4, 'BITCOIN_P2PKH_TEST3_2', 'P2PKH address', 'n', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (1, 'BITCOIN_P2SH_MAIN', 'P2SH address', '3', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (4, 'BITCOIN_P2SH_TEST3', 'P2SH address', '2', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (5, 'LITECOIN_P2PKH_MAIN', 'P2PKH address', 'L', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (6, 'LITECOIN_P2PKH_TEST1_1', 'P2PKH address', 'm', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (6, 'LITECOIN_P2PKH_TEST1_2', 'P2PKH address', 'n', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
    VALUES (5, 'LITECOIN_P2SH_MAIN', 'P2SH address', '3', 3);
INSERT INTO address_type (chain_id, code, name, leading_symbol, created_by) 
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