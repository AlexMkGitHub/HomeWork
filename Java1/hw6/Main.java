package Java1.hw6;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Мурзик", "чёрный", 3);
        Cat cat1 = new Cat("Барсик", "белый", 1);
        Cat cat2 = new Cat("Рыжик", "рыжий", 5);
        Cat cat3 = new Cat("Васька", "серый", 2);

        Dog dog = new Dog("Бобик", "рыжий", 5);
        Dog dog1 = new Dog("Тузик", "черный", 3);
        Dog dog2 = new Dog("Шарик", "серый", 2);


        cat.runAnimal(201);
        cat3.runAnimal(150);
        dog.runAnimal(470);
        dog2.runAnimal(501);
        cat1.swimAnimal(20);
        dog2.swimAnimal(5);
        System.out.println("Всего создано котов " + Cat.catNumbers + ".");
        System.out.println("Всего создано собак " + Dog.dogNumbers + ".");
    }
}
