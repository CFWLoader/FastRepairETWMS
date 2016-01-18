package pers.evan.fastrepair.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.ToolDao;
import pers.evan.fastrepair.model.Company;
import pers.evan.fastrepair.model.Department;
import pers.evan.fastrepair.model.Tool;

import java.util.List;

/**
 * Created by cfwloader on 4/10/15.
 */
@Repository
public class MySqlToolDao extends TemplateDaoImpl<Tool> implements ToolDao {

    @Override
    public Tool getToolById(long id) {
        return (Tool) hibernateTemplate.get("id", id);
    }

    @Override
    public List<Tool> getToolsByDepartment(Department department, int startIndex, int size) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("department", department));

        return (List<Tool>) hibernateTemplate.findByCriteria(criteria, startIndex, size);
    }

    @Override
    public List<Tool> getToolsByCompany(Company company, int startIndex, int size) {

        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        criteria.add(Restrictions.eq("company", company));

        return (List<Tool>) hibernateTemplate.findByCriteria(criteria, startIndex, size);
    }

    @Override
    public int getTotalOfTool(Department department) {

        Criteria criteria = this.getSession().createCriteria(clazz);

        criteria.add(Restrictions.eq("department", department));

        criteria.setProjection(Projections.count("id"));

        Integer result = (Integer) criteria.uniqueResult();

        return result;
    }

    /*
    @Override
    public int addTool(Tool tool) throws SQLException {

        String sql = "insert into tool values(null, ?, ?, ?, ?, ?);";

        PreparedStatement statement = null;

        ResultSet resultSet = null;

        int id = -1;

        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, tool.getToolName());
            statement.setBoolean(2, tool.isExpensive());
            statement.setInt(3, tool.getNumberOfAvailable());
            statement.setInt(4, tool.getCompanyId());
            statement.setInt(5, tool.getDepartmentId());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next())id = resultSet.getInt(1);

            resultSet.close();

            statement.close();
        } catch (SQLException e) {
            if(resultSet != null && !resultSet.isClosed())resultSet.close();

            if(statement != null && !statement.isClosed())statement.close();

            throw e;
        }

        return id;
    }

    @Override
    public Tool getToolById(int id) throws SQLException {

        String sql = "select * from tool where id = " + id + ";";

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        CompanyDao companyDao = new MySqlCompanyDao();

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(companyDao.getCompanyById(tool.getCompanyId()));
                tool.setDepartment(departmentDao.getDepartmentById(tool.getDepartmentId()));
            }

            companyDao.close();
            departmentDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tool;
    }

    @Override
    public void updateTool(Tool tool) throws SQLException {

        String sql = "update tool set toolname = ?, isexpensive = ?, numberofavailable = ?, companyid = ?, departmentid = ? where id = ?;";

        PreparedStatement statement = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, tool.getToolName());
            statement.setBoolean(2, tool.isExpensive());
            statement.setInt(3, tool.getNumberOfAvailable());
            statement.setInt(4, tool.getCompanyId());
            statement.setInt(5, tool.getDepartmentId());
            statement.setInt(6, tool.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

            if(statement != null && !statement.isClosed())statement.close();
        }
    }

    @Override
    public void removeTool(Tool tool) throws SQLException {

        String sql = "delete from tool where id = ?;";

        PreparedStatement statement = null;

        try{
            statement.setInt(1, tool.getId());

            statement = connection.prepareStatement(sql);

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();

            if(statement != null && !statement.isClosed())statement.close();
        }
    }

    @Override
    public List<Tool> getToolsByDepartment(Department department, int startIndex, int size) throws SQLException {

        List<Tool> tools = new LinkedList<Tool>();

        String sql = "select * from tool where departmentid = " + department.getId() + " limit " + startIndex + "," + size + ";";

        CompanyDao companyDao = new MySqlCompanyDao();

        Map<Integer, Company> companyMap = new HashMap<Integer, Company>();

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            for(Company company : companyDao.getCompanies()){
                companyMap.put(company.getId(), company);
            }

            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(companyMap.get(tool.getCompanyId()));
                tool.setDepartment(department);

                tools.add(tool);
            }

            companyDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (companyDao != null) companyDao.close();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tools;
    }

    @Override
    public List<Tool> getToolsByCompany(Company company, int startIndex, int size) throws SQLException {
        List<Tool> tools = new LinkedList<Tool>();

        String sql = "select * from tool where departmentid = " + company.getId() + " limit " + startIndex + "," + size + ";";

        DepartmentDao departmentDao = new MySqlDepartmentDao();

        Map<Integer, Department> departmentMap = new HashMap<Integer, Department>();

        Statement statement = null;

        ResultSet resultSet = null;

        Tool tool = null;

        try {
            statement = connection.createStatement();

            statement.execute(sql);

            for(Department department : departmentDao.getDepartments()){
                departmentMap.put(department.getId(), department);
            }

            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                tool = new Tool();

                tool.setId(resultSet.getInt(1));
                tool.setToolName(resultSet.getString(2));
                tool.setIsExpensive(resultSet.getBoolean(3));
                tool.setNumberOfAvailable(resultSet.getInt(4));
                tool.setCompanyId(resultSet.getInt(5));
                tool.setDepartmentId(resultSet.getInt(6));
                tool.setCompany(company);
                tool.setDepartment(departmentMap.get(tool.getDepartmentId()));

                tools.add(tool);
            }

            departmentDao.close();
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();

            if (departmentDao != null) resultSet.close();

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return tools;
    }

    @Override
    public int getTotalOfTool(Department department) throws SQLException {

        String sql = "select count(*) from tool where departmentid = " + department.getId() + ";";

        int count = 0;

        Statement statement = null;

        ResultSet resultSet = null;

        try{
            statement = connection.createStatement();

            statement.execute(sql);

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                count = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {

            if (resultSet != null && !resultSet.isClosed()) resultSet.close();

            if (statement != null && !statement.isClosed()) statement.close();

            throw e;
        }

        return count;
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return connection.getAutoCommit();
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        connection.setAutoCommit(autoCommit);
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
    }

    @Override
    public void close() {
        try {
            if(connection != null && !connection.isClosed())connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!connection.isClosed()) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */
}
