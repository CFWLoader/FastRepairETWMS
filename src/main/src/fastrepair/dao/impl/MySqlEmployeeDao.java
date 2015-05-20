package fastrepair.dao.impl;

import fastrepair.dao.EmployeeDao;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */
@Repository
public class MySqlEmployeeDao extends TemplateDaoImpl<Employee> implements EmployeeDao {

    @Override
    public Employee login(Long id, String password) {

        Session session = this.getSession();

        Query query = session.createQuery("From Employee e where e.id = :id and password = :passwd")
                .setParameter("id", id)
                .setParameter("passwd", password);

        return (Employee) query.list().get(0);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) {

        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("From Employee e where e.department = :dId")
                .setParameter("dId", department);

        //Equivalent to "limit" clause of sql;
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return (List<Employee>) query.list();

    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {

        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("From Employee e where e.company = :dId")
                .setParameter("dId", company);

        //Equivalent to "limit" clause of sql;
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return (List<Employee>) query.list();
    }

    @Override
    public int getTotalOfEmployeesByDepartment(Department department) {

        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("Select count(*) From Employee e where e.department = :dId")
                .setParameter("dId", department);


        return ((Long) query.uniqueResult()).intValue();

    }

    @Override
    public int getTotalOfEmployeesByCompany(Company company) {

        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("Select count(*) From Employee e where e.company = :dId")
                .setParameter("dId", company);


        return ((Long) query.uniqueResult()).intValue();
    }
}
