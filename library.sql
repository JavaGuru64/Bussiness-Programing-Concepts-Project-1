
use database;

drop table if exists books;

create table books(
bookId int unsigned not null auto_increment,
name varchar(150) not null,
author varchar(50) not null,
edition varchar(25) null,
available boolean not null,
primary key (bookId));

drop table if exists members;

create table members(
memberId int unsigned not null auto_increment,
firstName varchar(25) not null,
lastName varchar(25) not null,
phone varchar(25) not null,
resident boolean not null,
lateFeesDue decimal not null,
booksOut int not null,
primary key (memberId));

drop table if exists borrows;

create table borrows(
borrowId int unsigned not null auto_increment,
memberId int unsigned not null,
bookId int unsigned not null,
dateBorrowed date not null,
dateReturned date null,
primary key (borrowId)); 


