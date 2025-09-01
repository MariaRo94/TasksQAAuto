package animals;

public class Duck extends AbsAnimal implements IFlying {

    public Duck(String name, int age, long weight, String color) {
        super(name, age, weight, color);
    }

    @Override
    public void fly() {
        System.out.println("Я лечу!");
    }

    @Override
    public void say() {
        System.out.println("Кря!");
    }
}
