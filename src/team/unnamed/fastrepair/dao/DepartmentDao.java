package team.unnamed.fastrepair.dao;

import team.unnamed.fastrepair.model.Department;

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

    void close() throws SQLException;
}
