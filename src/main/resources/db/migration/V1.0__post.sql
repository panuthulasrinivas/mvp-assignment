
create table if not exists `post`(
`id`        varchar(36),
`data`      varchar(2000),
`reference` varchar(36),
`created_by` varchar(50),
`updated_by` varchar(50),
`created_ts` DATETIME,
`updated_ts` DATETIME,
`is_deleted` tinyint(1) default 0
);

INSERT INTO `post` (`id`,`data`,`reference`,`created_by`,`updated_by`,`created_ts`,`updated_ts`,`is_deleted`) VALUES ('d648faa1-177d-405c-8508-0e196b27296f','hello world 0',NULL,'admin','admin','2023-06-05 20:42:48','2023-06-05 20:47:03',0);
INSERT INTO `post` (`id`,`data`,`reference`,`created_by`,`updated_by`,`created_ts`,`updated_ts`,`is_deleted`) VALUES ('3a43a6d7-f1ef-4d7e-9639-d11f664daa74','hello world 1','d648faa1-177d-405c-8508-0e196b27296f','admin','admin','2023-06-05 20:44:57','2023-06-05 20:44:57',0);
INSERT INTO `post` (`id`,`data`,`reference`,`created_by`,`updated_by`,`created_ts`,`updated_ts`,`is_deleted`) VALUES ('f0e5fd1d-59ea-4bd1-a68d-00119664f076','hello world 2','3a43a6d7-f1ef-4d7e-9639-d11f664daa74','admin','admin','2023-06-05 20:45:25','2023-06-05 20:45:25',0);