package team.unnamed.fastrepair.service.impl;

import team.unnamed.fastrepair.exception.BadRequestParameterException;
import team.unnamed.fastrepair.exception.BadUpdateQueryException;
import team.unnamed.fastrepair.exception.UserNotFoundException;
import team.unnamed.fastrepair.dao.EmployeeDao;
import team.unnamed.fastrepair.dao.impl.MySqlEmployeeDao;
import team.unnamed.fastrepair.model.Company;
import team.unnamed.fastrepair.model.Department;
import team.unnamed.fastrepair.model.Employee;
import team.unnamed.fastrepair.service.EmployeeService;

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
    public Employee employeeLogin(String idStr, String password) throws BadUpdateQueryException, UserNotFoundException, BadRequestParameterException {
        if(idStr == null || idStr.trim().equals("") || password == null || password.trim().equals("")){
            throw new BadRequestParameterException();
        }
        return employeeDao.getEmployee(Integer.parseInt(idStr.trim()), password.trim());
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
