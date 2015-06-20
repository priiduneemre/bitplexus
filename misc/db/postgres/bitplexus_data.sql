
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-06-19 13:36:26*/


/*1. DML - Regular tables*/
/*1.1 Insertion statements*/
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (1, 'james.bradford', '$2a$04$xBGWfmsud2aCjG1cXe8FEu8q4e2ZYBb4wigyJHlx/JC1bC9Cdg39q', 'james.bradford@bitplexus.com', '441415836225', '2015-03-19 09:15:37'); -- password (plain text): 'flattire' 
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (2, 'ryan.hopkins', '$2a$04$DSkFW1csg6XKQD0Hmhqodu78sSmJOi/Uwi527roE9p5JOYqbgYjqy', 'ryan.hopkins@bitplexus.com', '5511968310447', '2015-03-19 16:52:03'); -- password (plain text): 'burnttoast'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (3, 'adam.wright', '$2a$04$NWxWWDw3kcp6Hfzzuz4WCeEpQ.I7ED4KLh03BBM/dDCvtrX/MxQn2', 'adam.wright@bitplexus.com', '441415193781', '2015-03-19 23:19:57'); -- password (plain text): 'redsharpie'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (4, 'garden_gnome', '$2a$04$hG8zh1OTw8vlcV5NbiTBAuCk.CrxDazF92vSXxt90YpLZ.xR2bvlO', 'g_gnome@mailinator.com', '5073854091', '2015-03-24 13:06:51'); -- password (plain text): 'rustyfork'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (5, 'rebel_sloth', '$2a$04$Mi0tWLM34FwntMj6qovDn.WuzvVSeeLoEKXzaaJo1aCJrlwLFCOIS', 'milosz.szczepanski@wp.pl', '48220862914', '2015-03-26 18:42:39'); -- password (plain text): 'mouldyham'

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

INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (1, '1976-10-29', 'GB37CITI08320034125984', '2015-03-02', '2015-03-19 09:15:37');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (2, '1982-03-14', 'BR6033870163000011001084395P1', '2015-03-13', '2015-03-19 16:52:03');
INSERT INTO employee (employee_id, born_on, iban, employed_on, created_at) VALUES (3, '1978-09-18', 'GB81NRNB40642573908441', '2015-03-02', '2015-03-19 23:19:57');

INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (1, 1, 4, '2015-03-19 09:19:43');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (2, 1, 3, '2015-03-19 09:19:43');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (3, 2, 2, '2015-03-19 16:57:10');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (4, 3, 1, '2015-03-19 23:22:58');
INSERT INTO employee_role (employee_role_id, employee_id, role_id, assigned_at) VALUES (5, 3, 2, '2015-03-19 23:22:58');

INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (1, 'Bitcoin', 'BTC', '฿', 600, f_calc_bitcoin_supply(359529), 'https://bitcoin.org/', '2009-01-03', '2015-03-19 23:48:41', 3);
INSERT INTO currency (currency_id, name, abbreviation, symbol, block_time, available_supply, website_url, launched_on, created_at, created_by) VALUES (2, 'Litecoin', 'LTC', 'Ł', 150, f_calc_litecoin_supply(792946), 'https://litecoin.org/', '2011-10-08', '2015-03-20 00:34:20', 3);
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

INSERT INTO wallet (wallet_id, customer_id, wallet_state_type_id, name, created_at, updated_at) VALUES (1, 4, 2, 'Garden gnome''s personal wallet', '2015-03-24 13:58:03', '2015-03-24 14:09:36');
INSERT INTO wallet (wallet_id, customer_id, wallet_state_type_id, name, created_at, updated_at) VALUES (2, 4, 2, 'Garden gnome''s merchant wallet', '2015-03-24 14:07:52', NULL);
INSERT INTO wallet (wallet_id, customer_id, wallet_state_type_id, name, created_at, updated_at) VALUES (3, 5, 2, 'Miłosz''s transactional wallet', '2015-03-26 20:03:17', NULL);
INSERT INTO wallet (wallet_id, customer_id, wallet_state_type_id, name, created_at, updated_at) VALUES (4, 5, 2, 'Miłosz''s savings (short) wallet', '2015-03-26 20:11:40', '2015-03-26 20:35:21');
INSERT INTO wallet (wallet_id, customer_id, wallet_state_type_id, name, created_at, updated_at) VALUES (5, 5, 2, 'Miłosz''s savings (long) wallet', '2015-03-26 20:26:08', '2015-03-26 20:37:44');

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

INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (1, 1, 2, 3, 'Loan repayments address #1', 'mvnAeskyHgLdx22xv8asRUnUdsguAiPZa5', 0, '2015-03-24 14:13:29', '2015-06-02 15:30:44');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (2, 1, 2, 2, 'Change address #1', 'mq4gQZwpzW9fr4vhoPoANz7eMZKwTqx8rT', 0.0699, '2015-06-02 15:30:44', '2015-06-02 16:31:07');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (3, 1, 2, 2, 'Beerware donations address #1', 'mtC2udBiEdNTTYcFFxRWnFpvbwqUba53Ez', 0.002, '2015-03-24 14:18:35', '2015-06-02 12:23:59');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (4, 1, 7, 1, 'Reddit tips address #1', 'mifQrGFaN6Pm1ZCQbwufKJk8861qf44Znr', 0, '2015-03-24 14:26:23', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (5, 1, 7, 1, 'Twitter tips address #1', 'mz31qhAQjfVrxVhb1Rkn33jv3yURPtwpRz', 0, '2015-03-24 14:30:51', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (6, 2, 3, 1, 'Sales revenue (Agora) address #1', 'n27yJtoKLe4iU71ofTSTSyM4TEGgRE5zDw', 0, '2015-03-24 14:39:17', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (7, 2, 2, 1, 'Sales revenue (BlackBank) address #1', 'mjy7pYbSvwSvcf1wLQjWAvVPdbi4gs2ZVs', 0, '2015-03-24 14:44:35', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (8, 2, 7, 1, 'Sales revenue (AlphaBay) address #1', 'mjAt7qXhKJpqyiNwqtJbpzXp8uvt3GvRui', 0, '2015-03-24 14:50:48', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (9, 2, 8, 1, 'Sales revenue (over-the-counter) address #1', 'n22hJfEwwExb8hMxeoxzaoGNAF8uCZxBWP', 0, '2015-03-24 14:57:04', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (10, 3, 2, 3, 'Monthly scholarship address #1', 'mogE6d26pwbwwG8LYZo9MMkH7afatF7NHU', 0, '2015-05-31 06:48:53', '2015-06-02 17:56:38');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (11, 3, 2, 2, 'Change address #1', 'mmqjCVEky9HEmnaQWeyQCtw9jCNxYonH3M', 0.1399, '2015-06-02 17:56:38', '2015-06-02 20:04:58');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (12, 3, 2, 2, 'Payments from friends/family address #1', 'mhsM4EZDz5zZcZsR4KxQTaWUp3jTpK1Fq9', 0.007, '2015-05-31 07:05:19', '2015-06-02 12:33:14');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (13, 3, 2, 1, 'Payments from abroad address #1', 'mi6S8dC5kvj435D4Wr1mVQ8RVFDHh8n8DV', 0, '2015-05-31 07:11:36', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (14, 3, 7, 3, 'Freelance earnings address #1', 'mxzsZRCAzMQKD8BpgPhfbKywSQERF7eLEr', 0, '2015-05-31 07:19:20', '2015-06-02 16:08:15');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (15, 3, 7, 2, 'Change address #1', 'miqiQLj49a6bSg53jwaMghGv1VSbLgD2ip', 6.999, '2015-06-02 16:08:15', '2015-06-02 16:12:08');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (16, 3, 7, 1, 'Dividend payouts address #1', 'mxfN2XEo52abd9vM8dNkRimyTxcdPGApvx', 0, '2015-05-31 07:24:13', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (17, 4, 2, 2, 'Short-term deposit (0.1 tBTC) address #1', 'miH27nBXbKuhiie2EndaL7HpyEU3Pfjmu4', 0.1, '2015-05-31 07:30:55', '2015-06-02 15:30:05');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (18, 4, 2, 2, 'Short-term deposit (0.2 tBTC) address #2', 'muuxD5NeoQnhq5muyizhYGC62AMW9Za2Eg', 0.2, '2015-05-31 07:38:29', '2015-06-02 14:48:31');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (19, 4, 7, 2, 'Short-term deposit (100 tLTC) address #1', 'mkL5CvwXmsoDT6QMLJRfDBbhr1LwGeGpSN', 100, '2015-05-31 07:44:01', '2015-06-02 13:07:08');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (20, 5, 2, 2, 'Medium-term deposit (0.3 tBTC) address #1', 'mpYuzvbc1QZCzdZnBt5zLDFLXFa64UyGJd', 0.3, '2015-05-31 07:48:47', '2015-06-02 14:48:31');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (21, 5, 2, 2, 'Long-term deposit (0.5 tBTC) address #1', 'muo4sC6q5QiDAvqbxZ71gaU1q99hzgZCG6', 0.5, '2015-05-31 07:52:20', '2015-06-02 14:48:31');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (22, 5, 7, 2, 'Medium-term deposit (250 tLTC) address #1', 'mqQSkX987x665dPfr3inveo6pQstB3j9XU', 250, '2015-05-31 07:58:13', '2015-06-02 13:15:28');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (23, 5, 8, 2, 'Long-term deposit (400 tLTC) address #1', 'n2cRjaRK1kwsW9rmr7ck6kzJsdkATfYYbc', 400, '2015-05-31 08:04:30', '2015-06-02 13:20:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (24, NULL, 2, 6, NULL, 'mw81EALsE1LEaUK8tHzNrBoNn2mWojFHFR', NULL, '2015-03-24 15:28:01', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (25, NULL, 2, 6, NULL, 'miHzjx3GAQLUGU1aHMifeo4XLtez6Nr922', NULL, '2015-03-24 15:33:57', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (26, NULL, 8, 6, NULL, 'n3XYC7QLMAgXAU1FGXmdeK7o8KstMuWN3s', NULL, '2015-03-24 15:39:14', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (27, NULL, 2, 6, NULL, 'mxKsWkbnfTmCoJiUyEpKtH7KmwXnnpr3Mi', NULL, '2015-05-31 08:41:26', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (28, NULL, 2, 6, NULL, 'mzHTUWkWCtV1ZsbV3TLuHk3mjEFVsp8Din', NULL, '2015-05-31 08:47:32', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (29, NULL, 2, 6, NULL, 'mn8Jde1ie4RFUFxGkJ51Juh5ZdnG9eznLY', NULL, '2015-05-31 08:53:49', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (30, NULL, 7, 6, NULL, 'mq1rdtD6x3hrh89L2KKjJ25ta9FJDeHZow', NULL, '2015-05-31 09:00:51', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (31, NULL, 7, 6, NULL, 'mkiQ5B9KSqJX2spb6Zs4vjBsUWSrt9oc8r', NULL, '2015-05-31 09:06:13', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (32, NULL, 2, 6, NULL, 'mmhtFgTWbM8ST1Uk7zLudpXjLpJwEo84xd', NULL, '2015-06-02 10:21:56', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (33, NULL, 2, 6, NULL, 'mx23ddBMZs1Gqs9fYq7PRFD68ipbircjBt', NULL, '2015-06-02 10:45:04', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (34, NULL, 2, 6, NULL, 'mveUs1uFXvmXg5siNiPiRtLAttiui1ijfo', NULL, '2015-06-02 10:51:49', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (35, NULL, 2, 6, NULL, 'mpQqcmnq36AjBx8evcotvKmosixavrXcQj', NULL, '2015-06-02 17:52:43', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (36, NULL, 2, 6, NULL, 'mtU6sLJJ3DFERn4i6VGNH8DYVPr27MKV95', NULL, '2015-06-02 10:59:17', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (37, NULL, 2, 6, NULL, 'muxnQysdwHDXxpkUQhy9CgRhFw1ewW6XDZ', NULL, '2015-06-02 13:28:53', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (38, NULL, 2, 6, NULL, 'mjHqxuUMjAxv8rbeNRjsF2dY6WQwGmPASc', NULL, '2015-06-02 13:31:00', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (39, NULL, 3, 6, NULL, 'n2EFkJ6mPd9ERcM3smBSfnUyR4xeJDZ2Rm', NULL, '2015-06-02 13:33:10', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (40, NULL, 3, 6, NULL, 'n1diChR7gjYYSSiCg4GZnm97aqp1woDAVk', NULL, '2015-06-02 13:35:15', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (41, NULL, 7, 6, NULL, 'mx49eyKqA4PmR99fMqhzEPnaMUQR596F6d', NULL, '2015-06-02 15:16:23', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (42, NULL, 7, 6, NULL, 'mkkahgv3E44dxyfx3UMiEaciavTjxLQph6', NULL, '2015-06-02 13:07:05', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (43, NULL, 7, 6, NULL, 'mvZc7N9hMi27dx3WHDWv7sZL34MyR9MmJ2', NULL, '2015-06-02 13:14:25', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (44, NULL, 8, 6, NULL, 'n3BEh2hmryedNfvAywqd6eUZthqDYEqrXP', NULL, '2015-06-02 13:18:16', NULL);

INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (1, 4, 24, 'BTC-e (exchange) deposit address #1', '2015-03-24 15:28:01');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (2, 4, 25, 'Colin Fletcher''s personal address #1', '2015-03-24 15:33:57');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (3, 4, 26, 'Luca Bianchi''s personal address #1', '2015-03-24 15:39:14');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (4, 5, 27, 'Kraken (exchange) deposit address #1', '2015-05-31 08:41:26');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (5, 5, 28, 'Shapely (gym) membership fee address #1', '2015-05-31 08:47:32');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (6, 5, 29, 'Aaron Howell''s personal address #1', '2015-05-31 08:53:49');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (7, 5, 30, 'Clark Poole''s personal address #1', '2015-05-31 09:00:51');
INSERT INTO address_book_entry (address_book_entry_id, customer_id, address_id, label, created_at) VALUES (8, 5, 31, 'Garrett Beck''s personal address #1', '2015-05-31 09:06:13');

/*Table 'transactions': data parsed automatically (via lazy loading) from the block chain.*/
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (1, 2, 'd9e9c057-23b1-4b75-a9bc-a3f2dd729123', 'bc3789df89cba9f6f558d4b3dc5cdc93be88c74f5d4f105040ca9ddc767e3f4c', '2015-06-12 11:57:34', NULL, NULL, NULL, 308, 0.00010000, 244.26, 'Sample transaction to my friend Bob.', '2015-06-12 11:57:36', NULL);
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (2, 2, '95305bfa-10fb-11e5-9493-1697f925ec7b', '1d16061cea3d07065d34b499681d82251a3c979709156dfc8fe8f90a21ce64c9', '2015-06-12 12:06:19', NULL, NULL, NULL, 259, 0.00010000, 239.51, 'Alimony for that bitch Stacy.', '2015-06-12 12:06:21', NULL);
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (3, 2, 'e6fcf9a2-1119-11e5-9493-1697f925ec7b', '3a9e8318c9c0478ba6e892043c31a58335f67d0bde36022fdf2741395e053674', '2015-06-02 23:48:55', NULL, NULL, NULL, 673, 0.00000000, 240.78, 'Payment for the hit on Koolaid Man.', '2015-06-02 23:48:57', NULL);

/*Table 'transaction_endpoint': data parsed automatically (via lazy loading) from the block chain.*/
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (1, 1, 16, 1, '0.95', '2015-06-12 11:57:36');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (2, 1, 17, 2, '0.6', '2015-06-12 11:57:36');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (3, 1, 18, 3, '0.34', '2015-06-12 11:57:36');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (4, 2, 19, 1, '50.9', '2015-06-12 12:06:21');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (5, 2, 20, 2, '2', '2015-06-12 12:06:21');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (6, 2, 21, 3, '48.89', '2015-06-12 12:06:21');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (8, 3, 22, 1, '3.8', '2015-06-02 23:48:57');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (9, 3, 23, 2, '3.79', '2015-06-02 23:48:57');


INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (1, 1, 0.2, 'Please note that the emergency loan you have requested (0.15 tBTC + 0.05 tBTC interest) is due for repayment on June 3, 2015.', '2015-03-24 16:03:47');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (2, 3, 0.001, 'Cookie & ice cream fund (donate 0,001 tBTC)', '2015-03-24 16:10:22');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (3, 10, 0.35, 'Academic scholarship payment for June, 2015', '2015-05-31 09:32:15');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (4, 12, 0.007, 'Your share of the restaurant bill (The Dubliner, 30.05.2015)', '2015-05-31 09:38:06');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (5, 14, 25, 'Licensing fee for web template (CreativeCoelacanth.com)', '2015-05-31 09:44:50');

INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (1, 1, '197.84.169.202', '2015-03-19 09:15:37');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (2, 2, '90.171.250.38', '2015-03-19 16:52:03');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (3, 3, '153.140.76.115', '2015-03-19 23:19:57');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (4, 4, '215.206.32.149', '2015-03-24 13:06:51');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (5, 5, '82.45.227.253', '2015-03-26 18:42:39');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (6, 5, '82.45.227.253', '2015-05-31 06:27:44');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (7, 4, '215.206.32.149', '2015-06-02 15:11:26');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (8, 5, '82.45.227.253', '2015-06-02 16:05:12');
INSERT INTO visit (visit_id, member_id, ip_address, login_at) VALUES (9, 5, '82.45.227.253', '2015-06-02 17:31:48');

/*1.2 Deletion statements*/
TRUNCATE TABLE member CASCADE;
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

INSERT INTO wallet_state_type (wallet_state_type_id, code, name) VALUES (1, 'CREATED', 'Created');
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
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (5, 'FAILED', 'Failed');
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (6, 'EXTINCT', 'Extinct');

INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (1, 'INPUT', 'Input');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (2, 'OUTPUT_MAIN', 'Output');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (3, 'OUTPUT_CHANGE', 'Output (change)');

/*2.2 Deletion statements*/
TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE wallet_state_type CASCADE;
TRUNCATE TABLE address_state_type CASCADE;
TRUNCATE TABLE transaction_status_type CASCADE;
TRUNCATE TABLE transaction_endpoint_type CASCADE;