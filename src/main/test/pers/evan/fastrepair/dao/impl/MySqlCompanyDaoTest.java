package pers.evan.fastrepair.dao.impl;

import org.junit.Before;
import org.junit.Test;
import pers.evan.fastrepair.dao.CompanyDao;
import pers.evan.fastrepair.model.Company;
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

        company.setCompanyName("Wait for delete");

        company.setLocation("Deleting");

        companyDao.addEntity(company);
    }

}
