package pers.evan.fastrepair.service.impl;

import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

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
    public List<Department> getDepartments(int pageIndex, int pageSize) {

        if(pageIndex < 1)
        {
            pageIndex = 1;
        }

        return departmentDao.getEntities(pageIndex - 1, pageSize);
    }

    @Override
    public List<Department> getEngineerDepartments() {
        //construction-device, automobile, appliance, computer

        List<Department> departments = new LinkedList<>();

        departments.addAll(departmentDao.getDepartmentsByName("Construction Device"));

        departments.addAll(departmentDao.getDepartmentsByName("Automobile"));

        departments.addAll(departmentDao.getDepartmentsByName("Appliance"));

        departments.addAll(departmentDao.getDepartmentsByName("Computer"));

        return departments;
    }

    @Override
    public List<Department> getDepartmentsByName(String name) {
        return departmentDao.getDepartmentsByName(name);
    }

    @Override
    public Department getDepartmentById(long id) {
        return departmentDao.getEntityById(id);
    }
}
