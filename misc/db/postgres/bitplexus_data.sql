
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-04-15 12:27:44*/


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

INSERT INTO customer (customer_id, created_at) VALUES (1, '2015-03-19 09:15:37');
INSERT INTO customer (customer_id, created_at) VALUES (2, '2015-03-19 16:52:03');
INSERT INTO customer (customer_id, created_at) VALUES (3, '2015-03-19 23:19:57');
INSERT INTO customer (customer_id, created_at) VALUES (4, '2015-03-24 13:06:51');
INSERT INTO customer (customer_id, created_at) VALUES (5, '2015-03-26 18:42:39');

INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (1, '1976-10-29', '', '2015-03-02', '2015-03-19 09:15:37');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (2, '1982-03-12', '', '2015-03-13', '2015-03-19 16:52:03');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (3, '1978-09-18', '', '2015-03-02', '2015-03-19 23:19:57');

INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (1, 1, 4, '2015-03-19 09:19:43');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (2, 1, 3, '2015-03-19 09:19:43');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (3, 2, 2, '2015-03-19 16:57:10');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (4, 3, 1, '2015-03-19 23:22:58');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (5, 3, 2, '2015-03-19 23:22:58');

INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (1, 'Bitcoin', 'BTC', '฿', 600, f_bitcoin_calc_supply(), 'https://bitcoin.org/', '2009-01-03', '2015-03-19 23:48:41', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (2, 'Litecoin', 'LTC', 'Ł', 150, f_litecoin_calc_supply(), 'https://litecoin.org/', '2011-10-08', '2015-03-20 00:34:20', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (3, 'Dash', 'DASH', NULL, 150, 5261810, 'https://www.dashpay.io/', '2014-01-19', '2015-03-20 01:21:36', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (4, 'Peercoin', 'PPC', 'Ᵽ', 600, 22212010, 'http://peercoin.net/', '2012-08-20', '2015-03-20 01:57:18', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (5, 'Monero', 'XMR', NULL, 60, 7079487, 'https://getmonero.org/', '2014-04-18', '2015-03-20 02:30:54', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (6, 'Namecoin', 'NMC', 'ℕ', 600, 11269000, 'https://namecoin.info/', '2011-04-17', '2015-03-20 02:59:07', 3);

INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (1, 1, 'BITCOIN_MAIN', 'Mainnet chain', '2009-01-03', TRUE, '2015-03-19 23:52:13', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (2, 1, 'BITCOIN_TEST1', 'Testnet1 chain', '2010-10-19', FALSE, '2015-03-19 23:54:30', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (3, 1, 'BITCOIN_TEST2', 'Testnet2 chain', '2011-02-02', FALSE, '2015-03-19 23:56:55', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (4, 1, 'BITCOIN_TEST3', 'Testnet3 chain', '2012-04-13', TRUE, '2015-03-19 23:59:07', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (5, 2, 'LITECOIN_MAIN', 'Mainnet chain', '2011-10-08', TRUE, '2015-03-20 00:40:26', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (6, 2, 'LITECOIN_TEST1', 'Testnet1 chain', '2011-10-05', FALSE, '2015-03-20 00:43:41', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, is_operational, created_at, created_by) VALUES (7, 2, 'LITECOIN_TEST3', 'Testnet3 chain', '2013-04-08', TRUE, '2015-03-20 00:45:58', 3);

INSERT INTO wallet (wallet_id, customer_id, name, created_at, updated_at) VALUES (1, 4, 'Garden gnome''s personal wallet', '2015-03-24 00:00:00', '2015-03-24 00:00:00'); '2015-03-24 13:06:51' to '2015-03-24 13:52:38'
INSERT INTO wallet (wallet_id, customer_id, name, created_at, updated_at) VALUES (2, 4, 'Garden gnome''s merchant wallet', '2015-03-24 00:00:00', '2015-03-24 00:00:00');
INSERT INTO wallet (wallet_id, customer_id, name, created_at, updated_at) VALUES (3, 5, 'Miłosz''s transactional wallet', '2015-03-24 00:00:00', '2015-03-24 00:00:00');
INSERT INTO wallet (wallet_id, customer_id, name, created_at, updated_at) VALUES (4, 5, 'Miłosz''s savings (short) wallet', '2015-03-24 00:00:00', '2015-03-24 00:00:00');
INSERT INTO wallet (wallet_id, customer_id, name, created_at, updated_at) VALUES (5, 5, 'Miłosz''s savings (long) wallet', '2015-03-24 00:00:00', '2015-03-24 00:00:00');

INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (1, 1, 'BITCOIN_P2PKH_MAIN', 'P2PKH address', '1', '2015-03-20 00:06:21', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (2, 4, 'BITCOIN_P2PKH_TEST3_1', 'P2PKH address', 'm', '2015-03-20 00:13:49', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (3, 4, 'BITCOIN_P2PKH_TEST3_2', 'P2PKH address', 'n', '2015-03-20 00:15:16', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (4, 1, 'BITCOIN_P2SH_MAIN', 'P2SH address', '3', '2015-03-20 00:22:30', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (5, 4, 'BITCOIN_P2SH_TEST3', 'P2SH address', '2', '2015-03-20 00:25:58', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (6, 5, 'LITECOIN_P2PKH_MAIN', 'P2PKH address', 'L', '2015-03-20 00:49:04', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (7, 7, 'LITECOIN_P2PKH_TEST3_1', 'P2PKH address', 'm', '2015-03-20 00:51:53', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (8, 7, 'LITECOIN_P2PKH_TEST3_2', 'P2PKH address', 'n', '2015-03-20 00:55:47', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (9, 5, 'LITECOIN_P2SH_MAIN', 'P2SH address', '3', '2015-03-20 01:01:22', 3);
INSERT INTO address_type (address_type_id, chain_id, code, name, leading_symbol, created_at, created_by) VALUES (10, 7, 'LITECOIN_P2SH_TEST3', 'P2SH address', '2', '2015-03-20 01:07:19', 3);

INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (1, 1, 2, 1, 'Loan repayments address #1', 'mvnAeskyHgLdx22xv8asRUnUdsguAiPZa5', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (2, 1, 2, 1, 'Change address #1', 'mq4gQZwpzW9fr4vhoPoANz7eMZKwTqx8rT', 0, TRUE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (3, 1, 2, 1, 'Beerware donations address #1', 'mtC2udBiEdNTTYcFFxRWnFpvbwqUba53Ez', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (4, 1, 7, 1, 'Reddit tips address #1', 'mifQrGFaN6Pm1ZCQbwufKJk8861qf44Znr', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (5, 1, 7, 1, 'Change address #1', 'miqiQLj49a6bSg53jwaMghGv1VSbLgD2ip', 0, TRUE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (6, 1, 7, 1, 'Twitter tips address #1', 'mz31qhAQjfVrxVhb1Rkn33jv3yURPtwpRz', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (7, 2, 3, 1, 'Sales revenue (Agora) address #1', 'n27yJtoKLe4iU71ofTSTSyM4TEGgRE5zDw', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (8, 2, 2, 1, 'Sales revenue (BlackBank) address #1', 'mjy7pYbSvwSvcf1wLQjWAvVPdbi4gs2ZVs', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (9, 2, 7, 1, 'Sales revenue (AlphaBay) address #1', 'mjAt7qXhKJpqyiNwqtJbpzXp8uvt3GvRui', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (10, 2, 7, 1, 'Change address #1', 'my6S2tDC6K8rbCRV8RJvUG4v6NdyDpYMKb', 0, '0000-00-00 00:00:00', TRUE, '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (11, 2, 8, 1, 'Sales revenue (over-the-counter) address #1', 'n22hJfEwwExb8hMxeoxzaoGNAF8uCZxBWP', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (12, 3, 2, 1, 'Monthly scholarship address #1', 'muZgscXPkRJoFNUALZKKqyX8YzKhpkXc5b', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (13, 3, 2, 1, 'Change address #1', 'mtygbF7okvRQsBaQKrRTdMw9kwRY9e8QJD', 0, '0000-00-00 00:00:00', TRUE, '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (14, 3, 2, 1, 'Change address #2', 'mk3uoZqzB71XeogS98XTnKsKseYJYTqogv', 0, '0000-00-00 00:00:00', TRUE, '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (15, 3, 2, 1, 'Payments from friends/family address #1', 'mhsM4EZDz5zZcZsR4KxQTaWUp3jTpK1Fq9', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (16, 3, 2, 1, 'Payments from abroad address #1', 'mi6S8dC5kvj435D4Wr1mVQ8RVFDHh8n8DV', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (17, 3, 8, 1, 'Freelance earnings address #1', 'n3PMhKKZtLPWmFSt3t1qJ2HovozEujp9N7', 0, FALSE, '00 00-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (18, 3, 7, 1, 'Dividend payouts address #1', 'mxfN2XEo52abd9vM8dNkRimyTxcdPGApvx', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (19, 3, 7, 1, 'Change address #1', 'mr1CELTrftry2UTxAkEApeB3cA3qxAkA9B', 0, TRUE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (20, 4, 2, 1, 'Short-term deposit (0.1 tBTC) address #1', 'miH27nBXbKuhiie2EndaL7HpyEU3Pfjmu4', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (21, 4, 2, 1, 'Short-term deposit (0.2 tBTC) address #2', 'muuxD5NeoQnhq5muyizhYGC62AMW9Za2Eg', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (22, 4, 7, 1, 'Short-term deposit (100 tLTC) address #1', 'mkL5CvwXmsoDT6QMLJRfDBbhr1LwGeGpSN', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (23, 5, 2, 1, 'Medium-term deposit (0.3 tBTC) address #1', 'mpYuzvbc1QZCzdZnBt5zLDFLXFa64UyGJd', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (24, 5, 2, 1, 'Long-term deposit (0.5 tBTC) address #1', 'muo4sC6q5QiDAvqbxZ71gaU1q99hzgZCG6', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (25, 5, 7, 1, 'Medium-term deposit (250 tLTC) address #1', 'mqQSkX987x665dPfr3inveo6pQstB3j9XU', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (26, 5, 8, 1, 'Long-term deposit (400 tLTC) address #1', 'n2cRjaRK1kwsW9rmr7ck6kzJsdkATfYYbc', 0, FALSE, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (27, NULL, 2, 6, NULL, 'mw81EALsE1LEaUK8tHzNrBoNn2mWojFHFR', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (28, NULL, 2, 6, NULL, 'miHzjx3GAQLUGU1aHMifeo4XLtez6Nr922', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (29, NULL, 8, 6, NULL, 'n3XYC7QLMAgXAU1FGXmdeK7o8KstMuWN3s', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (30, NULL, 2, 6, NULL, 'mpCJ4ZzRbdi2j6GZ8t1b5fk9AYibJiMXi9', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (31, NULL, 2, 6, NULL, 'mzHTUWkWCtV1ZsbV3TLuHk3mjEFVsp8Din', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (32, NULL, 2, 6, NULL, 'mn8Jde1ie4RFUFxGkJ51Juh5ZdnG9eznLY', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (33, NULL, 7, 6, NULL, 'mq1rdtD6x3hrh89L2KKjJ25ta9FJDeHZow', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, enocded_form, balance, is_change, indexed_at, updated_at) VALUES (34, NULL, 7, 6, NULL, 'mkiQ5B9KSqJX2spb6Zs4vjBsUWSrt9oc8r', NULL, NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (1, 4, 27, 'BTC-e (exchange) deposit address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (2, 4, 28, 'Colin Fletcher''s personal address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (3, 4, 29, 'Luca Bianchi''s personal address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (4, 5, 30, 'Kraken (exchange) deposit address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (5, 5, 31, 'Shapely (gym) membership fee address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (6, 5, 32, 'Aaron Howell''s personal address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (7, 5, 33, 'Clark Poole''s personal address #1', '0000-00-00 00:00:00');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (8, 5, 34, 'Garrett Beck''s personal address #1', '0000-00-00 00:00:00');

/*Table 'transactions': data parsed automatically (via lazy loading) from the block chain.*/

/*Table 'transaction_endpoint': data parsed automatically (via lazy loading) from the block chain.*/

INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (1, 1, 0.2, 'Please note that the emergency loan you have requested (0.15 tBTC + 0.05 tBTC interest) is due for repayment on April 18, 2015.', '2015-03-25 15:41:06');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (2, 3, 0.001, 'Cookie & ice cream fund (donate 0,001 tBTC)', '2015-03-25 15:41:06');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (3, 12, 0.35, 'Academic scholarship payment for April, 2015', '2015-03-25 15:41:06');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (4, 15, 0.007, 'Your share of the restaurant bill (The Dubliner, 10.04.2015)', '2015-03-25 15:41:06');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (5, 17, 25, 'Licensing fee for web template (CageyCoalecanth.com)', '2015-03-25 15:41:06');

INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (1, 1, '153.88.169.202', '0000-00-00 00:00:00');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (2, 2, '90.171.250.38', '0000-00-00 00:00:00');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (3, 3, '153.140.76.115', '0000-00-00 00:00:00');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (4, 4, '215.206.32.149', '0000-00-00 00:00:00');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (5, 5, '82.45.227.253', '0000-00-00 00:00:00');

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