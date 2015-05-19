package fastrepair.dao.impl;

import fastrepair.dao.DepartmentDao;
import fastrepair.model.Department;
import fastrepair.util.MySqlConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by cfwloader on 4/9/15.
 */
@Component
@Transactional
public class MySqlDepartmentDao implements DepartmentDao {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public void addDepartment(Department department) throws SQLException {

        Session session = sessionFactory.getCurrentSession();

        session.save(department);

    }

    @Override
    public void updateDepartment(Department department) throws SQLException {

    }

    @Override
    public void removeDepartment(Department department) throws SQLException {

    }

    @Override
    public List<Department> getDepartments() throws SQLException {
        return null;
    }

    @Override
    public Department getDepartmentById(int id) throws SQLException {
        return null;
    }
}
