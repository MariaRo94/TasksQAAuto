package core;

import animals.AbsAnimal;
import services.AbsAnimalService;
import services.ConsoleMenuService;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AbsAnimalService animalService = new AbsAnimalService();
        ConsoleMenuService consoleMenuService = new ConsoleMenuService(animalService, scanner);

        consoleMenuService.startApplication();
    }
 }

