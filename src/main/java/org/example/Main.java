package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static ArrayList<AbsAnimal> absAnimals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    private static Set<String> animalSet = Set.of("cat", "dog", "duck");

    private static boolean animalIsContains(String inputAnimal) {
        return animalSet.contains(inputAnimal);
    }

    private static boolean validateAnimalName(String inputName){
        return inputName.matches("^[a-zA-Zа-яА-Я\\\\s]{2,32}$");
    }

    private static boolean validateAnimalAge(int inputAge){
        String numberStr = String.valueOf(inputAge);
        return numberStr.matches("^(0|[1-9][0-9]?)$");
    }

    private static boolean validateAnimalWeight(long inputWeight){
        String numberStr = String.valueOf(inputWeight);
        return numberStr.matches("^(0|[1-9][0-9]?)$");
    }

    private static boolean validateAnimalColor(String inputColor){
        return inputColor.matches("^[a-zA-Zа-яА-Я\\s]{2,16}$");
    }

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
        String inputAnimal = scanner.nextLine().toLowerCase();

        while (!animalIsContains(inputAnimal)) {
            System.out.println("Вы ввели неверное животное \nПожалуйста, повторите попытку");
            inputAnimal = scanner.nextLine().toLowerCase();
            if (animalIsContains(inputAnimal)){
                break;
            }
        }

        System.out.println("Как вы назовете ваше животное?");
        String inputName = scanner.nextLine();
        if(validateAnimalName(inputName)== false){
            System.out.println("Вы ввели неверное имя животного \nПожалуйста, повторите попытку");
            inputName= scanner.nextLine();
        }

        System.out.println("Сколько лет вашему животному?");
        int inputAge = Integer.parseInt(scanner.nextLine());
        if(validateAnimalAge(inputAge)== false){
            System.out.println("Вы ввели неверный возраст животного \nПожалуйста, повторите попытку");
            inputAge= Integer.parseInt(scanner.nextLine());
        }

        System.out.println("Сколько весит ваше животное?");
        long inputWeight = Long.parseLong(scanner.nextLine());
        if(validateAnimalWeight(inputWeight)){
            System.out.println("Вы ввели неверный вес животного \nПожалуйста, повторите попытку");
            inputWeight= Long.parseLong(scanner.nextLine());
        }

        System.out.println("Какого цвета ваше животное?");
        String inputColor = scanner.nextLine();
        if(validateAnimalColor(inputColor)== false){
            System.out.println("Вы ввели неверный цвет животного \nПожалуйста, повторите попытку");
            inputColor= scanner.nextLine();
        }

        AbsAnimal newAbsAnimal;
        switch (inputAnimal) {
            case "cat":
                newAbsAnimal = new Cat(inputName, inputAge, inputWeight, inputColor);
                newAbsAnimal.say();
                break;
            case "dog":
                newAbsAnimal = new Dog(inputName, inputAge, inputWeight, inputColor);
                newAbsAnimal.say();
                break;
            case "duck":
                newAbsAnimal = new Duck(inputName, inputAge, inputWeight, inputColor);
                newAbsAnimal.say();
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
