package pers.evan.fastrepair.service;

import pers.evan.fastrepair.model.Company;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyService {

    void addCompany(Company company);

    void deleteCompany(Company company);

    void updateCompany(Company company);

    List<Company> getCompanyByCompanyName(String companyName);

    List<Company> getCompanies();

    Company getCompanyById(long id);

}
