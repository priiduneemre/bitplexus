
/*Project:          Bitplexus - a proof-of-concept universal cryptocurrency wallet service (for Bitcoin, Litecoin etc.)*/
/*File description: DML statements for populating the application's database with seed data (optimized for PostgreSQL 9.4.1).*/
/*Author:           Priidu Neemre (priidu@neemre.com)*/
/*Last modified:    2015-07-10 19:55:53*/


/*1. DML - Regular tables*/
/*1.1 Insertion statements*/
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (1, 'james.bradford', '$2a$12$bZZL4w4y0/KepId3sTkI0u2HJ8.vxY10sjILdqfjIJEDDVH99Qiyq', 'james.bradford@bitplexus.com', '441415836225', '2015-03-19 09:15:37'); -- password (plain text): 'flattire' 
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (2, 'ryan.hopkins', '$2a$12$ETDTjZyqZ5QMrVq2KtY0BOLS8G.Vc21wvEeMKH0u7LdrOSix05Ga2', 'ryan.hopkins@bitplexus.com', '5511968310447', '2015-03-19 16:52:03'); -- password (plain text): 'burnttoast'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (3, 'adam.wright', '$2a$12$mq5bU747Db2sB89A2mHwb.UgTs5c8Z69.A.Zgz9ADIPiyw/4L1mwu', 'adam.wright@bitplexus.com', '441415193781', '2015-03-19 23:19:57'); -- password (plain text): 'redsharpie'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (4, 'garden_gnome', '$2a$12$lbTjjpgls62yox192paJpuHxrJJKnQNHzVeLMEX79/koi2c464IOm', 'g_gnome@mailinator.com', '5073854091', '2015-03-24 13:06:51'); -- password (plain text): 'rustyfork'
INSERT INTO member (member_id, username, password, email_address, phone_number, registered_at) VALUES (5, 'rebel_sloth', '$2a$12$lcfDUZGWXiMyxPXcM2Yiu.pWmzLyU.A3tZkaaUCEF/GQs2kzN/cD2', 'milosz.szczepanski@wp.pl', '48220862914', '2015-03-26 18:42:39'); -- password (plain text): 'mouldyham'

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

INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (1, 'Bitcoin', 'BTC', '฿', '2009-01-03', 600, 0.0001, 'https://bitcoin.org/', '2015-03-19 23:48:41');
INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (2, 'Litecoin', 'LTC', 'Ł', '2011-10-08', 150, 0.001, 'https://litecoin.org/', '2015-03-20 00:34:20');
INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (3, 'Dash', 'DASH', NULL, '2014-01-19', 150, 0.001, 'https://www.dashpay.io/', '2015-03-20 01:21:36');
INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (4, 'Peercoin', 'PPC', 'Ᵽ', '2012-08-20', 600, 0.01, 'http://peercoin.net/', '2015-03-20 01:57:18');
INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (5, 'Monero', 'XMR', NULL, '2014-04-18', 60, 0.01, 'https://getmonero.org/', '2015-03-20 02:30:54');
INSERT INTO currency (currency_id, name, abbreviation, symbol, launched_on, block_time, standard_fee, website_url, created_at) VALUES (6, 'Namecoin', 'NMC', 'ℕ', '2011-04-17', 600, 0.005, 'https://namecoin.info/', '2015-03-20 02:59:07');

INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (1, 1, 'BITCOIN_MAIN', 'Mainnet chain', '2009-01-03', f_estimate_btc_supply('2009-01-03', f_to_timestamp(CURRENT_TIMESTAMP(0)), TRUE, '2015-03-19 23:52:13', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (2, 1, 'BITCOIN_TEST1', 'Testnet1 chain', '2010-10-19', f_estimate_btc_supply('2010-10-19', f_to_timestamp(CURRENT_TIMESTAMP(0)), FALSE, '2015-03-19 23:54:30', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (3, 1, 'BITCOIN_TEST2', 'Testnet2 chain', '2011-02-02', f_estimate_btc_supply('2011-02-02', f_to_timestamp(CURRENT_TIMESTAMP(0)), FALSE, '2015-03-19 23:56:55', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (4, 1, 'BITCOIN_TEST3', 'Testnet3 chain', '2012-04-13', f_estimate_btc_supply('2012-04-13', f_to_timestamp(CURRENT_TIMESTAMP(0)), TRUE, '2015-03-19 23:59:07', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (5, 2, 'LITECOIN_MAIN', 'Mainnet chain', '2011-10-08', f_estimate_ltc_supply('2011-10-08', f_to_timestamp(CURRENT_TIMESTAMP(0)), TRUE, '2015-03-20 00:40:26', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (6, 2, 'LITECOIN_TEST1', 'Testnet1 chain', '2011-10-05', f_estimate_ltc_supply('2011-10-05', f_to_timestamp(CURRENT_TIMESTAMP(0)), FALSE, '2015-03-20 00:43:41', 3);
INSERT INTO chain (chain_id, currency_id, code, name, started_on, available_supply, is_operational, created_at, created_by) VALUES (7, 2, 'LITECOIN_TEST3', 'Testnet3 chain', '2013-04-08', f_estimate_ltc_supply('2013-04-08', f_to_timestamp(CURRENT_TIMESTAMP(0)), TRUE, '2015-03-20 00:45:58', 3);

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
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (2, 1, 2, 2, 'Change address #1', 'mq4gQZwpzW9fr4vhoPoANz7eMZKwTqx8rT', 0.0699, '2015-06-02 15:30:44', '2015-06-02 17:52:53');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (3, 1, 2, 2, 'Beerware donations address #1', 'mtC2udBiEdNTTYcFFxRWnFpvbwqUba53Ez', 0.002, '2015-03-24 14:18:35', '2015-06-02 12:33:14');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (4, 1, 7, 1, 'Reddit tips address #1', 'mifQrGFaN6Pm1ZCQbwufKJk8861qf44Znr', 0, '2015-03-24 14:26:23', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (5, 1, 7, 1, 'Twitter tips address #1', 'mz31qhAQjfVrxVhb1Rkn33jv3yURPtwpRz', 0, '2015-03-24 14:30:51', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (6, 2, 3, 1, 'Sales revenue (Agora) address #1', 'n27yJtoKLe4iU71ofTSTSyM4TEGgRE5zDw', 0, '2015-03-24 14:39:17', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (7, 2, 2, 1, 'Sales revenue (BlackBank) address #1', 'mjy7pYbSvwSvcf1wLQjWAvVPdbi4gs2ZVs', 0, '2015-03-24 14:44:35', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (8, 2, 7, 1, 'Sales revenue (AlphaBay) address #1', 'mjAt7qXhKJpqyiNwqtJbpzXp8uvt3GvRui', 0, '2015-03-24 14:50:48', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (9, 2, 8, 1, 'Sales revenue (over-the-counter) address #1', 'n22hJfEwwExb8hMxeoxzaoGNAF8uCZxBWP', 0, '2015-03-24 14:57:04', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (10, 3, 2, 3, 'Monthly scholarship address #1', 'mogE6d26pwbwwG8LYZo9MMkH7afatF7NHU', 0, '2015-05-31 06:48:53', '2015-06-02 17:56:38');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (11, 3, 2, 2, 'Change address #1', 'mmqjCVEky9HEmnaQWeyQCtw9jCNxYonH3M', 0.1399, '2015-06-02 17:56:38', '2015-06-02 20:23:59');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (12, 3, 2, 2, 'Payments from friends/family address #1', 'mhsM4EZDz5zZcZsR4KxQTaWUp3jTpK1Fq9', 0.007, '2015-05-31 07:05:19', '2015-06-02 12:36:09');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (13, 3, 2, 1, 'Payments from abroad address #1', 'mi6S8dC5kvj435D4Wr1mVQ8RVFDHh8n8DV', 0, '2015-05-31 07:11:36', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (14, 3, 7, 3, 'Freelance earnings address #1', 'mxzsZRCAzMQKD8BpgPhfbKywSQERF7eLEr', 0, '2015-05-31 07:19:20', '2015-06-02 16:08:15');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (15, 3, 7, 2, 'Change address #1', 'miqiQLj49a6bSg53jwaMghGv1VSbLgD2ip', 6.999, '2015-06-02 16:08:15', '2015-06-02 16:14:45');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (16, 3, 7, 1, 'Dividend payouts address #1', 'mxfN2XEo52abd9vM8dNkRimyTxcdPGApvx', 0, '2015-05-31 07:24:13', NULL);
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (17, 4, 2, 2, 'Short-term deposit (0.1 tBTC) address #1', 'miH27nBXbKuhiie2EndaL7HpyEU3Pfjmu4', 0.1, '2015-05-31 07:30:55', '2015-06-02 15:36:30');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (18, 4, 2, 2, 'Short-term deposit (0.2 tBTC) address #2', 'muuxD5NeoQnhq5muyizhYGC62AMW9Za2Eg', 0.2, '2015-05-31 07:38:29', '2015-06-02 15:56:32');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (19, 4, 7, 2, 'Short-term deposit (100 tLTC) address #1', 'mkL5CvwXmsoDT6QMLJRfDBbhr1LwGeGpSN', 100, '2015-05-31 07:44:01', '2015-06-02 13:14:05');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (20, 5, 2, 2, 'Medium-term deposit (0.3 tBTC) address #1', 'mpYuzvbc1QZCzdZnBt5zLDFLXFa64UyGJd', 0.3, '2015-05-31 07:48:47', '2015-06-02 15:56:32');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (21, 5, 2, 2, 'Long-term deposit (0.5 tBTC) address #1', 'muo4sC6q5QiDAvqbxZ71gaU1q99hzgZCG6', 0.5, '2015-05-31 07:52:20', '2015-06-02 15:56:32');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (22, 5, 7, 2, 'Medium-term deposit (250 tLTC) address #1', 'mqQSkX987x665dPfr3inveo6pQstB3j9XU', 250, '2015-05-31 07:58:13', '2015-06-02 13:20:00');
INSERT INTO address (address_id, wallet_id, address_type_id, address_state_type_id, label, encoded_form, balance, indexed_at, updated_at) VALUES (23, 5, 8, 2, 'Long-term deposit (400 tLTC) address #1', 'n2cRjaRK1kwsW9rmr7ck6kzJsdkATfYYbc', 400, '2015-05-31 08:04:30', '2015-06-02 13:26:12');
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

INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (1, 4, '155ab5f1-7d36-485a-a378-f1c007bf6121', '440b19baf347d16586f0aed1355b571cb8241c818aa74da92b1fd8660e7736d2', '2015-06-02 10:21:56', '2015-06-02 11:38:40', '2015-06-02 11:45:44', 446398, 192, 0.0002, 222.40, NULL, '2015-06-02 10:21:56', '2015-06-02 11:45:44');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (2, 4, 'dd06fd59-e94b-4616-b70f-70777a9df88a', '8e1b1faeb35cfb9144e38f0049c5787221494306a080d3a51deec3f1cb8f3109', '2015-06-02 15:30:44', '2015-06-02 16:31:07', '2015-06-02 17:52:53', 446483, 225, 0.0001, 223.61, 'Transfer funds to BTC-e for tBTC->USD conversion (0.13 tBTC)', '2015-06-02 15:30:44', '2015-06-02 17:52:53');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (3, 4, 'be320f50-d78a-42a5-b5a4-ad9a6ca4b0f6', '26fab599f7cc7477d01347963f26f5a8da120910af7c63720c21e082d8cab10e', '2015-06-02 10:45:04', '2015-06-02 12:16:54', '2015-06-02 12:48:09', 446406, 192, 0.0002, 222.40, NULL, '2015-06-02 10:45:04', '2015-06-02 12:48:09');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (4, 4, '49fb8612-50cf-4e21-8956-243885870a14', '12e210bb33cf953f0f3cbceb40ad1102fcfe7336f917b301a9b787dc81f7ddef', '2015-06-02 10:51:49', '2015-06-02 12:23:59', '2015-06-02 12:33:14', 446409, 192, 0.0002, 222.40, NULL, '2015-06-02 10:51:49', '2015-06-02 12:33:14');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (5, 4, '2d80218d-3a9c-4679-8fb2-e31540c799f2', '8943ca3a7a2475bf84ff054fcc87556a97f88a1b6693c299e66c1733958ec93f', '2015-06-02 17:52:43', '2015-06-02 20:04:58', '2015-06-02 20:23:59', 446503, 192, 0.0002, 224.91, NULL, '2015-06-02 17:52:43', '2015-06-02 20:23:59');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (6, 4, 'c39495b3-e70a-48c3-9056-1f21a47e0441', '39fcab09e65e4c14fd963866f2d5505c8cfdf44f00e7fd0dbfcc883156e40bed', '2015-06-02 17:56:38', '2015-06-02 20:04:58', '2015-06-02 20:23:59', 446503, 225, 0.0001, 224.91, 'Deposit 0.21 tBTC to Kraken for daytrading', '2015-06-02 17:56:38', '2015-06-02 20:23:59');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (7, 4, '0d785629-4045-4038-aa02-b59b89269e95', 'dbd27c43be43710d14f20cb95a5d8e77403a3c7bfa8d80f062e41dc6d84b9fed', '2015-06-02 10:59:17', '2015-06-02 12:33:14', '2015-06-02 12:36:09', 446411, 191, 0.0002, 222.40, NULL, '2015-06-02 10:59:17', '2015-06-02 12:36:09');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (8, 4, '92a5c00d-ad54-494f-b4e7-f6a5a4a3b529', 'eda5901f5fc35a2b724b8c751281c8152d6df9ea9c68a9928ba2f71a190561d1', '2015-06-02 15:16:23', '2015-06-02 15:17:30', '2015-06-02 15:19:07', 615146, 192, 0.0002, 1.671, NULL, '2015-06-02 15:16:23', '2015-06-02 15:19:07');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (9, 4, 'b385b64e-8c0f-43d3-a11d-991408e77ee2', '45a48c901df784c55102b233cc3878345350068a6e74c00fe2983b2355d397bc', '2015-06-02 16:08:15', '2015-06-02 16:12:08', '2015-06-02 16:14:45', 615172, 226, 0.001, 1.671, 'Payment to Garrett for the painting ''The Woodsie Lord''', '2015-06-02 16:08:15', '2015-06-02 16:14:45');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (10, 4, '0190695a-1c35-402a-9122-ba9e32d2d0cc', 'd21d602b82d3163a4fc1d57068803b7c1c324d82820f4dc2bcfec93d80ec4acd', '2015-06-02 13:28:53', '2015-06-02 15:30:05', '2015-06-02 15:36:30', 446465, 192, 0.0002, 222.89, NULL, '2015-06-02 13:28:53', '2015-06-02 15:36:30');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (11, 4, '47594a67-047b-4744-947a-0cf347e74f31', '4b88d8e0900ff8ae1445603a9e48ebe96f303b5d2f81796fd9c7ed1e3975da89', '2015-06-02 13:31:00', '2015-06-02 14:48:31', '2015-06-02 15:56:32', 446466, 192, 0.0002, 222.89, NULL, '2015-06-02 13:31:00', '2015-06-02 15:56:32');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (12, 4, '36a67d3a-9a50-4c10-b984-38c2c7a5fda9', '3e8d679fffa52985e72161ecd71a75ccf14976dc8d24c89b8ef1175c543c11b0', '2015-06-02 13:07:05', '2015-06-02 13:07:08', '2015-06-02 13:14:05', 615087, 191, 0.0002, 1.612, NULL, '2015-06-02 13:07:05', '2015-06-02 13:14:05');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (13, 4, 'c3a1c822-5198-4726-bc10-f5ecdd32e62e', '3a9e8318c9c0478ba6e892043c31a58335f67d0bde36022fdf2741395e053674', '2015-06-02 13:33:10', '2015-06-02 14:48:31', '2015-06-02 15:56:32', 446466, 191, 0.0002, 222.89, NULL, '2015-06-02 13:33:10', '2015-06-02 15:56:32');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (14, 4, '1e88db46-9f64-45e2-8197-23636c7cdf57', '2484128a25e5c987658624092e79a0e463280823e67ab40624b67bb9f42d08dd', '2015-06-02 13:35:15', '2015-06-02 14:48:31', '2015-06-02 15:56:32', 446466, 191, 0.0002, 222.89, NULL, '2015-06-02 13:35:15', '2015-06-02 15:56:32');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (15, 4, 'cbfc4131-657e-401a-833a-bf0d9d28c862', '71c5fccf177ad8b31c6574fcfb56bc833bd80a8daa5fd024a6dbeed661e29958', '2015-06-02 13:14:25', '2015-06-02 13:15:28', '2015-06-02 13:20:00', 615090, 191, 0.0002, 1.612, NULL, '2015-06-02 13:14:25', '2015-06-02 13:20:00');
INSERT INTO transactions (transaction_id, transaction_status_type_id, local_uid, network_uid, received_at, confirmed_at, completed_at, block_height, binary_size, fee, unit_price, note, logged_at, updated_at) VALUES (16, 4, '00b8500e-cd21-40e8-b206-1a2bf14c9b34', '07c253ae6b926ebf9e96c4b69f55a0c23737f4a0869356300d392c63b3c8fb95', '2015-06-02 13:18:16', '2015-06-02 13:20:00', '2015-06-02 13:26:12', 615092, 193, 0.0002, 1.612, NULL, '2015-06-02 13:18:16', '2015-06-02 13:26:12');

INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (1, 1, 32, 1, 0.2002, '2015-06-02 10:21:56');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (2, 1, 1, 2, 0.2, '2015-06-02 10:21:56');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (3, 2, 1, 1, 0.2, '2015-06-02 15:30:44');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (4, 2, 24, 2, 0.13, '2015-06-02 15:30:44');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (5, 2, 2, 3, 0.0699, '2015-06-02 15:30:44');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (6, 3, 33, 1, 0.0012, '2015-06-02 10:45:04');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (7, 3, 3, 2, 0.001, '2015-06-02 10:45:04');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (8, 4, 34, 1, 0.0012, '2015-06-02 10:51:49');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (9, 4, 3, 2, 0.001, '2015-06-02 10:51:49');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (10, 5, 35, 1, 0.3502, '2015-06-02 17:52:43');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (11, 5, 10, 2, 0.35, '2015-06-02 17:52:43');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (12, 6, 10, 1, 0.35, '2015-06-02 17:56:38');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (13, 6, 27, 2, 0.21, '2015-06-02 17:56:38');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (14, 6, 11, 3, 0.1399, '2015-06-02 17:56:38');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (15, 7, 36, 1, 0.0072, '2015-06-02 10:59:17');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (16, 7, 12, 2, 0.007, '2015-06-02 10:59:17');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (17, 8, 41, 1, 25.0002, '2015-06-02 15:16:23');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (18, 8, 14, 2, 25, '2015-06-02 15:16:23');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (19, 9, 14, 1, 25, '2015-06-02 16:08:15');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (20, 9, 31, 2, 18, '2015-06-02 16:08:15');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (21, 9, 15, 3, 6.999, '2015-06-02 16:08:15');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (22, 10, 37, 1, 0.1002, '2015-06-02 13:28:53');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (23, 10, 17, 2, 0.1, '2015-06-02 13:28:53');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (24, 11, 38, 1, 0.2002, '2015-06-02 13:31:00');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (25, 11, 18, 2, 0.2, '2015-06-02 13:31:00');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (26, 12, 42, 1, 100.0002, '2015-06-02 13:07:05');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (27, 12, 19, 2, 100, '2015-06-02 13:07:05');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (28, 13, 39, 1, 0.3002, '2015-06-02 13:33:10');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (29, 13, 20, 2, 0.3, '2015-06-02 13:33:10');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (30, 14, 40, 1, 0.5002, '2015-06-02 13:35:15');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (31, 14, 21, 2, 0.5, '2015-06-02 13:35:15');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (32, 15, 43, 1, 250.0002, '2015-06-02 13:14:25');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (33, 15, 22, 2, 250, '2015-06-02 13:14:25');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (34, 16, 44, 1, 400.0002, '2015-06-02 13:18:16');
INSERT INTO transaction_endpoint (transaction_endpoint_id, transaction_id, address_id, transaction_endpoint_type_id, amount, logged_at) VALUES (35, 16, 23, 2, 400, '2015-06-02 13:18:16');

INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (1, 1, 0.2, 'Please note that the emergency loan you have requested (0.15 tBTC + 0.05 tBTC interest) is due for repayment on June 3, 2015.', '2015-03-24 16:03:47');
INSERT INTO payment_request (payment_request_id, address_id, amount, message, requested_at) VALUES (2, 3, 0.001, 'Cookie & ice cream fund (donate 0.001 tBTC)', '2015-03-24 16:10:22');
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
INSERT INTO role (role_id, code, name) VALUES (1, 'WEBSITE_EDITOR', 'Website Editor');
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
INSERT INTO transaction_status_type (transaction_status_type_id, code, name) VALUES (6, 'DROPPED', 'Dropped');

INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (1, 'INPUT', 'Input');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (2, 'OUTPUT_MAIN', 'Output');
INSERT INTO transaction_endpoint_type (transaction_endpoint_type_id, code, name) VALUES (3, 'OUTPUT_CHANGE', 'Output (change)');

/*2.2 Deletion statements*/
TRUNCATE TABLE role CASCADE;
TRUNCATE TABLE wallet_state_type CASCADE;
TRUNCATE TABLE address_state_type CASCADE;
TRUNCATE TABLE transaction_status_type CASCADE;
TRUNCATE TABLE transaction_endpoint_type CASCADE;