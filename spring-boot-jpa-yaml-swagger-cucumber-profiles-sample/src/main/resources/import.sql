insert into user(name, password, date_of_birth) values ('admin', 'admin', '1983-07-11');
insert into user(name, password, date_of_birth) values ('guest', 'guest', '1969-10-12');
insert into user(name, password, date_of_birth) values ('user', 'user', '1996-02-13');

insert into authority(name) values ('ROLE_ADMIN');
insert into authority(name) values ('ROLE_GUEST');
insert into authority(name) values ('ROLE_USER');

insert into user_authority(user_id, authority_id) values (1, 1);
insert into user_authority(user_id, authority_id) values (1, 3);
insert into user_authority(user_id, authority_id) values (2, 2);
insert into user_authority(user_id, authority_id) values (3, 3);