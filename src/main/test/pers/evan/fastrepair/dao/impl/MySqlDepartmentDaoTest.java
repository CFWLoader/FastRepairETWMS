package pers.evan.fastrepair.dao.impl;

import org.junit.Test;
import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlDepartmentDaoTest {
    @Test
    public void testAddDepartment() throws SQLException {
        Department department = new Department();

        //department.setDepartmentType("Construction Device");
        //department.setDepartmentType("Automobile");
        //department.setDepartmentType("Appliance");
        //department.setDepartmentType("Computer");
        //department.setDepartmentType("Specialist");
        //department.setDepartmentType("Admin");
        //department.setDepartmentType("Human Resource");
        department.setDepartmentType("Tool Keeper");

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        departmentDao.addEntity(department);

    }

    @Test
    public void testUpdateDepartment() {

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        List<Department> departments = departmentDao.getEntities();

        for (Department department : departments) {
            System.out.println(department);
            if (department.getDepartmentType().equals("test department")) {
                department.setDepartmentType("test");
                departmentDao.updateEntity(department);
            }
        }

    }

    @Test
    public void testRemoveDepartment() {
        DepartmentDao departmentDao = new MySqlDepartmentDao();

        List<Department> departments = departmentDao.getEntities();

        for (Department department : departments) {
            System.out.println(department);
            if (department.getDepartmentType().equals("test")) {
                departmentDao.deleteEntity(department);
            }
        }

    }
}
