package fastrepair.service.impl;

import fastrepair.dao.CompanyDao;
import fastrepair.dao.DepartmentDao;
import fastrepair.dao.impl.MySqlCompanyDao;
import fastrepair.dao.impl.MySqlDepartmentDao;
import fastrepair.exception.BadRequestParameterException;
import fastrepair.exception.BadUpdateQueryException;
import fastrepair.exception.UserNotFoundException;
import fastrepair.dao.EmployeeDao;
import fastrepair.dao.impl.MySqlEmployeeDao;
import fastrepair.model.Company;
import fastrepair.model.Department;
import fastrepair.model.Employee;
import fastrepair.service.EmployeeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        employeeDao = new MySqlEmployeeDao();
    }

    @Override
    public int addEmployee(Employee employee, String password) throws SQLException, BadRequestParameterException {
        if(password == null || password.trim().equals(""))throw new BadRequestParameterException();

        return employeeDao.addEmployee(employee, password);
    }

    @Override
    public void updateEmployee(Employee employee, String password) throws BadUpdateQueryException, SQLException {
        employeeDao.updateEmployee(employee, password);
    }

    @Override
    public void removeEmployee(Employee employee) throws BadUpdateQueryException {
        employeeDao.removeEmployee(employee);
    }

    @Override
    public Employee employeeLogin(String idStr, String password) throws BadUpdateQueryException, UserNotFoundException, BadRequestParameterException {
        if (idStr == null || idStr.trim().equals("") || password == null || password.trim().equals("")) {
            throw new BadRequestParameterException();
        }
        return employeeDao.getEmployee(Integer.parseInt(idStr.trim()), password.trim());
    }

    @Override
    public Employee getEmployeeById(String idStr) throws BadRequestParameterException, BadUpdateQueryException, UserNotFoundException {
        if(idStr == null || idStr.trim().equals(""))throw new BadRequestParameterException();

        return employeeDao.getEmployeeById(Integer.parseInt(idStr.trim()));
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int pageIndex, int pageSize) throws SQLException {
        if(department != null) {
            return employeeDao.getEmployeesByDepartment(department, (pageIndex - 1) * pageSize, pageSize);
        }

        List<Employee> employees = new ArrayList<Employee>(10);

        int equivalentIndex = (pageIndex - 1) * pageSize;

        int equivalentVolume = 0;

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        for (Department departmentIterator : departmentDao.getDepartments()){
            equivalentVolume = employeeDao.getTotalOfEmployeeByDepartment(departmentIterator);

            employees.addAll(employeeDao.getEmployeesByDepartment(departmentIterator, equivalentIndex, pageSize - employees.size()));

            if(employees.size() >= pageSize)break;

            equivalentIndex -= equivalentVolume;

            if(equivalentIndex < 0)equivalentIndex = 0;
        }

        departmentDao.close();

        return employees;
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int pageIndex, int pageSize) throws SQLException {
        if (company != null) {
            return employeeDao.getEmployeesByCompany(company, (pageIndex - 1) * pageSize, pageSize);
        }

        List<Employee> employees = new ArrayList<Employee>(10);

        int equivalentIndex = (pageIndex - 1) * pageSize;

        int equivalentVolume = 0;

        CompanyDao companyDao = new MySqlCompanyDao();

        for(Company companyIterator : companyDao.getCompanies()){
            equivalentVolume = employeeDao.getTotalOfEmployeeByCompany(companyIterator);

            employees.addAll(employeeDao.getEmployeesByCompany(companyIterator, equivalentIndex, pageSize - employees.size()));

            equivalentIndex -= equivalentVolume;

            if(equivalentIndex < 0)equivalentIndex = 0;

            if(employees.size() >= pageSize)break;
        }

        companyDao.close();

        return employees;
    }

    @Override
    public int getTotalOfEmployeesByDepartment(Department department) throws SQLException {
        if(department != null){
            return employeeDao.getTotalOfEmployeeByDepartment(department);
        }

        int total = 0;

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        for(Department departmentIterator : departmentDao.getDepartments()){
            total += employeeDao.getTotalOfEmployeeByDepartment(departmentIterator);
        }

        departmentDao.close();

        return total;
    }

    @Override
    public int getTotalOfEmployeesByCompany(Company company) throws SQLException {
        if(company != null){
            return employeeDao.getTotalOfEmployeeByCompany(company);
        }

        int total = 0;

        CompanyDao companyDao = new MySqlCompanyDao();

        for(Company companyIterator : companyDao.getCompanies()){
            total += employeeDao.getTotalOfEmployeeByCompany(companyIterator);
        }

        companyDao.close();

        return total;
    }

    @Override
    public Employee employeeObjectAssembler(String idStr, String firstName, String lastName, String gender, String phone, String address, String companyIdStr, String departmentIdStr) throws BadUpdateQueryException, UserNotFoundException {
        Employee employee = null;

        if(idStr != null && !idStr.trim().equals("")){
            employee = employeeDao.getEmployeeById(Integer.parseInt(idStr.trim()));
        }else{
            employee = new Employee();
        }

        if(firstName != null)employee.setFirstName(firstName.trim());

        if(lastName != null)employee.setLastName(lastName.trim());

        if(gender != null)employee.setGender(gender.trim());

        if(phone != null)employee.setPhone(phone.trim());

        if(address != null)employee.setAddress(address);

        if(companyIdStr != null && !companyIdStr.trim().equals(""))employee.setCompanyId(Integer.parseInt(companyIdStr.trim()));

        if(departmentIdStr != null && !departmentIdStr.trim().equals(""))employee.setDepartmentId(Integer.parseInt(departmentIdStr.trim()));

        return employee;
    }

    @Override
    public void close() throws SQLException {
        employeeDao.close();
    }
}
