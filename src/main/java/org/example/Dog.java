package org.example;

public class Dog extends AbsAnimal {

    public Dog(String name, int age, long weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.println("Гав!");
    }
}
