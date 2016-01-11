package fastrepair.service.impl;

import fastrepair.model.Company;
import fastrepair.service.CompanyService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by cfwloader on 5/19/15.
 */
public class CompanyServiceImplTest {

    private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void testAddCompany(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        Company company = new Company();

        company.setCompanyName("Unnamed Universe Company");

        company.setLocation("Unknown");

        companyService.addCompany(company);

    }

    @Test
    public void testGetCompanyById(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        Company company = companyService.getCompanyById(3L);

        System.out.println(company);
    }

    @Test
    public void testGetCompanies(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        for(Company company : companyService.getCompanies()){
            System.out.println(company);
        }

    }

    @Test
    public void testGetCompanyByName(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        for(Company company : companyService.getCompanyByCompanyName("Company")){
            System.out.println(company);
        }

    }

    @Test
    public void testUpdateCompany(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        Company company = companyService.getCompanyByCompanyName("delete").get(0);

        company.setLocation("New deleting.");

        companyService.updateCompany(company);

    }

    @Test
    public void testDeleteCompany(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        Company company = companyService.getCompanyByCompanyName("delete").get(0);

        company.setLocation("New deleting.");

        companyService.deleteCompany(company);
    }
}
