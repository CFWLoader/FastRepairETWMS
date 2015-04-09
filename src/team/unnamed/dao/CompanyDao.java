package team.unnamed.dao;

import team.unnamed.model.Company;

import java.sql.SQLException;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyDao {

    void addCompany(Company company) throws SQLException;

    void removeCompany(Company company) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    Company getCompanyByCompanyName(String companyName) throws SQLException;

    void close() throws SQLException;
}
