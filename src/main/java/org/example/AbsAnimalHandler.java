package org.example;

import java.util.Scanner;

public class AbsAnimalHandler {
    static Scanner scanner = new Scanner(System.in);

    public static String inputAnimal() {
        System.out.println("Пожалуйста, введите животное, которое хотите добавить");
        String inputAnimal = scanner.nextLine().toLowerCase();

        while (!InputValidator.animalIsContains(inputAnimal)) {
            System.out.println("Вы ввели неверное животное \nПожалуйста, повторите попытку");
            inputAnimal = scanner.nextLine().toLowerCase();
            if (InputValidator.animalIsContains(inputAnimal)) {
                break;
            }
        }
        return inputAnimal;
    }

    public static String inputName() {
        System.out.println("Как вы назовете ваше животное?");
        String inputName = scanner.nextLine();
        while (!InputValidator.validateAnimalName(inputName)) {
            System.out.println("Вы ввели неверное имя животного \nПожалуйста, повторите попытку");
            inputName = scanner.nextLine();
            break;
        }
        return inputName;
    }

    public static int inputAge() {
        while (true) {
            System.out.println("Сколько лет вашему животному?");
            try {
                int inputAge = Integer.parseInt(scanner.nextLine());
                if (InputValidator.validateAnimalAge(inputAge)) {
                    return inputAge;
                } else {
                    System.out.println("Вы ввели неверный возраст животного \nПожалуйста, повторите попытку");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число для возраста \nПовторите попытку");
            }
        }
    }

    public static long inputWeight() {
        while (true) {
            System.out.println("Сколько весит ваше животное?");
            try {
                long inputWeight = Long.parseLong(scanner.nextLine());
                if (InputValidator.validateAnimalWeight(inputWeight)) {
                    return inputWeight;
                } else {
                    System.out.println("Вы ввели неверный вес животного \nПожалуйста, повторите попытку");
                }
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите число для веса \nПовторите попытку");
            }
        }
    }

    public static String inputColor() {
        System.out.println("Какого цвета ваше животное?");
        String inputColor = scanner.nextLine().toLowerCase();

        while (!InputValidator.validateAnimalColor(inputColor)) {
            System.out.println("Вы ввели неверный цвет животного \nПожалуйста, повторите попытку");
            inputColor = scanner.nextLine().toLowerCase();
            if (InputValidator.validateAnimalColor(inputColor)) {
                break;
            }
        }
        return inputColor;
    }
}
