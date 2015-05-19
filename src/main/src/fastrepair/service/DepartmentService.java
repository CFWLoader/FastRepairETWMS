package fastrepair.service;

import fastrepair.exception.BadRequestParameterException;
import fastrepair.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
public interface DepartmentService {

    void addDepartment(Department department) throws SQLException;

    void updateDepartment(Department department) throws SQLException;

    void removeDepartment(Department department) throws SQLException;

    List<Department> getDepartments() throws SQLException;

    Department getDepartmentById(String idStr) throws SQLException, BadRequestParameterException;

    void close() throws SQLException;
}
