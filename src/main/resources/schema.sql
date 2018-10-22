CREATE SCHEMA demo;

create table demo.users(
	username varchar(50) not null primary key,
	password varchar(8),
	enabled boolean not null
);

create table demo.autorities(
	username varchar(50),
	authority varchar(50),
	constraint ft_autorities_users foreign key (username) references users(username)
);