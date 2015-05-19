package fastrepair.service;

import fastrepair.exception.BadRequestParameterException;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Tool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolService {

    int addTool(Tool tool) throws SQLException;

    Tool getToolById(String id) throws BadRequestParameterException, SQLException;

    void updateTool(Tool tool) throws SQLException;

    void removeTool(Tool tool) throws SQLException;

    List<Tool> getToolsByDepartmentId(String departmentId, int pageIndex, int size) throws SQLException, BadRequestParameterException;

    List<Tool> getToolsByCompanyId(String companyId, int pageIndex, int size) throws SQLException, BadRequestParameterException;

    int getTotalOfTool(String departmentId) throws SQLException, BadRequestParameterException;

    Tool toolObjectAssembler(String idStr, String toolType, String numberOfAvailableStr, String isExpensiveStr, String companyIdStr, String departmentIdStr) throws SQLException;

    void close();

}
