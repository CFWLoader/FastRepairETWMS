package team.kopyleft.dao.impl;

import team.kopyleft.dao.CompanyDao;
import team.kopyleft.model.Company;
import team.kopyleft.util.MySqlConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
    public void updateCompany(Company company) {

    }

    @Override
    public Company getCompanyByCompanyName(String companyName) {
        return null;
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
