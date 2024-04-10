CREATE TABLE `bond_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `token` varchar(255) DEFAULT NULL COMMENT '用户登录成功后由系统颁发的身份验证令牌',
    `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(32) DEFAULT NULL COMMENT '手机号',
    `status` char(1) DEFAULT '0' COMMENT '登录状态: 0代表未登录，1代表已登录',
    `address` varchar(64) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`),
    UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cusip VARCHAR(255) NOT NULL,
    productName VARCHAR(255) NOT NULL,
    productDescription TEXT,
    position INT NOT NULL,
    startingValue DECIMAL(10, 2) NOT NULL,
    minimumBidIncrement DECIMAL(10, 2) NOT NULL,
    auctionDeadline DATETIME NOT NULL,
    UNIQUE INDEX cusip_unique (cusip)
);

CREATE TABLE bid (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cusip VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    bidAmount DECIMAL(10, 2) NOT NULL,
    bidTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cusip) REFERENCES product(cusip),
    FOREIGN KEY (username) REFERENCES bond_user(username)
);

CREATE TABLE transaction (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cusip VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    paymentMethod VARCHAR(255) NOT NULL,
    transactionAmount DECIMAL(10, 2) NOT NULL, 
    transactionTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transactionType ENUM('buy', 'sell') NOT NULL,
    FOREIGN KEY (cusip) REFERENCES product(cusip),
    FOREIGN KEY (username) REFERENCES bond_user(username)
);