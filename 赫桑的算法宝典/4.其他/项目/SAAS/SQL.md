# Release One

- ```sql
  -- merchant
  CREATE TABLE `merchant` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `merchantId` varchar(100) DEFAULT NULL,
    `merchantName` varchar(100) DEFAULT NULL,
    `status` varchar(20) DEFAULT NULL,
    `createTime` datetime DEFAULT NULL,
    `updateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
  
  -- user
  CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `phone_number` varchar(100) DEFAULT NULL,
    `cognito_username` varchar(100) DEFAULT NULL,
    `aud` varchar(100) DEFAULT NULL,
    `mId` int(11) DEFAULT NULL,
    `createTime` datetime DEFAULT NULL,
    `updateTime` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
  
  -- user_role 
  CREATE TABLE `user_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userId` int(11) NOT NULL,
    `roleId` int(11) NOT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
  
  -- role
  CREATE TABLE `role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `roleName` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
  
  ```

- 