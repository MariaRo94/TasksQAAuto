package org.example;

import animals.AbsAnimal;
import services.AbsAnimalService;
import services.ConsoleMenuService;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static final ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


//
//    public static String getEnumNames() {
//        ArrayList<String> names = new ArrayList<>();
//        for (Commands cmd : Commands.values()) {
//            names.add(cmd.name());
//        }
//        return String.join(", ", names);
//    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AbsAnimalService animalService = new AbsAnimalService();
        ConsoleMenuService consoleMenuService = new ConsoleMenuService(animalService, scanner);

        consoleMenuService.startApplication();
    }

//
//        while (true) {
//            System.out.println("Добро пожаловать в консольное меню! Пожалуйста, введите команду!" +
//                    "\n" + "Доступные команды: " + getEnumNames());
//
//            String inputCommand = scanner.nextLine().toUpperCase().trim();
//
//            AbsAnimalService absAnimalService = new AbsAnimalService();
//
//
//            try {
//                Commands command = Commands.valueOf(inputCommand);
//                switch (command) {
//                    case ADD:
//                        absAnimalService.addAnimal();
//                        break;
//                    case LIST:
//                        absAnimalService.animalsToList();
//                        break;
//                    case EXIT:
//                        System.out.println("Спасибо за использование нашего приложения! До скорых встреч!");
//                        System.exit(0);
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Вы ввели неизвестную команду");
//            }
//        }
 }

