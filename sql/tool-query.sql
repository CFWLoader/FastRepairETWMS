use fastrepair;

create table tool(id integer primary key AUTO_INCREMENT, toolname varchar(64), isexpensive bool default false,
numberofavailable integer, companyid integer references company.id, departmentid integer references department.id); 

select * from tool;

desc tool;

show create table tool;

# alter table tool add column isexpensive bool default false after toolname;