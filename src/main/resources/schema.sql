
drop table if exists todo;

create table todo(
	id bigint not null AUTO_INCREMENT,
	name varchar(255),
	description varchar(255),
	updated_date date,
	checked_task boolean default false,
	user_name varchar(36) not null
);



