package animals;

public class Cat extends AbsAnimal {
    public Cat(String name, int age, long weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void say() {
        System.out.println("Мяу!");
    }
}
