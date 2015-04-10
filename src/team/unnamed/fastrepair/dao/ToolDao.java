package team.unnamed.fastrepair.dao;

import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Tool;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/8/15.
 */
public interface ToolDao {

    void addTool(Tool tool);

    Tool getToolById(int id) throws SQLException;

    void updateTool(Tool tool);

    void removeTool(Tool tool);

    List<Tool> getToolsByDepartment(Department department, int startIndex, int size) throws SQLException;

    List<Tool> getToolsByCompany(Company company, int startIndex, int size) throws SQLException;

    int getTotalOfTool(Department department) throws SQLException;

    void close();
}
