package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    private static ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static String getEnumNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Commands cmd : Commands.values()) {
            names.add(cmd.name());
        }
        return String.join(", ", names);
    }

    public static void main(String[] args) {


        while (true) {
            System.out.println("Добро пожаловать в консольное меню! Пожалуйста, введите команду!" +
                    "\n" + "Доступные команды: " + getEnumNames());

            String inputCommand = scanner.nextLine().toUpperCase().trim();


            try {
                Commands command = Commands.valueOf(inputCommand);
                switch (command) {
                    case ADD:
                        AbsAnimalService.addAnimal();
                        break;
                    case LIST:
                        AbsAnimalService.animalsToList();
                        break;
                    case EXIT:
                        System.out.println("Спасибо за использование нашего приложения! До скорых встреч!");
                        System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели неизвестную команду");
            }
        }
    }

}
