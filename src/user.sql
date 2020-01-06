/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.26 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `users` (
	`id` Decimal (11),
	`username` varchar (96),
	`email` varchar (192),
	`grade` Decimal (11),
	`passwd` varchar (96)
); 
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('2','a2','a2@sohu.com','3','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('4','a4','a4@sohu.com','1','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('5','a5','a5@sohu.com','1','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('6','a6','a6@sohu.com','1','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('7','a7','a7@sohu.com','4','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('8','a8','a8@sohu.com','2','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('9','a9','a9@sohu.com','5','123');
insert into `users` (`id`, `username`, `email`, `grade`, `passwd`) values('10','a10','a10@sohu.com','1','123');
