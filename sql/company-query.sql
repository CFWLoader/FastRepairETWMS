use fastrepair;

# drop table company;

select * from company;

create table company(id integer primary key auto_increment, companyname varchar(128) unique,location varchar(256));