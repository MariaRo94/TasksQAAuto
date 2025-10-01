package services;

import animals.AbsAnimal;
import handlers.AbsAnimalHandler;
import animals.Cat;
import animals.Dog;
import animals.Duck;
import tables.AnimalTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class AbsAnimalService {
    static ArrayList<AbsAnimal> absAnimalsList = new ArrayList<>();
    AnimalTable animalTable = new AnimalTable("animals");

    public void addAnimal() {
        AbsAnimalHandler absAnimalHandler = new AbsAnimalHandler();

        String inputAnimalType = absAnimalHandler.inputAnimal();
        String inputName = absAnimalHandler.inputName();
        int inputAge = absAnimalHandler.inputAge();
        long inputWeight = absAnimalHandler.inputWeight();
        String inputColor = absAnimalHandler.inputColor();
        try {
            AbsAnimal newAnimal = createNewAnimal(inputAnimalType, inputName, inputAge, inputWeight, inputColor);
            absAnimalsList.add(newAnimal);
            System.out.println("Ура! Вы добавили новое животное!");
        } catch (IllegalArgumentException e) {
            System.out.println("Не удалось добавить новое животное");
        } catch (SQLException e) {
            throw new RuntimeException(e + "Ошибка добавления животного в базу данных");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AbsAnimal createNewAnimal(String animalType, String name, int age, long weight, String color) throws SQLException, IOException {
        AbsAnimal animal =  switch (animalType.toLowerCase()) {
            case "cat" -> new Cat(name, age, weight, color);
            case "dog" -> new Dog(name, age, weight, color);
            case "duck" -> new Duck(name, age, weight, color);
            default -> throw new IllegalArgumentException("Вы ввели неизвестное животное");
        };

        animalTable.addAnimalToDB(animal);

        return animal;
    }

    public void animalsToList() {
        if (!absAnimalsList.isEmpty())
            for (AbsAnimal absAnimal : absAnimalsList) {
                System.out.println("Список добавленных вами животных: " + "\n" + absAnimal);
            }
        else {
            System.out.println("Список живоных пуст");
        }
    }
}
