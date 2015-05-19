package fastrepair.dao;

import fastrepair.model.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
public interface DepartmentDao {

    void addDepartment(Department department) throws SQLException;

    void updateDepartment(Department department) throws SQLException;

    void removeDepartment(Department department) throws SQLException;

    List<Department> getDepartments() throws SQLException;

    Department getDepartmentById(int id) throws SQLException;

    void close() throws SQLException;
}
