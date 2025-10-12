package database;

import tools.PropertiesConfigReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class MySQLConnector implements IDBConnector {

    private static MySQLConnector instance = null;
    private static final PropertiesConfigReader propertiesConfigReader = new PropertiesConfigReader();

    private static Connection connection = null;
    private static Statement statement = null;

    public MySQLConnector() {
    }

    public static synchronized MySQLConnector getInstance() {
        if (instance == null) {
            instance = new MySQLConnector();
        }
        return instance;
    }

    private static synchronized Connection getConnection() throws SQLException, IOException {
        if (connection == null || connection.isClosed()) {
            Map<String, String> creds = propertiesConfigReader.getCredentials();
            connection = DriverManager.getConnection(creds.get("URL"), creds.get("USER"), creds.get("PASSWORD"));
            System.out.println("Соединение с базой данных установлено");
        }
        return connection;
    }

    public void tryConnection() throws SQLException, IOException {
        Connection connection = getConnection();
        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    public void execute(String sqlRequest) throws SQLException, IOException {
        tryConnection();
        statement.execute(sqlRequest);
    }

    public ResultSet executeResultResponse(String sqlRequest) throws SQLException, IOException {
        tryConnection();
        return statement.executeQuery(sqlRequest);
    }

    public int executeUpdate(String sqlRequest) throws SQLException, IOException {
        tryConnection();
        return statement.executeUpdate(sqlRequest);
    }

    public void closeConnection() throws SQLException {
        if (statement != null) {
            statement.close();
            statement = null;
        }
        if (connection != null) {
            connection.close();
            connection = null;
        }
        System.out.println("Соединение с базой данных закрыто");
    }

    public static synchronized void resetInstance() {
        if (instance != null) {
            try {
                instance.closeConnection();
            } catch (SQLException e) {
                System.err.println("Ошибка закрытия соединения во время сброса: " + e.getMessage());
            }
            instance = null;
        }
    }

}

