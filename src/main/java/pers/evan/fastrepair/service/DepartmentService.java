package pers.evan.fastrepair.service;

import pers.evan.fastrepair.model.Department;

import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
public interface DepartmentService {

    void addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartment(Department department);

    List<Department> getDepartments();

    Department getDepartmentById(Long id);

}
