package pers.evan.fastrepair.dao.impl;

import pers.evan.fastrepair.dao.CompanyDao;
import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.dao.EmployeeDao;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cfwloader on 5/19/15.
 */
public class MySqlEmployeeDaoTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testAddEmployee(){

        CompanyDao companyDao = (CompanyDao) context.getBean("mySqlCompanyDao");

        DepartmentDao departmentDao = (DepartmentDao) context.getBean("mySqlDepartmentDao");

        EmployeeDao employeeDao = (EmployeeDao) context.getBean("mySqlEmployeeDao");

        Employee employee = new Employee();

        employee.setFirstName("Jack");
        employee.setLastName("Chen");
        employee.setGender("Male");
        employee.setPhone("12255557777");
        employee.setAddress("Master Street");

        Company company = companyDao.getEntityById(1L);

        Department department = departmentDao.getEntityById(1L);

        employee.setCompany(company);

        employee.setDepartment(department);

        employeeDao.addEntity(employee);
    }

    @Test
    public void testUpdateEmployee(){

        EmployeeDao employeeDao = (EmployeeDao) context.getBean("mySqlEmployeeDao");

        Employee employee = employeeDao.getEntityById(1L);

        employee.setPassword("123456");

        employeeDao.updateEntity(employee);
    }

    @Test
    public void testGetEmployeeById(){

        EmployeeDao employeeDao = (EmployeeDao) context.getBean("mySqlEmployeeDao");

        Employee employee = employeeDao.getEntityById(1L);

        System.out.println(employee);
    }
}
