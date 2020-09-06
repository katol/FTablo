create user ftablo with encrypted password 'ftablo';
create database ftablo;
create database test_ftablo;
grant all privileges on database ftablo to ftablo;
grant all privileges on database test_ftablo to ftablo;
drop database ftablo;
drop database test_ftablo;
