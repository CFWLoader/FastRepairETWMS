package pers.evan.fastrepair.dao.impl;

import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.model.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by cfwloader on 4/9/15.
 */
@Repository
public class MySqlDepartmentDao extends TemplateDaoImpl<Department> implements DepartmentDao{
}
