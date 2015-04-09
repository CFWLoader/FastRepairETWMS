package team.unnamed.dao.impl;

import team.unnamed.dao.CompanyDao;
import team.unnamed.model.Company;
import team.unnamed.util.MySqlConnectionManager;

import java.sql.*;

/**
 * Created by cfwloader on 4/9/15.
 */
public class MySqlCompanyDao implements CompanyDao {

    private Connection connection;

    public MySqlCompanyDao() {
        this.connection = MySqlConnectionManager.getDefaultConnection();
    }

    @Override
    public void addCompany(Company company) throws SQLException {

        String sql = "insert into company values(null,?,?);";

        PreparedStatement statement;

        try {
            statement = connection.prepareStatement(sql);

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getLocation());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }


    }

    @Override
    public void removeCompany(Company company) throws SQLException {

        String sql = "delete from company where id = " + company.getId() + ";";

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate(sql);

            statement.close();
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void updateCompany(Company company) throws SQLException {

        String sql = "update company set companyname = ?, location = ? where id = ?;";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getLocation());
            statement.setInt(3, company.getId());

            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Company getCompanyByCompanyName(String companyName) throws SQLException {

        Company company = null;

        String sql = "select * from company where companyname = ?;";

        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            statement = connection.prepareStatement(sql);

            statement.setString(1, companyName);

            statement.execute();

            resultSet = statement.getResultSet();

            if(resultSet.next()){
                company = new Company();
                company.setId(resultSet.getInt(1));
                company.setCompanyName(resultSet.getString(2));
                company.setLocation(resultSet.getString(3));
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return company;
    }

    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
