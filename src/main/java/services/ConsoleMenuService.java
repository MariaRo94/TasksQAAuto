package services;

import tables.AnimalTable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleMenuService implements IMenuService {
    private AbsAnimalService absAnimalService = new AbsAnimalService();
    private Scanner scanner = new Scanner(System.in);
    private static AnimalTable animalTable = new AnimalTable("animals");

    public ConsoleMenuService(AbsAnimalService absAnimalService, Scanner scanner) {
        this.absAnimalService = absAnimalService;
        this.scanner = scanner;
    }

    public String getEnumNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Commands cmd : Commands.values()) {
            names.add(cmd.name());
        }
        return String.join(", ", names);
    }

    @Override
    public void startApplication() {
        while (true) {
            printWelcomeMessage();
            String inputCommand = scanner.nextLine().toUpperCase().trim();
            processCommand(inputCommand);
        }
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Добро пожаловать в консольное меню! Пожалуйста, введите команду!" +
                "\n" + "Доступные команды: " + getEnumNames());
    }


    @Override
    public void processCommand(String inputCommand) {

        if (inputCommand == null || inputCommand.isBlank()) {
            System.out.println("Неверная команда. Пожалуйста, попробуйте снова.\n");
            return;
        }

            try {
                Commands command = Commands.valueOf(inputCommand);
                switch (command) {
                    case ADD:
                        absAnimalService.addAnimal();
                        break;
                    case LIST:
                        absAnimalService.animalsToList();
                        break;
                    case EXIT:
                        System.out.println("Спасибо за использование нашего приложения! До скорых встреч!");
                        animalTable.exitDataBase();
                        System.exit(0);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели неизвестную команду");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    }

