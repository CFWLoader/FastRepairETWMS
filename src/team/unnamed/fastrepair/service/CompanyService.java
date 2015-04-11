package team.unnamed.fastrepair.service;

import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.Company;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyService {

    void addCompany(Company company) throws SQLException;

    void removeCompany(Company company) throws SQLException;

    void updateCompany(Company company) throws SQLException;

    Company getCompanyByCompanyName(String companyName) throws SQLException;

    List<Company> getCompanies() throws SQLException;

    Company getCompanyById(String idStr) throws SQLException, BadRequestParameterException;

    void close() throws SQLException;
}
