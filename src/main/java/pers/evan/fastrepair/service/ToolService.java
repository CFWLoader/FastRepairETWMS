package pers.evan.fastrepair.service;

import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Tool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolService {

    int addTool(Tool tool);

    Tool getToolById(String id) throws BadRequestParameterException;

    void updateTool(Tool tool);

    void removeTool(Tool tool);

    List<Tool> getToolsByDepartmentId(Department department, int pageIndex, int size) throws BadRequestParameterException;

    List<Tool> getToolsByCompanyId(Company company, int pageIndex, int size) throws BadRequestParameterException;

    int getTotalOfTool(Department department) throws BadRequestParameterException;

    Tool toolObjectAssembler(String idStr, String toolType, String numberOfAvailableStr, String isExpensiveStr, Company company, Department department);

}
