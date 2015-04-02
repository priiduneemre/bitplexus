
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-03-31 18:31:25*/


/*1. DML - Regular tables*/
/*1.1 Insertion statements*/
INSERT INTO member (, , ,) VALUES (, , ,);
INSERT INTO email_address (, , ,) VALUES (, , ,);
INSERT INTO phone_number (, , ,) VALUES (, , ,);
INSERT INTO member_email_address (, , ,) VALUES (, , ,);
INSERT INTO member_phone_number (, , ,) VALUES (, , ,);
INSERT INTO person (, , ,) VALUES (, , ,);
INSERT INTO customer (, , ,) VALUES (, , ,);
INSERT INTO employee (, , ,) VALUES (, , ,);
INSERT INTO employee_role (, , ,) VALUES (, , ,);
INSERT INTO currency (, , ,) VALUES (, , ,);
INSERT INTO chain (, , ,) VALUES (, , ,);
INSERT INTO wallet (, , ,) VALUES (, , ,);
INSERT INTO address_type (, , ,) VALUES (, , ,);
INSERT INTO address (, , ,) VALUES (, , ,);
INSERT INTO address_book_entry (, , ,) VALUES (, , ,);
INSERT INTO transactions (, , ,) VALUES (, , ,);
INSERT INTO transaction_endpoint (, , ,) VALUES (, , ,);
INSERT INTO payment_request (, , ,) VALUES (, , ,);
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
INSERT INTO role (role_id, code, name) VALUES (1, 'SUPPORT_ENGINEER', 'Support Engineer');
INSERT INTO role (role_id, code, name) VALUES (1, 'EDITOR', 'Editor');
INSERT INTO role (role_id, code, name) VALUES (2, 'DATABASE_MANAGER', 'Database Manager');
INSERT INTO role (role_id, code, name) VALUES (2, 'DATABASE_ADMINISTRATOR', 'Database Administrator');


INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (1, 'INITIALIZED', 'Initialized');
INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (2, 'ACTIVE', 'Active');
INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (3, 'ARCHIVED', 'Archived');

INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (1, 'ALLOCATED', 'Allocated');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (2, 'ACTIVE', 'Active');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (3, 'USED', 'Used');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (4, 'HIDDEN', 'Hidden');
INSERT INTO address_state_type (address_state_type_id, code, name) VALUES (5, 'NOT_APPLICABLE', 'n/a');

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