use fastrepair;

create table inexpensivetoollog(id integer primary key auto_increment, employeeid integer, toolid integer,
quantity integer,stat varchar(32), logdate datetime);

drop table expensivetoollog;

create table expensivetoollog(id integer primary key auto_increment, employeeid integer, toolid integer,
quantity integer,stat varchar(32), lenddate datetime, backdate datetime default '1000-01-01 00:00:00');

desc inexpensivetoollog;

select * from inexpensivetoollog;

desc expensivetoollog;

select * from expensivetoollog;

show create table expensivetoollog;