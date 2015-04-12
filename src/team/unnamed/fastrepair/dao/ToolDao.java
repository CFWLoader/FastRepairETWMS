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

    int addTool(Tool tool) throws SQLException;

    Tool getToolById(int id) throws SQLException;

    void updateTool(Tool tool) throws SQLException;

    void removeTool(Tool tool) throws SQLException;

    List<Tool> getToolsByDepartment(Department department, int startIndex, int size) throws SQLException;

    List<Tool> getToolsByCompany(Company company, int startIndex, int size) throws SQLException;

    int getTotalOfTool(Department department) throws SQLException;

    boolean getAutoCommit() throws SQLException;

    void setAutoCommit(boolean autoCommit) throws SQLException;

    void commit() throws SQLException;

    void close();
}
