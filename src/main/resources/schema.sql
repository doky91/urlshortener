CREATE SCHEMA demo;

create table demo.users(
	username varchar(50) not null primary key,
	password varchar(8),
	enabled boolean not null
);

create table demo.authorities(
	username varchar(50),
	authority varchar(50),
	constraint ft_autorities_users foreign key (username) references users(username)
);

create table demo.links(
	url varchar(300),
	short_url varchar(50),
	link_visits int,
	redirect_type int,
	user varchar(50),
	constraint ft_links_users foreign key (user) references users(username)
);