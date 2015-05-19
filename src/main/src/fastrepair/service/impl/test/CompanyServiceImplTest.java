package fastrepair.service.impl.test;

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

        company.setCompanyName("Fan Shen Gundam Company");

        company.setLocation("Moon");

        companyService.addCompany(company);

    }

    @Test
    public void testGetCompanyById(){

        CompanyService companyService = (CompanyService) context.getBean("companyServiceImpl");

        Company company = companyService.getCompanyById(2L);

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
}
