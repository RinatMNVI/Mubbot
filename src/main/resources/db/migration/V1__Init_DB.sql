
create table hibernate_sequence (next_val bigint not null auto_increment, primary key(next_val)) engine=MyISAM;

create table Expenses (
    id bigint not null auto_increment,
    buyDate datetime,
    comment varchar(255),
    cost integer not null,
    user_id integer,
    tag_id integer, primary key (id)) engine=MyISAM;


create table Tags (
    id integer not null,
    name varchar(255),
    user_id integer,
    primary key (id)) engine=MyISAM;

create table users (
    id integer not null,
    name varchar(255),
    primary key (id)) engine=MyISAM;

alter table Expenses add constraint users_fk foreign key (user_id) references users (id);
alter table Expenses add constraint Tags_fk foreign key (tag_id) references Tags (id);
alter table Tags add constraint users_fk foreign key (user_id) references users (id);
