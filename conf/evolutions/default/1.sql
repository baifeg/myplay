
# --- !Downs


# --- !Ups

create table if not exists Company (
	id bigint not null auto_increment , 
	name varchar(45) not null
);

create table if not exists User (
	id bigint not null auto_increment , 
	name varchar(45) not null, 
	companyId bigint not null
);

create table if not exists Vm (
	id bigint not null auto_increment ,
	name varchar(45) not null, 
	userId bigint not null,
	createTime datetime not null, 
	status tinyint not null comment '1:running, 0:destroyed',
	hardwareId bigint not null
);

create table if not exists Hardware (
	id bigint not null auto_increment ,
	name varchar(45) not null, 
	cpu int, 
	memory int,
	disk int, 
	bandwidth int
);

create table if not exists HardwarePrice (
	id bigint not null auto_increment ,
	hardwareId bigint not null,
	pricePerDay int not null,
	createTime datetime not null,
	startTime datetime not null, 
	endTime datetime
);

create table if not exists VmMonthBill (
	id bigint not null auto_increment ,
	vmId bigint not null,
	year int not null,
	month int not null,
	useDays int not null,
	charge int not null
);