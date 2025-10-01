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

    private PropertiesConfigReader propertiesConfigReader = new PropertiesConfigReader();

    private Connection connection = null;
    private Statement statement = null;

    public void tryConnection() throws SQLException, IOException {
        Map<String, String > creds = propertiesConfigReader.getCredentials();
        if (connection == null) {
            connection = DriverManager.getConnection(creds.get("URL"), creds.get("USER"), creds.get("PASSWORD"));
        }
        if (statement == null) {
            statement = connection.createStatement();
        }
    }

    public void execute(String sqlRequest) throws SQLException, IOException {
        tryConnection();
        statement.execute(sqlRequest);
    }

    public ResultSet executeResultResponse(String sqlRequest) throws SQLException, IOException{
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
        }
        if (connection != null) {
            connection.close();
        }
    }

}

