package pers.evan.fastrepair.service.impl;

import pers.evan.fastrepair.dao.EmployeeDao;
import pers.evan.fastrepair.exception.UserNotFoundException;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Employee;
import pers.evan.fastrepair.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cfwloader on 5/20/15.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public long addEmployee(Employee employee) {

        employeeDao.addEntity(employee);

        return employee.getId();

    }

    @Override
    public void updateEmployee(Employee employee) {

        employeeDao.updateEntity(employee);

    }

    @Override
    public void deleteEmployee(Employee employee) {

        employeeDao.deleteEntity(employee);

    }

    @Override
    public Employee employeeLogin(String username, String password) throws UserNotFoundException {

        Employee employee = employeeDao.getEmployeeByUsername(username);

        if (employee == null)
        {
            throw new UserNotFoundException();
        }

        if(!employee.getPassword().equals(password.trim()))
        {
            return null;
        }

        return employee;
    }

    @Override
    public Employee getEmployeeById(long id) {

        return employeeDao.getEntityById(id);

    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int startIndex, int pageSize) {
        return employeeDao.getEmployeesByDepartment(department, startIndex, pageSize);
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int startIndex, int pageSize) {
        return employeeDao.getEmployeesByCompany(company, startIndex, pageSize);
    }

    @Override
    public int getTotalOfEmployeesByDepartment(Department department) {
        return employeeDao.getTotalOfEmployeesByDepartment(department);
    }

    @Override
    public int getTotalOfEmployeesByCompany(Company company) {
        return employeeDao.getTotalOfEmployeesByCompany(company);
    }
}
