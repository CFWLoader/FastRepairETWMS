package pers.evan.fastrepair.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import pers.evan.fastrepair.dao.CompanyDao;
import pers.evan.fastrepair.model.Company;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
@Repository
public class MySqlCompanyDao extends TemplateDaoImpl<Company> implements CompanyDao {

    @Override
    public List<Company> getCompanyByCompanyName(String companyName) {

        //Session session = this.getSession();
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.like("companyName", "%" + companyName + "%"));

        //return session.createQuery("From Company c where c.companyName like :name ").setParameter("name", "%" + companyName + "%").list();

        return (List<Company>) hibernateTemplate.findByCriteria(criteria);

    }

}
