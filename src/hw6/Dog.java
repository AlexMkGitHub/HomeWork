package hw6;

public class Dog extends Animal {
   private static int dogNumbers = 0;

    {
        dogNumbers += 1;
    }

    public Dog(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public void runAnimal(int distanceLength) {
        if (distanceLength > 500) {
            System.out.println("Извините, но пёс " + name + " не может бежать больше 500м!");
        } else {
            System.out.println("Пёс " + name + " пробежал " + distanceLength + "м");
        }
    }

    @Override
    public void swimAnimal(int distanceSwim) {
        if (distanceSwim > 10) {
            System.out.println("Извините, но собака " + name + " не может проплыть больше 500м!");
        } else {
            System.out.println(name + " проплыл " + distanceSwim + "м");
        }
    }

    public static int getDogNumbers() {
        return dogNumbers;
    }
}