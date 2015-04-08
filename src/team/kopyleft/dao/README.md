# Data Access Objects for Fast-Repair-ETWMS

Because class Department is extra information of Employee and Tools, we do not set a independent DAO for Department.<br/>
We embedded sql query of department in query of those classes.