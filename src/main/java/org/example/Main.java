package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<AbsAnimal> absAnimals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        while (true) {
            System.out.println("Добро пожаловать в консольное меню! Пожалуйста, введите команду!" +
                    "\n" + "Доступные команды: ADD, LIST, EXIT");

            String inputCommand = scanner.nextLine().toUpperCase().trim();
            try {
                Commands command = Commands.valueOf(inputCommand);
                switch (command) {
                    case ADD:
                        addAnimal();
                        break;
                    case LIST:
                        animalsToList();
                    case EXIT:
                        System.out.println("Спасибо за использование нашего приложения! До скорых встреч!");
                        scanner.close();
                        return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели неизвестную команду");
            }
        }
    }

    public static void addAnimal() {

        System.out.println("Пожалуйста, введите животное, которое хотите добавить");
        Scanner scanner = new Scanner(System.in);
        String inputAnimal = scanner.nextLine().toLowerCase();
        System.out.println("Как вы назовете ваше животное?");
        String inputName = scanner.nextLine();
        System.out.println("Сколько лет вашему животному?");
        int inputAge = Integer.parseInt(scanner.nextLine());
        System.out.println("Сколько весит ваше животное?");
        long inputWeight = Long.parseLong(scanner.nextLine());
        System.out.println("Какого цвета ваше животное?");
        String inputColor = scanner.nextLine();

        AbsAnimal newAbsAnimal = null;
        switch (inputAnimal) {
            case "cat":
                newAbsAnimal = new Cat(inputName, inputAge, inputWeight, inputColor);
                newAbsAnimal.say();
                break;
            case "dog":
                Dog dog = new Dog(inputName, inputAge, inputWeight, inputColor);
                dog.say();
                break;
            case "duck":
                Duck duck = new Duck(inputName, inputAge, inputWeight, inputColor);
                break;
            default:
                System.out.println("Неизвестный тип животного: " + inputAnimal);
                return;
        }
        if (newAbsAnimal != null) {
            newAbsAnimal.say();
            absAnimals.add(newAbsAnimal);
            System.out.println("Вы успешно добавили животное в список!");
        }
    }

    public static void animalsToList() {
        if (absAnimals.isEmpty())
            System.out.println("Список животных пуст");
        for (AbsAnimal absAnimal : absAnimals) {
            System.out.println("Список добавленных вами животных: " + "\n" + absAnimal);
        }
    }
}
