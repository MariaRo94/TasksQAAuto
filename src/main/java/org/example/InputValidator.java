package org.example;

import java.util.Set;

public class InputValidator {

    static Set<String> animalSet = Set.of("cat", "dog", "duck");

    public static boolean animalIsContains(String inputAnimal) {
        return animalSet.contains(inputAnimal);
    }

    public static boolean validateAnimalName(String inputName) {
        return inputName.matches("^[a-zA-Zа-яА-Я\\\\s]{2,32}$");
    }

    public static boolean validateAnimalAge(int inputAge) {
        return Math.abs(inputAge) <= 99;
    }

    public static boolean validateAnimalWeight(long inputWeight) {
       return Math.abs(inputWeight) <= 99;
    }

    public static boolean validateAnimalColor(String inputColor) {
        return inputColor.matches("^[a-zA-Zа-яА-Я\\s]{2,16}$");
    }

}
