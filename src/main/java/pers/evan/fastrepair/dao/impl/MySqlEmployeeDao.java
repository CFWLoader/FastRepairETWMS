package pers.evan.fastrepair.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.EmployeeDao;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;

import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */
@Repository
public class MySqlEmployeeDao extends TemplateDaoImpl<Employee> implements EmployeeDao {

    @Override
    public Employee getEmployeeByUsername(String username) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("username", username));

        List<Employee> result = (List<Employee>) hibernateTemplate.findByCriteria(criteria);

        if(result.size() > 0) {
            return result.get(0);
        }

        return null;

        /*
        Session session = this.getSession();

        Query query = session.createQuery("From Employee e where e.id = :id and password = :passwd")
                .setParameter("id", id)
                .setParameter("passwd", password);

        return (Employee) query.list().get(0);
        */
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("department", department));

        return (List<Employee>) hibernateTemplate.findByCriteria(criteria, startIndex, pageSize);

        /*
        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("From Employee e where e.department = :dId")
                .setParameter("dId", department);

        //Equivalent to "limit" clause of sql;
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return (List<Employee>) query.list();
        */

    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("company", company));

        return (List<Employee>) hibernateTemplate.findByCriteria(criteria, startIndex, pageSize);

        /*
        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("From Employee e where e.company = :dId")
                .setParameter("dId", company);

        //Equivalent to "limit" clause of sql;
        query.setFirstResult(startIndex);
        query.setMaxResults(pageSize);

        return (List<Employee>) query.list();
        */
    }

    @Override
    public int getTotalOfEmployeesByDepartment(Department department) {

        Criteria criteria = this.getSession().createCriteria(clazz);

        criteria.add(Restrictions.eq("department", department));

        criteria.setProjection(Projections.count("id"));

        Integer result = (Integer) criteria.uniqueResult();

        return result;

        /*
        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("Select count(*) From Employee e where e.department = :dId")
                .setParameter("dId", department);


        return ((Long) query.uniqueResult()).intValue();
        */
    }

    @Override
    public int getTotalOfEmployeesByCompany(Company company) {

        Criteria criteria = this.getSession().createCriteria(clazz);

        criteria.add(Restrictions.eq("company", company));

        criteria.setProjection(Projections.count("id"));

        Integer result = (Integer) criteria.uniqueResult();

        return result;

        /*

        Session session = this.getSession();

        //Directly pass object to the hql. Magical!!
        Query query = session.createQuery("Select count(*) From Employee e where e.company = :dId")
                .setParameter("dId", company);


        return ((Long) query.uniqueResult()).intValue();
        */
    }
}
