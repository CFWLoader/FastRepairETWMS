package pers.evan.fastrepair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
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
}
