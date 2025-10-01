package tables;

import animals.AbsAnimal;

import java.sql.SQLException;

public class AnimalTable extends AbsDB <AbsAnimal>{
    public AnimalTable(String tableName) {
        super("animals");
    }
}
