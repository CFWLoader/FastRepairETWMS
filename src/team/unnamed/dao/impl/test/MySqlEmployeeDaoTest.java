package team.unnamed.dao.impl.test;

import org.junit.Test;
import team.unnamed.dao.EmployeeDao;
import team.unnamed.dao.impl.MySqlEmployeeDao;
import team.unnamed.exception.BadUpdateQueryException;
import team.unnamed.exception.UserNotFoundException;
import team.unnamed.model.Employee;

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

        employee.setFirstName("Admin");
        employee.setLastName("Admin");
        employee.setGender("NULL");
        employee.setPhone("NULL");
        employee.setAddress("Nilhil");
        employee.setCompanyId(1);
        employee.setDepartmentId(6);

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
            Employee employee = employeeDao.getEmployee(2, "123456");

            System.out.println(employee);

            employeeDao.close();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (BadUpdateQueryException e) {
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
        }
    }
}
