package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Animal> animals = new ArrayList<>();
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

        Animal newAnimal = null;
        switch (inputAnimal) {
            case "cat":
                newAnimal = new Cat(inputName, inputAge, inputWeight, inputColor);
                newAnimal.say();
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
        if (newAnimal != null) {
            newAnimal.say();
            animals.add(newAnimal);
            System.out.println("Вы успешно добавили животное в список!");
        }
    }

    public static void animalsToList() {
        if (animals.isEmpty())
            System.out.println("Список животных пуст");
        for (Animal animal : animals) {
            System.out.println("Список добавленных вами животных: " + "\n" + animal);
        }
    }
}
