package hw6;

public class Animal {
    String name;
    String color;
    int age;

    public Animal() {

    }

    public Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public void runAnimal(int distanceLength) {
        System.out.println(name + " пробежал " + distanceLength + "м");
    }

    public void swimAnimal(int distanceSwim) {
        System.out.println(name + " проплыл " + distanceSwim + "м");
    }

}