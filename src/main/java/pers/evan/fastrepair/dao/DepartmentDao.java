package pers.evan.fastrepair.dao;

import pers.evan.fastrepair.model.Department;

import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
public interface DepartmentDao extends TemplateDao<Department>{
    List<Department> getDepartmentsByName(String name);
}
