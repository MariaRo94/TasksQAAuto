package validation;

import java.util.Set;

public class InputValidator {

    private static final int MAX_AGE = 99;
    private static final int MIN_AGE = 0;
    private static final String REGEX_STRING = "^[a-zA-Zа-яА-Я\\\\s]{2,32}$";

    static Set<String> animalSet = Set.of("cat", "dog", "duck");

    public static boolean animalIsContains(String inputAnimal) {

        return animalSet.contains(inputAnimal);
    }

    public static boolean validateAnimalName(String inputName) {
        return inputName.matches(REGEX_STRING);
    }

    public static boolean validateAnimalAge(int inputAge) {
        return inputAge <= MAX_AGE && inputAge > MIN_AGE;
    }

    public static boolean validateAnimalWeight(long inputWeight) {
        return inputWeight <= MAX_AGE && inputWeight > MIN_AGE;
    }

    public static boolean validateAnimalColor(String inputColor) {
        return inputColor.matches(REGEX_STRING);
    }

}
