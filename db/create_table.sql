/* MySQL*/

create database bankdb;

create table users ( id int primary key auto_increment,
name varchar(100) not null,
email varchar(100) not null,
password varchar(100) not null,
unique (email)
);

insert into users ( id,name,username,password) values
( 1, 'Naresh', 'naresh','pass123'),
( 2, 'Adam', 'adam','pass123');

create table accounts ( id int primary key auto_increment,
user_id int not null,
balance int not null default 0,
foreign key ( user_id) references users(id)
);
insert into accounts ( id, user_id,balance)
values ( 1, 1, 1000),(2,2,2000); 

select * from users;
select * from accounts;


create table transactions ( id int primary key auto_increment,
account_id int not null,
transaction_date timestamp );