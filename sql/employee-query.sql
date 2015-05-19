use fastrepair;

# drop table employee;

create table employee(id integer primary key auto_increment, 
firstname varchar(64), lastname varchar(64), 
gender varchar(64), phone varchar(64), address varchar(64), 
companyid integer, departmentid integer, passwd varchar(64));

select * from employee;

select * from employee as e join department d on (e.departmentid = d.id) join company c on (e.companyid = c.id) where e.id = 2;