package fastrepair.dao.impl;

import fastrepair.dao.CompanyDao;
import fastrepair.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
@Repository
public class MySqlCompanyDao extends TemplateDaoImpl<Company> implements CompanyDao {

    @Override
    public List<Company> getCompanyByCompanyName(String companyName) {

        Session session = this.getSession();

        return session.createQuery("From Company c where c.companyName like :name ").setParameter("name", "%" + companyName + "%").list();

    }

}
