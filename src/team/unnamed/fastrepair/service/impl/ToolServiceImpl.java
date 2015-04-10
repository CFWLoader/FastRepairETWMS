package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.dao.ToolDao;
import team.unnamed.fastrepair.dao.impl.MySqlToolDao;
import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Tool;
import team.unnamed.fastrepair.service.ToolService;

import java.sql.SQLException;
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
    public void addTool(Tool tool) {
        toolDao.addTool(tool);
    }

    @Override
    public Tool getToolById(String id) throws BadRequestParameterException, SQLException {
        if(id == null || id.trim().equals(""))throw new BadRequestParameterException();

        return toolDao.getToolById(Integer.parseInt(id.trim()));
    }

    @Override
    public void updateTool(Tool tool) {
        toolDao.updateTool(tool);
    }

    @Override
    public void removeTool(Tool tool) {
        toolDao.removeTool(tool);
    }

    @Override
    public List<Tool> getToolsByDepartmentId(String departmentId, int pageIndex, int size) throws SQLException, BadRequestParameterException {
        if(departmentId == null || departmentId.trim().equals(""))throw new BadRequestParameterException();

        Department department = new Department();

        department.setId(Integer.parseInt(departmentId.trim()));

        if(department.getId() == 5 || department.getId() == 6)return toolsOfSpecialist(department, pageIndex, size);

        return toolDao.getToolsByDepartment(department, (pageIndex - 1) * size, size);
    }

    private List<Tool> toolsOfSpecialist(Department department, int pageIndex, int size) throws SQLException {

        List<Tool> tools = new LinkedList<Tool>();
        
        int equivalentIndex = (pageIndex - 1) * size;

        department.setId(1);

        int equivalentVolume = toolDao.getTotalOfTool(department);

        if((equivalentIndex + 1) > equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size));
        }

        department.setId(2);

        department.setId(3);

        department.setId(4);

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
