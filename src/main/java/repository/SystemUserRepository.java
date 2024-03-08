package repository;

import utils.PropertyUtils;
import utils.SQLUtils;

import java.sql.*;
import java.util.Properties;

public class SystemUserRepository {
    private final Properties properties;
    public SystemUserRepository() {
        properties = PropertyUtils.loadProperty();
    }
    public SystemUserRepository(Properties properties) {
        this.properties = properties;
    }

    private Connection startDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("DB_URL"),
                properties.getProperty("USERNAME"),
                properties.getProperty("PASSWORD")
        );
    }

    public boolean authenticateUser(String username, String password) {
        try (
                Connection connection = startDatabaseConnection();
                PreparedStatement statement = connection.prepareStatement(SQLUtils.SystemUserSQL.findMatched)
        ) {

                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    String foundPassword = resultSet.getString("password");
                    return password.equals(foundPassword);
               }
        } catch (SQLException ex) {
            System.out.println("Error when connecting to the database or executing the query.");
            ex.printStackTrace();
        }
        return false;
    }
}
