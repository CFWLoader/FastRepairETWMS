package pers.evan.fastrepair.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
@Repository
public class MySqlDepartmentDao extends TemplateDaoImpl<Department> implements DepartmentDao{

    @Override
    public List<Department> getDepartmentsByName(String name) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("departmentType", name));

        return (List<Department>) hibernateTemplate.findByCriteria(criteria);
    }
}
