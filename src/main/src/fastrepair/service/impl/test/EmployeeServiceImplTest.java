package fastrepair.service.impl.test;

import fastrepair.model.Department;
import fastrepair.model.Employee;
import fastrepair.service.DepartmentService;
import fastrepair.service.EmployeeService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by cfwloader on 5/20/15.
 */
public class EmployeeServiceImplTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testEmployeeLogin(){

        EmployeeService employeeService = (EmployeeService) context.getBean("employeeServiceImpl");

        Employee employee = employeeService.employeeLogin(1L, "123456");

        System.out.println(employee);
    }

    @Test
    public void testGetEmployeesByDepartment(){

        EmployeeService employeeService = (EmployeeService) context.getBean("employeeServiceImpl");

        DepartmentService departmentService = (DepartmentService) context.getBean("departmentServiceImpl");

        Department department = departmentService.getDepartmentById(1L);

        List<Employee> employees = employeeService.getEmployeesByDepartment(department, 0, 2);

        for(Employee employee : employees)
        {
            System.out.print(employee);
        }
    }
}
