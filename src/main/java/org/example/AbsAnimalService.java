package org.example;

import java.util.ArrayList;
import java.util.Scanner;


public class AbsAnimalService {
    static ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();

    public static void addAnimal() {
        String inputAnimalType = AbsAnimalHandler.inputAnimal();
        String inputName = AbsAnimalHandler.inputName();
        int inputAge = AbsAnimalHandler.inputAge();
        long inputWeight = AbsAnimalHandler.inputWeight();
        String inputColor = AbsAnimalHandler.inputColor();
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
