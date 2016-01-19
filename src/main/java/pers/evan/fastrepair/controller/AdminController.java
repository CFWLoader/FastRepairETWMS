package pers.evan.fastrepair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.Tool;
import pers.evan.fastrepair.service.CompanyService;
import pers.evan.fastrepair.service.DepartmentService;
import pers.evan.fastrepair.service.ToolService;
import pers.evan.fastrepair.util.AppContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Evan on 2016/1/17.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private ToolService toolService;

    @Resource
    private CompanyService companyService;

    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/index")
    public String indexPage() {
        return "admin/index";
    }

    @RequestMapping("/employees")
    public String employeesPage() {
        return "admin/employees";
    }

    @RequestMapping("/employee")
    public String employeePage() {
        return "admin/employee";
    }

    @RequestMapping("/company")
    public String companyPage(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        return "admin/company";
    }

    @RequestMapping("/department")
    public String departmentPage(HttpSession session) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        return "admin/department";
    }

    @RequestMapping(value = "/tools", method = RequestMethod.GET)
    public String toolsPage(HttpSession session, Map<String, Object> models, int pageIndex, int pageSize) {

        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        try {
            models.put("tools", toolService.getToolsByDepartment(employee.getDepartment(), pageIndex, pageSize));

            models.put("pageIndex", pageIndex);

            models.put("pageSize", pageSize);

            int sum = toolService.getTotalOfTool(employee.getDepartment());

            models.put("totalRecords", sum);

            //int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;
            models.put("totalPages", (sum + pageSize - 1) / pageSize);
        } catch (BadRequestParameterException e) {
            models.put("errorMessage", "Failed to require the tool list.");

            return "errorPage";
        }

        return "admin/tools";
    }

    @RequestMapping("addTool")
    public String toolPage(Map<String, Object> models)
    {

        Tool tool = new Tool();
        tool.setId(-1);
        tool.setToolName("");
        tool.setIsExpensive(false);
        tool.setNumberOfAvailable(0);

        tool.setCompany(new Company());
        tool.getCompany().setCompanyName("");
        tool.getCompany().setLocation("");

        tool.setDepartment(new Department());
        tool.getDepartment().setId(0);
        tool.getDepartment().setDepartmentType("");

        models.put("tool", tool);

        models.put("target", AppContext.getBaseUrl() + "/admin/doAddTool");

        models.put("companies", companyService.getCompanies());

        models.put("departments", departmentService.getEngineerDepartments());

        return "admin/tool";
    }

    @RequestMapping("doAddTool")
    public String addTool(String toolName, String isExpensive, int numberOfAvailable,
                          int companySelection, int departmentSelection, Map<String, Object> models)
    {

        Tool tool = new Tool();
        tool.setId(-1);
        tool.setToolName(toolName);
        tool.setIsExpensive(Boolean.valueOf(isExpensive));
        tool.setNumberOfAvailable(numberOfAvailable);

        tool.setCompany(companyService.getCompanyById(companySelection));

        tool.setDepartment(departmentService.getDepartmentById(departmentSelection));

        tool.setId(toolService.addTool(tool));

        models.put("tool", tool);

        models.put("target", AppContext.getBaseUrl() + "/admin/modifyTool");

        models.put("companies", companyService.getCompanies());

        models.put("departments", departmentService.getEngineerDepartments());

        return "admin/tool";
    }
}
