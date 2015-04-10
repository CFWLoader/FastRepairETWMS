package team.unnamed.fastrepair.dao.impl.test;

import org.junit.Test;
import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.impl.MySqlDepartmentDao;
import team.unnamed.fastrepair.model.Department;

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
        department.setDepartmentType("test department");

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        departmentDao.addDepartment(department);

        departmentDao.close();
    }

    @Test
    public void testUpdateDepartment() {

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        try {
            List<Department> departments = departmentDao.getDepartments();

            for(Department department : departments){
                System.out.println(department);
                if(department.getDepartmentType().equals("test department")){
                    department.setDepartmentType("test");
                    departmentDao.updateDepartment(department);
                }
            }

            departmentDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveDepartment(){
        DepartmentDao departmentDao = new MySqlDepartmentDao();

        try {
            List<Department> departments = departmentDao.getDepartments();

            for(Department department : departments){
                System.out.println(department);
                if(department.getDepartmentType().equals("test")){
                    departmentDao.removeDepartment(department);
                }
            }

            departmentDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
