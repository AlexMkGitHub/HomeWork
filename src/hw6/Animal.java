package hw6;

public class Animal {
    String name;
    String color;
    int age;
    public static int animalNumber = 0;

    public Animal() {

    }

    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    {
        animalNumber += 1;
    }

    public void runAnimal(int distanceLength) {
        System.out.println(name + " пробежал " + distanceLength + "м");
    }

    public void swimAnimal(int distanceSwim) {
        System.out.println(name + " проплыл " + distanceSwim + "м");
    }

    public static int getAnimalNumber() {
        return animalNumber;
    }
}