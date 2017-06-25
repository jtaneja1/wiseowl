insert into user_auth(password_hash, username) values (SHA2('david123',256),'david');
insert into siteuser(firstname,lastname,nickname,dob,user_role_id,user_auth_id) values('David','Bennet','David','1985-01-01',2,1);

insert into user_auth(password_hash, username) values (SHA2('justin123',256),'justin');
insert into siteuser(firstname,lastname,nickname,dob,user_role_id,user_auth_id) values('Justin','Miller','Justin','1985-02-02',2,2);

insert into user_auth(password_hash, username) values (SHA2('password1',256),'jatin');
insert into siteuser(firstname,lastname,nickname,dob,user_role_id,user_auth_id) values('Jatin','Taneja','Jatin','1985-04-04',2,3);

insert into user_auth(password_hash, username) values (SHA2('password123',256),'dyoder');
insert into siteuser(firstname,lastname,nickname,dob,user_role_id,user_auth_id) values('Duane','Yoder','Duane','1984-04-04',2,4);
