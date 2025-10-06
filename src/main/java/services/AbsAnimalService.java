package services;

import animals.AbsAnimal;
import handlers.AbsAnimalHandler;
import animals.Cat;
import animals.Dog;
import animals.Duck;
import tables.AnimalTable;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


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
        try {
            printResultSet(animalTable.selectAllAnimals(), "\nВсе животные в базе данных:");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void editAnimal() {
        Scanner scanner = new Scanner(System.in);
        AbsAnimalHandler absAnimalHandler = new AbsAnimalHandler();
        try {
            System.out.println("Введите ID животного, которое нужно обновить:");
            String idInput = scanner.nextLine().trim();
            int id = Integer.parseInt(idInput);

            // Check existence before asking for new values
            ResultSet existing = animalTable.getAnimal(new String[]{"id"}, new String[]{"id = " + id});
            if (!existing.next()) {
                System.out.println("Ошибка: Животного с таким ID нет в базе данных.");
                return;
            }

            String name = absAnimalHandler.inputName();
            int age = absAnimalHandler.inputAge();
            long weightLong = absAnimalHandler.inputWeight();
            double weight = (double) weightLong;

            int updated = animalTable.updateAnimal(id, name, age, weight);
            if (updated == 0) {
                System.out.println("Животное с таким ID не найдено или данные не изменились.");
            } else {
                System.out.println("Данные животного успешно обновлены.");
                printResultSet(animalTable.getAnimal(new String[]{}, new String[]{"id = " + id}), "Обновлено:");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода числовых данных. Попробуйте снова.");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectAnimal() {
        AbsAnimalHandler absAnimalHandler = new AbsAnimalHandler();
        String animalType = absAnimalHandler.inputAnimal();
        try {
            printResultSet(animalTable.selectAnimalByType(animalType), "\nРезультаты фильтрации по типу: " + animalType.toLowerCase());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printResultSet(ResultSet resultSet, String header) throws SQLException {
        System.out.println(header);
        boolean hasRows = false;
        while (resultSet.next()) {
            hasRows = true;
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String weight = resultSet.getString("weight");
            String color = resultSet.getString("color");
            String type = resultSet.getString("animal_type");
            System.out.printf("ID: %d | name=%s | age=%d | weight=%s | color=%s | type=%s%n",
                    id, name, age, weight, color, type);
        }
        if (!hasRows) {
            System.out.println("Нет записей.");
        }
    }
    
    
}
