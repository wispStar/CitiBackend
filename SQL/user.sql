CREATE DATABASE IF NOT EXISTS `bond`;

USE `bond`;

DROP TABLE IF EXISTS `bond_user`;

CREATE TABLE `bond_user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
    `token` varchar(255) DEFAULT NULL COMMENT '用户登录成功后由系统颁发的身份验证令牌',
    `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(32) DEFAULT NULL COMMENT '手机号',
    `status` char(1) DEFAULT '0' COMMENT '登录状态: 0代表未登录，1代表已登录',
    `address` varchar(64) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';