package fastrepair.dao;

import fastrepair.model.Company;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyDao {

    void addCompany(Company company);

    void removeCompany(Company company);

    void updateCompany(Company company);

    List<Company> getCompanyByCompanyName(String companyName);

    List<Company> getCompanies();

    Company getCompanyById(Long id);

}
