package org.example;

public abstract class AbsAnimal {
    private String name;
    private int age;
    private float weight;
    private String color;

    public AbsAnimal(String name, int age, long weight, String color) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go(){
        System.out.println("Я иду");
    }

    public void drink(){
        System.out.println("Я пью");
    }

    public void eat (){
        System.out.println("Я ем");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String getAgeContext(int age) {
        int lastDigit = age % 10;
        int lastTwoDigits = age % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            return " лет";
        }
        switch (lastDigit) {
            case 1:
                return " год";
            case 2, 3, 4:
                return " года";
            default:
                return " лет";
        }
    }

    @Override
    public String toString() {
        String ageContext = getAgeContext(age);
        return String.format("Привет! Меня зовут %s, мне %d%s, я вешу - %d кг, мой цвет - %s",
                name, age, ageContext, weight, color);
    }
}
