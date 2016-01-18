package pers.evan.fastrepair.dao;

import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Tool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolDao extends TemplateDao<Tool> {

    Tool getToolById(long id);

    List<Tool> getToolsByDepartment(Department department, int startIndex, int size);

    List<Tool> getToolsByCompany(Company company, int startIndex, int size);

    int getTotalOfTool(Department department);

}
