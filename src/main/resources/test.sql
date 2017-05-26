DROP table IF EXISTS user;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 


DROP table IF EXISTS message;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(16) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8


DROP TABLE IF EXISTS `t_sofa_client`;

CREATE TABLE `t_sofa_client` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `FromApp` varchar(50) DEFAULT NULL,
  `ToApp` varchar(50) DEFAULT NULL,
  `InvokeCnt` varchar(100) DEFAULT NULL,#调用总次数
  `CreatTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY(`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
