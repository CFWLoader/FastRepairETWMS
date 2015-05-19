package fastrepair.dao.impl;

import fastrepair.dao.CompanyDao;
import fastrepair.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
@Component
@Transactional
public class MySqlCompanyDao implements CompanyDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void addCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    public void removeCompany(Company company) {
        sessionFactory.getCurrentSession().delete(company);
    }

    @Override
    public void updateCompany(Company company) {
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    public List<Company> getCompanyByCompanyName(String companyName) {

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("From Company c where c.companyName like :name ").setParameter("name", "%" + companyName + "%").list();

    }

    @Override
    public List<Company> getCompanies() {
        return sessionFactory.getCurrentSession().createQuery("From " + Company.class.getSimpleName()).list();
    }

    @Override
    public Company getCompanyById(Long id) {

        if(id != null){
            return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
        }

        return null;
    }
}
