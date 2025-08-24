package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static ArrayList<AbsAnimal> animals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;

    public static void main(String[] args) {


        System.out.println("Добро пожаловать! Пожалуйста, введите команду: " +
                "\n"+ String.join(" , ", getEnumNames()));

        String inputCommand = scanner.nextLine().toUpperCase().trim();

        boolean isCommandExist = false;
        for (String cmd : getEnumNames()) {
            if (cmd.equals(inputCommand)) {
                isCommandExist = true;
                break;
            } else {
                System.out.println("Вы ввели неверную команду");
            }

//            try {
//                Commands command = Commands.valueOf(inputCommand);
//                switch (command) {
//                    case ADD:
//                        addAnimal();
//                        break;
//                    case LIST:
//                        animalsToList();
//                        break;
//                    case EXIT:
//                        System.out.println("Спасибо за использование нашего приложения! До скорых встреч!");
//                        scanner.close();
//                        isRunning= false;
//                        break;
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Вы ввели неизвестную команду");
//            }
//        }
//    }
//
//    public static void addAnimal() {
//
//        System.out.println("Пожалуйста, введите животное, которое хотите добавить");
//        String inputAnimal = scanner.nextLine().toLowerCase();
//        System.out.println("Как вы назовете ваше животное?");
//        String inputName = scanner.nextLine();
//        System.out.println("Сколько лет вашему животному?");
//        int inputAge = Integer.parseInt(scanner.nextLine());
//        System.out.println("Сколько весит ваше животное?");
//        long inputWeight = Long.parseLong(scanner.nextLine());
//        System.out.println("Какого цвета ваше животное?");
//        String inputColor = scanner.nextLine();
//
//        AbsAnimal newAbsAnimal = null;
//        switch (inputAnimal) {
//            case "cat":
//                newAbsAnimal = new Cat(inputName, inputAge, inputWeight, inputColor);
//                break;
//            case "dog":
//                newAbsAnimal = new Dog(inputName, inputAge, inputWeight, inputColor);
//                break;
//            case "duck":
//                newAbsAnimal = new Duck(inputName, inputAge, inputWeight, inputColor);
//                break;
//            default:
//                System.out.println("Неизвестный тип животного: " + inputAnimal);
//                return;
//        }
//        if (newAbsAnimal != null) {
//            newAbsAnimal.say();
//            animals.add(newAbsAnimal);
//            System.out.println("Вы успешно добавили животное в список!");
//        }
//    }
//
//    public static void animalsToList() {
//        if (!animals.isEmpty()) {
//            for (AbsAnimal animal : animals) {
//                System.out.println("Список добавленных вами животных: " + "\n" + animal);
//            }
//        } else {
//            System.out.println("Список животных пуст");
//        }
//    }
        }
    }
            public static List<String> getEnumNames() {
                return Arrays.stream(Commands.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            }
        }
