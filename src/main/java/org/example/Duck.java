package org.example;

public class Duck extends AbsAnimal implements IFlying {

    public Duck(String name, int age, long weight, String color) {
        super(name, age, weight, color);
    }

    public static void fly() {
        System.out.println("Я лечу!");
    }

    @Override
    public void say() {
        System.out.println("Кря!");
    }
}
