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
    String animalDBName;

    public AbsDB(String tableName) {
        this.tableName = tableName;
        this.animalDBName = "animals_db";
    }

    public ResultSet getAnimal(String[] columns, String[] predicates) throws SQLException, IOException {

        String columnsInRequest = columns.length == 0 ? "*" : String.join(",", columns);
        String whereClause = predicates.length == 0 ? "" : "WHERE " + String.join(" AND ", predicates);

        String sqlRequest = String.format("SELECT %s FROM %s %s",
                columnsInRequest, tableName, whereClause);

        return dbConnector.executeResultResponse(sqlRequest);
    }

    public void initializeDataBase() throws SQLException, IOException {
        createDataBase();
        createTable(tableName);
        System.out.println("База данных и таблица успешно созданы");
    }

    public void createDataBase() throws SQLException, IOException {

        String createDb = String.format("CREATE DATABASE IF NOT EXISTS %s " +
                "CHARACTER SET utf8mb4 " +
                "COLLATE utf8mb4_unicode_ci", animalDBName);

        dbConnector.execute(createDb);

        String useDb = "USE animals_db";
        dbConnector.execute(useDb);
        System.out.println("База данных успешно создана");

    }


    @Override
    public void addAnimalToDB(AbsAnimal animal) throws SQLException, IOException {
        String animalType = animal.getClass().getSimpleName().toLowerCase();

        String sqlRequest = String.format(
                "INSERT INTO %s.%s (name, age, weight, color, animal_type) " +
                        "VALUES ('%s', %d, %f, '%s', '%s')",
                animalDBName, tableName,
                animal.getName(),
                animal.getAge(),
                animal.getWeight(),
                animal.getColor(),
                animalType

        );

        dbConnector.executeUpdate(sqlRequest);
        System.out.println("Животное успешно добавлено в базу данных");

    }

    public void createTable(String tableName) throws SQLException, IOException {

        String sqlStatement = String.format("CREATE TABLE IF NOT EXISTS %s (" +
                "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(15), " +
                "age INT, " +
                "weight VARCHAR(100), " +
                "color VARCHAR(15), " +
                "animal_type VARCHAR(10)" +
                ");", tableName);
        dbConnector.execute(sqlStatement);
        System.out.println("Таблица создана");
    }

    @Override
    public void deleteTable() throws SQLException, IOException {
        String sqlRequest = String.format("DROP TABLE IF EXISTS %s.%s", animalDBName, tableName);
        dbConnector.executeUpdate(sqlRequest);
        System.out.println("Таблица данных удалена");
    }

    @Override
    public void dropDataBase() throws SQLException, IOException {
        String sqlRequest = String.format("DROP DATABASE IF EXISTS %s", animalDBName);
        dbConnector.executeUpdate(sqlRequest);
        System.out.println("База данных удалена");
    }

    public void exitDataBase() throws SQLException, IOException {
        deleteTable();
        dropDataBase();
        dbConnector.closeConnection();
    }
}
