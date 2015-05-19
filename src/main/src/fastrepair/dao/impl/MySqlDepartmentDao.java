package fastrepair.dao.impl;

import fastrepair.dao.DepartmentDao;
import fastrepair.dao.TemplateDao;
import fastrepair.model.Department;
import org.springframework.stereotype.Repository;

/**
 * Created by cfwloader on 4/9/15.
 */
@Repository
public class MySqlDepartmentDao extends TemplateDaoImpl<Department> implements DepartmentDao{
}
