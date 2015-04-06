
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-04-04 00:00:34*/


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

INSERT INTO currency (name, abbreviation, symbol, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Bitcoin', 'BTC', '฿', 600, 21000000, );
INSERT INTO currency (name, abbreviation, symbol, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Litecoin', 'LTC', 'Ł', 150, 84000000, );
INSERT INTO currency (name, abbreviation, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Dash', 'DASH', 150, 22000000, );
INSERT INTO currency (name, abbreviation, symbol, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Peercoin', 'PPC', 'Ᵽ', 600, , );
INSERT INTO currency (name, abbreviation, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Monero', 'XMR', 60, );
INSERT INTO currency (name, abbreviation, symbol, block_interval, available_supply, website_url, launched_on, created_by) VALUES ('Namecoin', 'NMC', 'ℕ', 600, ,);

INSERT INTO chain (, , ,) VALUES (, , ,);
INSERT INTO chain (, , ,) VALUES (, , ,);
INSERT INTO chain (, , ,) VALUES (, , ,);
INSERT INTO chain (, , ,) VALUES (, , ,);
INSERT INTO chain (, , ,) VALUES (, , ,);

INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);

INSERT INTO address_type (, , ,) VALUES (, , ,);
INSERT INTO address_type (, , ,) VALUES (, , ,);
INSERT INTO address_type (, , ,) VALUES (, , ,);
INSERT INTO address_type (, , ,) VALUES (, , ,);
INSERT INTO address_type (, , ,) VALUES (, , ,);

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