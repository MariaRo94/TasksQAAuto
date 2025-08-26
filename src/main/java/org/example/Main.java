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
                        addAnimal();
                        break;
                    case LIST:
                        animalsToList();
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

    public static void addAnimal() {
        String inputAnimalType = inputAnimal();
        String inputName = inputName();
        int inputAge = inputAge();
        long inputWeight = inputWeight();
        String inputColor = inputColor();
        try {
            AbsAnimal newAnimal = createNewAnimal(inputAnimalType, inputName, inputAge, inputWeight, inputColor);
            absAnimalsList.add(newAnimal);
            System.out.println("Ура! Вы добавили новое животное!");
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось добавить новое животное");
        }
    }

    public static AbsAnimal createNewAnimal(String animalType, String name, int age, long weight, String color) {
        switch (animalType.toLowerCase()) {
            case "cat":
                return new Cat(name, age, weight, color);
            case "dog":
                return new Dog(name, age, weight, color);
            case "duck":
                return new Duck(name, age, weight, color);
            default:
                throw new IllegalArgumentException("Вы ввели неизвестное животное");

        }
    }

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


    public static void animalsToList() {
        if (!absAnimalsList.isEmpty())
        for (AbsAnimal absAnimal : absAnimalsList) {
            System.out.println("Список добавленных вами животных: " + "\n" + absAnimal);
        }
        else {
            System.out.println("Список живоных пуст");
        }
    }

}
