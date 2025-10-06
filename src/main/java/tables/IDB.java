package tables;

import animals.AbsAnimal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDB <T>{
    void createDataBase() throws SQLException, IOException;
    void initializeDataBase() throws SQLException, IOException;
    ResultSet getAnimal(String [] columns, String [] predicates) throws SQLException, IOException;
    void addAnimalToDB(AbsAnimal animal) throws SQLException, IOException;
    void createTable (String tableName) throws SQLException, IOException;
    void deleteTable () throws SQLException, IOException;
    void dropDataBase () throws SQLException, IOException;
    void exitDataBase () throws SQLException, IOException;
    ResultSet selectAllAnimals() throws SQLException, IOException;
    int updateAnimal(int id, String name, int age, double weight) throws SQLException, IOException;
}
