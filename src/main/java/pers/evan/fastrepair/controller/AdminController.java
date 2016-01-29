package pers.evan.fastrepair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.evan.fastrepair.exception.BadRequestParameterException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.model.Tool;
import pers.evan.fastrepair.service.CompanyService;
import pers.evan.fastrepair.service.DepartmentService;
import pers.evan.fastrepair.service.EmployeeService;
import pers.evan.fastrepair.service.ToolService;
import pers.evan.fastrepair.util.AppContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
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

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/index")
    public String indexPage() {
        return "admin/index";
    }

    @RequestMapping("/employees")
    public String employeesPage(ModelMap models, int pageIndex, int pageSize) {

        List<Employee> employees = employeeService.getEmployees(pageIndex, pageSize);

        models.put("employees", employees);

        models.put("pageIndex", pageIndex);

        models.put("pageSize", pageSize);

        int sum = employeeService.getTotalOfEmployees();

        models.put("totalRecords", sum);

        //int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;
        models.put("totalPages", (sum + pageSize - 1) / pageSize);

        return "admin/employees";
    }

    @RequestMapping("/addEmployee")
    public String addEmployee(Map<String, Object> models) {

        Employee employee = new Employee();

        employee.setId(-1);

        employee.setUsername("Not set");

        employee.setPassword("");

        employee.setFirstName("Not set");

        employee.setLastName("Not set");

        employee.setGender("Not set");

        employee.setPhone("Not set");

        employee.setAddress("Not set");

        List<Company> companies = companyService.getCompanies();

        List<Department> departments = departmentService.getDepartments();

        employee.setCompany(companies.get(0));

        employee.setDepartment(departments.get(0));

        models.put("employee", employee);

        models.put("target", AppContext.getBaseUrl() + "/admin/doAddEmployee");

        models.put("companies", companies);

        models.put("departments", departments);

        return "admin/employee";
    }

    @RequestMapping("/employee")
    public String employeePage(HttpSession session, Map<String, Object> models, String idStr) {

        if (idStr != null && !idStr.trim().equals("")) {

            Employee employee = employeeService.getEmployeeById(Long.valueOf(idStr));

            models.put("employee", employee);

            models.put("target", AppContext.getBaseUrl() + "/admin/modifyEmployee");
        } else {
            models.put("employee", session.getAttribute("employee"));

            models.put("target", "");
        }

        models.put("companies", companyService.getCompanies());

        models.put("departments", departmentService.getDepartments());

        return "admin/employee";
    }

    @RequestMapping("/modifyEmployee")
    public String modifyEmployee(int id, String firstname, String lastname, String gender, String phone, String address,
                                 int companyselect, int departmentselect, Map<String, Object> models) {

        Employee employee = employeeService.getEmployeeById(id);

        employee.setFirstName(firstname);

        employee.setLastName(lastname);

        employee.setGender(gender);

        employee.setPhone(phone);

        employee.setAddress(address);

        employee.setCompany(companyService.getCompanyById(companyselect));

        employee.setDepartment(departmentService.getDepartmentById(departmentselect));

        employeeService.updateEmployee(employee);

        models.put("employee", employee);

        models.put("target", AppContext.getBaseUrl() + "/admin/modifyEmployee");

        models.put("companies", companyService.getCompanies());

        models.put("departments", departmentService.getDepartments());

        return "admin/employee";
    }

    @RequestMapping("/companies")
    public String companiesPage(HttpSession session, ModelMap models, int pageIndex, int pageSize) {

        /*
        List<Company> companies = companyService.getCompanies();

        models.put("companyList", companies);
        */

        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        try {

            List<Company> companies = companyService.getCompanies();

            models.put("companyList", companies);

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

        return "admin/companies";
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String companyPage(HttpSession session, Map<String, Object> models, String idStr) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        if (idStr != null && !idStr.trim().equals("")) {
            Company company = companyService.getCompanyById(Long.valueOf(idStr));

            models.put("company", company);

            models.put("target", AppContext.getBaseUrl() + "/admin/modifyCompany");
        } else {
            models.put("company", employee.getCompany());

            models.put("target", "");
        }

        return "admin/company";
    }

    @RequestMapping(value = "/modifyCompany", method = RequestMethod.POST)
    public String modifyCompany(long id, String companyName, String location, Map<String, Object> models) {

        Company company = companyService.getCompanyById(id);

        if (company == null) {
            models.put("errorMessage", "Failed to require the company information.");

            return "errorPage";
        }

        company.setCompanyName(companyName);

        company.setLocation(location);

        companyService.updateCompany(company);

        models.put("company", company);

        models.put("target", AppContext.getBaseUrl() + "/admin/doAddCompany");

        return "admin/company";
    }

    @RequestMapping("addCompany")
    public String companyPage(Map<String, Object> models) {

        Company company = new Company();

        company.setId(-1);

        company.setCompanyName("Unset");

        company.setLocation("Unset");

        models.put("company", company);

        models.put("target", AppContext.getBaseUrl() + "/admin/doAddCompany");

        return "admin/company";
    }

    @RequestMapping(value = "/doAddCompany", method = RequestMethod.POST)
    public String addCompany(String companyName, String location, Map<String, Object> models) {

        Company company = new Company();

        company.setCompanyName(companyName);

        company.setLocation(location);

        companyService.addCompany(company);

        models.put("company", company);

        models.put("target", AppContext.getBaseUrl() + "/admin/modifyCompany");

        return "admin/company";
    }

    @RequestMapping("/departments")
    public String departmentsPage(ModelMap models, int pageIndex, int pageSize) {

        List<Department> departments = departmentService.getDepartments();

        models.put("departments", departments);

        models.put("pageIndex", pageIndex);

        models.put("pageSize", pageSize);

        int sum = departments.size();

        models.put("totalRecords", sum);

        //int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;
        models.put("totalPages", (sum + pageSize - 1) / pageSize);

        return "admin/departments";
    }

    @RequestMapping("/department")
    public String departmentPage(HttpSession session, Map<String, Object> models, String idStr) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee == null) {
            return "redirect:" + AppContext.getBaseUrl() + "/home/sign-in";
        }

        if (idStr != null && !idStr.trim().equals("")) {
            Department department = departmentService.getDepartmentById(Long.valueOf(idStr));

            models.put("department", department);

            models.put("target", AppContext.getBaseUrl() + "/admin/modifyDepartment");
        } else {
            models.put("department", employee.getDepartment());

            models.put("target", "");
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
    public String toolPage(Map<String, Object> models) {

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
                          int companySelection, int departmentSelection, Map<String, Object> models) {

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
