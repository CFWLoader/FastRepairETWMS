package pers.evan.fastrepair.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.DispatcherServlet;
import pers.evan.fastrepair.exception.UserNotFoundException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.service.CompanyService;
import pers.evan.fastrepair.service.DepartmentService;
import pers.evan.fastrepair.service.EmployeeService;

import java.util.List;

/**
 * Created by cfwloader on 1/18/16.
 */
public class BasicDataInitialServlet extends DispatcherServlet {

    @Override
    protected void initStrategies(ApplicationContext context) {
        super.initStrategies(context);

        initSqlData(context);
    }

    @Transactional
    private void initSqlData(ApplicationContext context) {
        /*
        AdminService adminService = context.getBean(AdminService.class);

        Admin admin = adminService.getAdminByUsername("admin");
        */
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        try {
            Employee employee = employeeService.getEmployeeByUsername("admin");

            if(employee != null)return;
        } catch (UserNotFoundException ignored) {
        }

        CompanyService companyService = context.getBean(CompanyService.class);

        Company company = null;

        List<Company> companies = companyService.getCompanies();

        if(companies.size() > 0)
        {
            company = companies.get(0);
        }
        else
        {
            company = new Company();

            company.setCompanyName("Unset");

            company.setLocation("Unset");

            companyService.addCompany(company);
        }

        DepartmentService departmentService = context.getBean(DepartmentService.class);

        Department department = null;

        List<Department> departments = departmentService.getDepartments();

        if(departments.size() > 0)
        {
            department = departments.get(0);
        }
        else
        {
            department = new Department();

            department.setDepartmentType("Admin");

            department.setCompany(company);

            departmentService.addDepartment(department);
        }

        Employee employee = new Employee();

        employee.setCompany(company);

        employee.setDepartment(department);

        employee.setFirstName("Admin");

        employee.setLastName("Admin");

        employee.setAddress("Unset");

        employee.setGender("Unset");

        employee.setUsername("admin");

        employee.setPassword("admin");

        employee.setPhone("Unset");

        employeeService.addEmployee(employee);
    }
}
