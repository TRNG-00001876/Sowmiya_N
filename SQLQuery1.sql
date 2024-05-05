use rev_console;

create table userdata(
id int identity(1,1) primary key,
name varchar(20) not null,
gmail varchar(20) not null,
password varchar(20) not null
);

create table empresume(
id int not null,
location varchar(20) not null,
skillset1 varchar(20) not null,
skillset2 varchar(20) not null,
skillset3 varchar(20) not null
);

create table empdata(
id int identity(1,1) primary key,
companyname varchar(20) not null,
gmail varchar(20) not null,
password varchar(20) not null
);

create table job(
id int not null,
jobrole varchar(20) not null,
required varchar(20) not null,
experience varchar(20) not null,
lpa varchar(20) not null,
);


create table application(
userid int not null,
empid int not null,
status varchar(20) not null,
);

select * from userdata;
select * from empdata;
select * from empresume;
select * from job;
select * from application;