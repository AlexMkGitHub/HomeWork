package hw6;

public class Cat extends Animal {
    static int catNumbers = 0;

    {
        catNumbers += 1;
    }


    public Cat(String name, String color, int age) {
        super(name, color, age);
    }


    @Override
    public void runAnimal(int distanceLength) {
        if (distanceLength > 200) {
            System.out.println("Извините, но кот " + name + " не может бежать больше 200м!");
        } else {
            System.out.println("Кот " + name + " пробежал " + distanceLength + "м");
        }
    }

    @Override
    public void swimAnimal(int distanceSwim) {
        System.out.println("Извините, но коты не умеют плавать!");
    }
}