package fastrepair.dao;

import fastrepair.model.Company;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyDao extends TemplateDao<Company>{

    List<Company> getCompanyByCompanyName(String companyName);

}
