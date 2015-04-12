package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.DepartmentDao;
import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.dao.impl.MySqlDepartmentDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.service.ToolService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
public class ToolServiceImpl implements ToolService {

    private ToolDao toolDao;

    public ToolServiceImpl() {
        toolDao = new MySqlToolDao();
    }

    @Override
    public int addTool(Tool tool) throws SQLException {
        return toolDao.addTool(tool);
    }

    @Override
    public Tool getToolById(String id) throws BadRequestParameterException, SQLException {
        if(id == null || id.trim().equals(""))throw new BadRequestParameterException();

        return toolDao.getToolById(Integer.parseInt(id.trim()));
    }

    @Override
    public void updateTool(Tool tool) throws SQLException {
        toolDao.updateTool(tool);
    }

    @Override
    public void removeTool(Tool tool) throws SQLException {
        toolDao.removeTool(tool);
    }

    @Override
    public List<Tool> getToolsByDepartmentId(String departmentId, int pageIndex, int size) throws SQLException, BadRequestParameterException {
        if(departmentId == null || departmentId.trim().equals(""))throw new BadRequestParameterException();

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        Department department = departmentDao.getDepartmentById(Integer.parseInt(departmentId.trim()));

        departmentDao.close();

        if(department.getId() == 5 || department.getId() == 6)return toolsOfSpecialist(pageIndex, size);

        return toolDao.getToolsByDepartment(department, (pageIndex - 1) * size, size);
    }

    private List<Tool> toolsOfSpecialist(int pageIndex, int size) throws SQLException {

        List<Tool> tools = new ArrayList<Tool>(10);
        
        int equivalentIndex = (pageIndex - 1) * size;

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        Department department = departmentDao.getDepartmentById(1);

        int equivalentVolume = toolDao.getTotalOfTool(department);

        if(equivalentIndex < equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size));

            if(tools.size() >= size){
                departmentDao.close();
                return tools;
            }
        }

        department = departmentDao.getDepartmentById(2);

        equivalentIndex -= equivalentVolume;

        if(equivalentIndex < 0)equivalentIndex = 0;

        equivalentVolume = toolDao.getTotalOfTool(department);

        if(equivalentIndex < equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size - tools.size()));

            if(tools.size() >= size){
                departmentDao.close();
                return tools;
            }
        }

        department = departmentDao.getDepartmentById(3);

        equivalentIndex -= equivalentVolume;

        if(equivalentIndex < 0)equivalentIndex = 0;

        equivalentVolume = toolDao.getTotalOfTool(department);

        if(equivalentIndex < equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size - tools.size()));

            if(tools.size() >= size){
                departmentDao.close();
                return tools;
            }
        }

        department = departmentDao.getDepartmentById(4);

        equivalentIndex -= equivalentVolume;

        if(equivalentIndex < 0)equivalentIndex = 0;

        equivalentVolume = toolDao.getTotalOfTool(department);

        if(equivalentIndex < equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size - tools.size()));

            if(tools.size() >= size){
                departmentDao.close();
                return tools;
            }
        }

        departmentDao.close();
        return tools;
    }

    @Override
    public List<Tool> getToolsByCompanyId(String companyId, int pageIndex, int size) throws SQLException, BadRequestParameterException {
        if(companyId == null || companyId.trim().equals(""))throw new BadRequestParameterException();

        Company company = new Company();

        company.setId(Integer.parseInt(companyId.trim()));

        return toolDao.getToolsByCompany(company, (pageIndex - 1) + size, size);
    }

    @Override
    public int getTotalOfTool(String departmentId) throws SQLException, BadRequestParameterException {
        if(departmentId == null || departmentId.trim().equals(""))throw new BadRequestParameterException();

        Department department = new Department();

        department.setId(Integer.parseInt(departmentId.trim()));

        if(department.getId() == 5 || department.getId() == 6)return totalOfToolOfSpecialist();

        return toolDao.getTotalOfTool(department);
    }

    @Override
    public Tool toolObjectAssembler(String idStr,
                                    String toolType,
                                    String isExpensiveStr,
                                    String numberOfAvailableStr,
                                    String companyIdStr,
                                    String departmentIdStr)
            throws SQLException {

        Tool tool = null;

        if(idStr != null && !idStr.trim().equals("")) {

            int id = Integer.parseInt(idStr.trim());

            tool = toolDao.getToolById(id);

        }else{
            tool = new Tool();
        }

        if(toolType != null)tool.setToolName(toolType.trim());

        if(isExpensiveStr != null)tool.setIsExpensive(Boolean.parseBoolean(isExpensiveStr));

        if(numberOfAvailableStr != null && !numberOfAvailableStr.trim().equals(""))tool.setNumberOfAvailable(Integer.parseInt(numberOfAvailableStr.trim()));

        if(companyIdStr != null && !companyIdStr.trim().equals(""))tool.setCompanyId(Integer.parseInt(companyIdStr.trim()));

        if(departmentIdStr != null && !departmentIdStr.trim().equals(""))tool.setDepartmentId(Integer.parseInt(departmentIdStr.trim()));

        return tool;
    }

    private int totalOfToolOfSpecialist() throws SQLException {

        int total = 0;

        Department department = new Department();

        department.setId(1);

        total += toolDao.getTotalOfTool(department);

        department.setId(2);

        total += toolDao.getTotalOfTool(department);

        department.setId(3);

        total += toolDao.getTotalOfTool(department);

        department.setId(4);

        total += toolDao.getTotalOfTool(department);

        return total;
    }

    @Override
    public void close() {
        toolDao.close();
    }
}
