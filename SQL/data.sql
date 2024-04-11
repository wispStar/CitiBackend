INSERT INTO bond_user (username, password, email, phone_number, address) VALUES
('user1', 'pass1', 'user1@example.com', '0000000001', 'Address 1'),
('user2', 'pass2', 'user2@example.com', '0000000002', 'Address 2'),
('user3', 'pass3', 'user3@example.com', '0000000003', 'Address 3'),
('user4', 'pass4', 'user4@example.com', '0000000004', 'Address 4'),
('user5', 'pass5', 'user5@example.com', '0000000005', 'Address 5'),
('user6', 'pass6', 'user6@example.com', '0000000006', 'Address 6'),
('user7', 'pass7', 'user7@example.com', '0000000007', 'Address 7'),
('user8', 'pass8', 'user8@example.com', '0000000008', 'Address 8'),
('user9', 'pass9', 'user9@example.com', '0000000009', 'Address 9'),
('user10', 'pass10', 'user10@example.com', '0000000010', 'Address 10');

INSERT INTO product (cusip, productName, productDescription, position, startingValue, minimumBidIncrement, auctionDeadline) VALUES
('CUSIP001', 'Product 1', 'Description 1', 10, 1000.00, 50.00, '2024-12-31'),
('CUSIP002', 'Product 2', 'Description 2', 20, 2000.00, 100.00, '2024-12-31'),
('CUSIP003', 'Product 3', 'Description 3', 30, 3000.00, 150.00, '2024-12-31'),
('CUSIP004', 'Product 4', 'Description 4', 40, 4000.00, 200.00, '2024-12-31'),
('CUSIP005', 'Product 5', 'Description 5', 50, 5000.00, 250.00, '2024-12-31'),
('CUSIP006', 'Product 6', 'Description 6', 60, 6000.00, 300.00, '2024-12-31'),
('CUSIP007', 'Product 7', 'Description 7', 70, 7000.00, 350.00, '2024-12-31'),
('CUSIP008', 'Product 8', 'Description 8', 80, 8000.00, 400.00, '2024-12-31'),
('CUSIP009', 'Product 9', 'Description 9', 90, 9000.00, 450.00, '2024-12-31'),
('CUSIP010', 'Product 10', 'Description 10', 100, 10000.00, 500.00, '2024-12-31');

INSERT INTO bid (cusip, username, bidAmount) VALUES
('CUSIP001', 'user1', 1050.00),
('CUSIP002', 'user2', 2100.00),
('CUSIP003', 'user3', 3150.00),
('CUSIP004', 'user4', 4200.00),
('CUSIP005', 'user5', 5250.00),
('CUSIP006', 'user6', 6300.00),
('CUSIP007', 'user7', 7350.00),
('CUSIP008', 'user8', 8400.00),
('CUSIP009', 'user9', 9450.00),
('CUSIP010', 'user10', 10500.00);

INSERT INTO transaction (cusip, username, paymentMethod, transactionAmount, transactionType, quantity) VALUES
('CUSIP001', 'user1', 'Credit Card', 1050.00, 'buy', 1),
('CUSIP002', 'user2', 'Debit Card', 2100.00, 'sell', 2),
('CUSIP003', 'user3', 'PayPal', 3150.00, 'buy', 3),
('CUSIP004', 'user4', 'Credit Card', 4200.00, 'sell', 4),
('CUSIP005', 'user5', 'Debit Card', 5250.00, 'buy', 5),
('CUSIP006', 'user6', 'PayPal', 6300.00, 'sell', 6),
('CUSIP007', 'user7', 'Credit Card', 7350.00, 'buy', 7),
('CUSIP008', 'user8', 'Debit Card', 8400.00, 'sell', 8),
('CUSIP009', 'user9', 'PayPal', 9450.00, 'buy', 9),
('CUSIP010', 'user10', 'Credit Card', 10500.00, 'sell', 10),
('CUSIP002', 'user1', 'Credit Card', 2050.00, 'buy', 1),
('CUSIP003', 'user1', 'Debit Card', 3100.00, 'buy', 1),
('CUSIP004', 'user1', 'PayPal', 4150.00, 'buy', 1),
('CUSIP005', 'user2', 'Credit Card', 5200.00, 'buy', 1),
('CUSIP006', 'user2', 'Debit Card', 6250.00, 'buy', 1),
('CUSIP007', 'user2', 'PayPal', 7300.00, 'buy', 1),
('CUSIP008', 'user1', 'Credit Card', 8350.00, 'buy', 1),
('CUSIP009', 'user1', 'Debit Card', 9400.00, 'buy', 1),
('CUSIP010', 'user2', 'PayPal', 10450.00, 'buy', 1),
('CUSIP001', 'user2', 'Credit Card', 1500.00, 'buy', 1);
