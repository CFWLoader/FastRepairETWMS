package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.impl.MySqlDepartmentDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/11/15.
 */
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl() {
        departmentDao = new MySqlDepartmentDao();
    }

    @Override
    public void addDepartment(Department department) throws SQLException {
        departmentDao.addDepartment(department);
    }

    @Override
    public void updateDepartment(Department department) throws SQLException {
        departmentDao.updateDepartment(department);
    }

    @Override
    public void removeDepartment(Department department) throws SQLException {
        departmentDao.removeDepartment(department);
    }

    @Override
    public List<Department> getDepartments() throws SQLException {
        return departmentDao.getDepartments();
    }

    @Override
    public Department getDepartmentById(String idStr) throws SQLException, BadRequestParameterException {
        if(idStr == null || idStr.trim().equals(""))throw new BadRequestParameterException();

        return departmentDao.getDepartmentById(Integer.parseInt(idStr));
    }

    @Override
    public void close() throws SQLException {
        departmentDao.close();
    }
}
