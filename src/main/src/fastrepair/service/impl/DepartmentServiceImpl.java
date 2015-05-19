package fastrepair.service.impl;

import fastrepair.dao.DepartmentDao;
import fastrepair.model.Department;
import fastrepair.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public void addDepartment(Department department) {
        departmentDao.addEntity(department);
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDao.updateEntity(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        departmentDao.deleteEntity(department);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentDao.getEntities();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentDao.getEntityById(id);
    }
}
