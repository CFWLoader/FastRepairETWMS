package pers.evan.fastrepair.service.impl;

import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.service.DepartmentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cfwloader on 5/19/15.
 */
public class DepartmentServiceImplTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testAddDepartment(){

        DepartmentService departmentService = (DepartmentService) context.getBean("departmentServiceImpl");

        Department department = new Department();

        department.setDepartmentType("Admin");

        departmentService.addDepartment(department);

        department.setDepartmentType("Appliance");

        departmentService.addDepartment(department);

        department.setDepartmentType("Automobile");

        departmentService.addDepartment(department);

        department.setDepartmentType("Computer");

        departmentService.addDepartment(department);

        department.setDepartmentType("Construction Device");

        departmentService.addDepartment(department);

        department.setDepartmentType("Human Resource");

        departmentService.addDepartment(department);

        department.setDepartmentType("Specialist");

        departmentService.addDepartment(department);

        department.setDepartmentType("Tool Keeper");

        departmentService.addDepartment(department);
    }
}
