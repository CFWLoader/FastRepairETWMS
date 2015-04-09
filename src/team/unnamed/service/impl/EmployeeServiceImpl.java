package team.unnamed.service.impl;

import team.unnamed.dao.EmployeeDao;
import team.unnamed.dao.impl.MySqlEmployeeDao;
import team.unnamed.exception.BadUpdateQueryException;
import team.unnamed.exception.UserNotFoundException;
import team.unnamed.model.Company;
import team.unnamed.model.Department;
import team.unnamed.model.Employee;
import team.unnamed.service.EmployeeService;

import java.sql.SQLException;
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
    public void addEmployee(Employee employee, String password) throws SQLException {
        employeeDao.addEmployee(employee, password);
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
    public Employee employeeLogin(int id, String password) throws BadUpdateQueryException, UserNotFoundException {
        return employeeDao.getEmployee(id, password);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department, int pageIndex, int pageSize) {
        return employeeDao.getEmployeesByDepartment(department,(pageIndex - 1) * pageSize, pageSize);
    }

    @Override
    public List<Employee> getEmployeesByCompany(Company company, int pageIndex, int pageSize) {
        return employeeDao.getEmployeesByCompany(company, (pageIndex - 1) * pageSize, pageSize);
    }

    @Override
    public void close() throws SQLException {
        employeeDao.close();
    }
}
