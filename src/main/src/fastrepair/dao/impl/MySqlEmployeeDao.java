package fastrepair.dao.impl;

import fastrepair.dao.EmployeeDao;
import fastrepair.model.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by cfwloader on 5/19/15.
 */
@Repository
public class MySqlEmployeeDao extends TemplateDaoImpl<Employee> implements EmployeeDao {
}
