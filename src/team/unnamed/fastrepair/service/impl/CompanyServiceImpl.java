package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.CompanyDao;
import team.unnamed.fastrepair.dao.impl.MySqlCompanyDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.service.CompanyService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/11/15.
 */
public class CompanyServiceImpl implements CompanyService {

    private CompanyDao companyDao;

    public CompanyServiceImpl() {
        companyDao = new MySqlCompanyDao();
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        companyDao.addCompany(company);
    }

    @Override
    public void removeCompany(Company company) throws SQLException {
        companyDao.removeCompany(company);
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        companyDao.updateCompany(company);
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) throws SQLException {
        return companyDao.getCompanyByCompanyName(companyName);
    }

    @Override
    public List<Company> getCompanies() throws SQLException {
        return companyDao.getCompanies();
    }

    @Override
    public Company getCompanyById(String idStr) throws SQLException, BadRequestParameterException {
        if(idStr == null || idStr.trim().equals(""))throw new BadRequestParameterException();

        return companyDao.getCompanyById(Integer.parseInt(idStr));
    }

    @Override
    public void close() throws SQLException {
        companyDao.close();
    }
}
