Hibernate: create sequence hibernate_sequence start 1 increment 1
Hibernate: create table game_stats (id int8 not null, build1 int4, build2 int4, pts int4, primary key (id))
Hibernate: create table message (id int8 not null, filename varchar(255), text varchar(2048), user_id int8, primary key (id))
Hibernate: create table user_role (user_id int8 not null, roles varchar(255))
Hibernate: create table usr (id int8 not null, active boolean not null, password varchar(255), username varchar(255), primary key (id))
Hibernate: alter table if exists message add constraint FK70bv6o4exfe3fbrho7nuotopf foreign key (user_id) references usr
Hibernate: alter table if exists user_role add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr