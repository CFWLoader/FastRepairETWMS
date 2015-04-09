package team.unnamed.dao.impl.test;

import org.junit.Test;
import team.unnamed.dao.CompanyDao;
import team.unnamed.dao.impl.MySqlCompanyDao;
import team.unnamed.model.Company;

import java.sql.SQLException;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlCompanyDaoTest {
    @Test
    public void testAddCompany(){
        Company company = new Company();
        /*
        company.setCompanyName("Fast Repair Parent Company");
        company.setLocation("Indiana state");
        */
        company.setCompanyName("Master Wang Machine Company");
        company.setLocation("Nanlei, Chang'an, Xi'an");

        CompanyDao companyDao = new MySqlCompanyDao();

        try {
            companyDao.addCompany(company);
            companyDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCompanyByCompanyName(){
        //String companyName = "Fast Repair Parent Company";
        String companyName = "Master Wang Machine Company";

        CompanyDao companyDao = new MySqlCompanyDao();

        try{
            Company company = companyDao.getCompanyByCompanyName(companyName);

            System.out.println(company);

            companyDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateCompany(){
        String companyName = "Master Wang Machine Company";

        CompanyDao companyDao = new MySqlCompanyDao();

        try{
            Company company = companyDao.getCompanyByCompanyName(companyName);

            System.out.println(company);

            company.setLocation("Qiang Zhe Area");

            companyDao.updateCompany(company);

            companyDao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveCompany(){

    }
}
