package fastrepair.dao.impl.test;

import org.junit.Before;
import org.junit.Test;
import fastrepair.dao.CompanyDao;
import fastrepair.model.Company;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlCompanyDaoTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    private CompanyDao companyDao;

    @Before
    public void initialize(){

        companyDao = (CompanyDao) context.getBean("mySqlCompanyDao");

    }

    @Test
    public void testAddCompany(){

        Company company = new Company();

        company.setCompanyName("Fast Repair Ultimate");

        company.setLocation("Future Street");

        companyDao.addCompany(company);
    }

}
