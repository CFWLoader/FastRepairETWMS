package fastrepair.dao.impl.test;

import org.junit.Test;
import fastrepair.exception.BadUpdateQueryException;
import fastrepair.exception.UserNotFoundException;
import fastrepair.dao.EmployeeDao;
import fastrepair.dao.impl.MySqlEmployeeDao;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;

import java.sql.SQLException;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlEmployeeDaoTest {
    @Test
    public void testAddEmployee(){
        Employee employee = new Employee();

        /*
        employee.setFirstName("Yifan");
        employee.setLastName("Zhang");
        employee.setGender("Male");
        employee.setPhone("18899994444");
        employee.setAddress("Nanlei");
        employee.setCompanyId(1);
        employee.setDepartmentId(5);
        */

        employee.setFirstName("Jack");
        employee.setLastName("Chen");
        employee.setGender("Male");
        employee.setPhone("12255557777");
        employee.setAddress("Master Street");
        employee.setCompanyId(5);
        employee.setDepartmentId(4);

        EmployeeDao employeeDao = new MySqlEmployeeDao();

        try{
            employeeDao.addEmployee(employee, "123456");

            employeeDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateEmployee(){

        EmployeeDao employeeDao = new MySqlEmployeeDao();

        try {
            Employee employee = employeeDao.getEmployee(2, "654321");

            System.out.println(employee);

            employee.setLastName("Matrix");

            employeeDao.updateEmployee(employee, "123456");

            employeeDao.close();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (BadUpdateQueryException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEmployee(){

        EmployeeDao employeeDao = new MySqlEmployeeDao();

        try {
            Employee employee = employeeDao.getEmployee(2, "123456");

            System.out.println(employee);

            employeeDao.close();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (BadUpdateQueryException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetEmployeeByDepartment() throws SQLException {

        EmployeeDao employeeDao = new MySqlEmployeeDao();

        Department department = new Department();

        department.setId(4);

        for(Employee employee : employeeDao.getEmployeesByDepartment(department, 0, 2)){
            System.out.println(employee);
        }

        employeeDao.close();
    }

    @Test
    public void testGetEmployeeByCompany() throws SQLException {

        EmployeeDao employeeDao = new MySqlEmployeeDao();

        Company company = new Company();

        company.setId(1);

        for(Employee employee : employeeDao.getEmployeesByCompany(company, 0, 2)){
            System.out.println(employee);
        }

        employeeDao.close();
    }
}
