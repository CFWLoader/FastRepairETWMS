package fastrepair.controller;

import fastrepair.model.Company;
import fastrepair.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 5/20/15.
 */
@Controller
@Scope("prototype")
@RequestMapping("/admin")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/companies")
    public String listCompanies(ModelMap modelMap){

        List<Company> companies = companyService.getCompanies();

        /*
        for(Company company : companies){
            System.out.println(company);
        }
        */

        modelMap.put("companyList", companies);

        return "admin/companies";
    }
}
