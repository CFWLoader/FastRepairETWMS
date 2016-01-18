package pers.evan.fastrepair.service.impl;

import org.springframework.stereotype.Service;
import pers.evan.fastrepair.dao.DepartmentDao;
import pers.evan.fastrepair.dao.ToolDao;
import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Tool;
import pers.evan.fastrepair.service.ToolService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
@Service
public class ToolServiceImpl implements ToolService {

    @Resource
    private ToolDao toolDao;

    @Resource
    private DepartmentDao departmentDao;

    @Override
    public int addTool(Tool tool) {

        toolDao.addEntity(tool);

        return 0;
    }

    @Override
    public Tool getToolById(String id) throws BadRequestParameterException {
        if(id == null || id.trim().equals(""))throw new BadRequestParameterException();

        return toolDao.getToolById(Long.parseLong(id.trim()));
    }

    @Override
    public void updateTool(Tool tool) {
        toolDao.updateEntity(tool);
    }

    @Override
    public void removeTool(Tool tool) {
        toolDao.deleteEntity(tool);
    }

    @Override
    public List<Tool> getToolsByDepartmentId(Department department, int pageIndex, int size) throws BadRequestParameterException {
        if(department == null)throw new BadRequestParameterException();

        if(department.getDepartmentType().equals("Specialist") || department.getDepartmentType().equals("Admin") || department.getDepartmentType().equals("Tool Keeper"))return toolsOfSpecialist(pageIndex, size);

        return toolDao.getToolsByDepartment(department, (pageIndex - 1) * size, size);
    }

    private List<Tool> toolsOfSpecialist(int pageIndex, int size){

        List<Tool> tools = new ArrayList<Tool>(10);
        
        int equivalentIndex = (pageIndex - 1) * size;

        int equivalentVolume = 0;

        for(Department department : departmentDao.getEntities())
        {
            equivalentVolume = toolDao.getTotalOfTool(department);

            if(equivalentIndex < equivalentVolume) {
                tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size));

                if(tools.size() >= size){
                    return tools;
                }
            }
        }

        /*
        Department department = departmentDao.getDepartmentById(1);

        int equivalentVolume = toolDao.getTotalOfTool(department);

        if(equivalentIndex < equivalentVolume) {
            tools.addAll(toolDao.getToolsByDepartment(department, equivalentIndex, size));

            if(tools.size() >= size){
                //departmentDao.close();
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
                //departmentDao.close();
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
                //departmentDao.close();
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
                //departmentDao.close();
                return tools;
            }
        }
        */

        //departmentDao.close();
        return tools;
    }

    @Override
    public List<Tool> getToolsByCompanyId(Company company, int pageIndex, int size) throws BadRequestParameterException {

        if(company == null)throw new BadRequestParameterException();

        return toolDao.getToolsByCompany(company, (pageIndex - 1) + size, size);
    }

    @Override
    public int getTotalOfTool(Department department) throws BadRequestParameterException {
        if(department == null)throw new BadRequestParameterException();

        if(department.getDepartmentType().equals("Specialist") || department.getDepartmentType().equals("Admin"))return totalOfToolOfSpecialist();

        return toolDao.getTotalOfTool(department);
    }

    @Override
    public Tool toolObjectAssembler(String idStr,
                                    String toolType,
                                    String isExpensiveStr,
                                    String numberOfAvailableStr,
                                    Company company,
                                    Department department) {

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

        if(company != null)tool.setCompany(company);

        if(department != null)tool.setDepartment(department);

        return tool;
    }

    private int totalOfToolOfSpecialist(){

        int total = 0;

        for(Department department : departmentDao.getEntities())
        {
            total += toolDao.getTotalOfTool(department);
        }

        /*
        Department department = new Department();

        department.setId(1);

        total += toolDao.getTotalOfTool(department);

        department.setId(2);

        total += toolDao.getTotalOfTool(department);

        department.setId(3);

        total += toolDao.getTotalOfTool(department);

        department.setId(4);

        total += toolDao.getTotalOfTool(department);
        */

        return total;
    }

}
