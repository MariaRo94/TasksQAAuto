package services;

public interface IMenuService {
    void startApplication();

    void printWelcomeMessage();

    void processCommand(String inputCommand);
}
