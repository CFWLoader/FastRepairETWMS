package team.kopyleft.dao;

import team.kopyleft.model.Company;

import java.sql.SQLException;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyDao {

    void addCompany(Company company) throws SQLException;

    void removeCompany(Company company) throws SQLException;

    void updateCompany(Company company);

    Company getCompanyByCompanyName(String companyName);

    void close() throws SQLException;
}
