package fastrepair.service.impl;

import fastrepair.dao.CompanyDao;
import fastrepair.model.Company;
import fastrepair.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 4/11/15.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Override
    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    @Override
    public void removeCompany(Company company) {

    }

    @Override
    public void updateCompany(Company company) {

    }

    @Override
    public List<Company> getCompanyByCompanyName(String companyName) {
        return companyDao.getCompanyByCompanyName(companyName);
    }

    @Override
    public List<Company> getCompanies() {
        return companyDao.getCompanies();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyDao.getCompanyById(id);
    }

}
