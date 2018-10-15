create table abc_user (
    id int not null primary key auto_increment,
	nickname varchar(20) not null,
	email varchar(64) not null,
	phone varchar(20),
	passwd varchar(40),
	country int,
	balance double,
	freeze double,
	active int,
	register_time timestamp default current_timestamp
	);

create table coin_abc (
	id int not null primary key,
	val double,
	foreign key(id) references abc_user(id)
);