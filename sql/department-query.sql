use fastrepair;

select * from department;

# alter table department change deparmenttype departmenttype varchar(64);

create table department(id integer primary key auto_increment, departmenttype varchar(64) unique);