package database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {

    void tryConnection () throws SQLException, IOException;
    void closeConnection () throws SQLException;
    void execute (String sqlRequest) throws SQLException, IOException;
    ResultSet executeResultResponse(String  sqlRequest) throws SQLException, IOException;

    int executeUpdate(String sqlRequest) throws SQLException, IOException;
}
