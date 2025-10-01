package tables;

import animals.AbsAnimal;
import database.IDBConnector;
import database.MySQLConnector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbsDB<T> implements IDB<T> {

    private IDBConnector dbConnector = new MySQLConnector();

    String tableName;

    public AbsDB(String tableName) {
        this.tableName = tableName;
    }

    public ResultSet getAnimal(String[] columns, String[] predicates) throws SQLException, IOException {

        String columnsInRequest = columns.length == 0 ? "*" : String.join(",", columns);
        String whereClause = predicates.length == 0 ? "" : "WHERE " + String.join(" AND ", predicates);

        String sqlRequest = String.format("SELECT %s FROM %s %s",
                columnsInRequest, tableName, whereClause);

        return dbConnector.executeResultResponse(sqlRequest);
    }

    public void initializeDataBase() throws SQLException, IOException{
                createDataBase();
                createTable(tableName);
                System.out.println("База данных и таблица успешно созданы");
    }

    public void createDataBase() throws SQLException, IOException {

        String createDb = "CREATE DATABASE IF NOT EXISTS animals_db " +
                "CHARACTER SET utf8mb4 " +
                "COLLATE utf8mb4_unicode_ci";

        dbConnector.execute(createDb);

        String useDb = "USE animals_db";
        dbConnector.execute(useDb);

    }


    @Override
    public void addAnimalToDB(AbsAnimal animal) throws SQLException, IOException {
        String animalType = animal.getClass().getSimpleName().toLowerCase();


        String sqlRequest = String.format(
                "INSERT INTO %s (name, age, weight, color, animal_type) VALUES ('%s', %d, %f, '%s', '%s')",
                "animals_db.animals",
                animal.getName(),
                animal.getAge(),
                (double)animal.getWeight(),
                animal.getColor(),
                animalType

        );

       dbConnector.executeUpdate(sqlRequest);

    }

    public void createTable(String tableName) throws SQLException, IOException {

        String sqlStatement = String.format("CREATE TABLE IF NOT EXISTS %s (name VARCHAR(15) NOT NULL, age INT NOT NULL, weight VARCHAR (100), color VARCHAR(15), " +
                "animal_type VARCHAR(10)  ); ", tableName);
        dbConnector.execute(sqlStatement);
    }

    @Override
    public void deleteTable() throws SQLException {

    }
}
