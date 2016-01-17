package pers.evan.fastrepair.dao;

import pers.evan.fastrepair.model.Company;

import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface CompanyDao extends TemplateDao<Company>{

    List<Company> getCompanyByCompanyName(String companyName);

}
