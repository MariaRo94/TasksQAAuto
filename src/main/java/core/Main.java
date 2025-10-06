package core;

import animals.AbsAnimal;
import services.AbsAnimalService;
import services.ConsoleMenuService;
import tables.AnimalTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final AnimalTable animalTable = new AnimalTable("animals");
    static {
        try {
            animalTable.initializeDataBase();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        AbsAnimalService animalService = new AbsAnimalService();
        ConsoleMenuService consoleMenuService = new ConsoleMenuService(animalService, scanner);

        consoleMenuService.startApplication();
    }
}

