package hw5;

public class Main_hw5 {

    public static void main(String[] args) {

        Person person1 = new Person("Ivanov Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30000,
                30);
        Person person2 = new Person("Petrov Ilya",
                "Engineer",
                "petrov@mailbox.com",
                "892357890",
                35000,
                40);
        Person person3 = new Person("Sidorov Evgeniy",
                "Tester",
                "sidorov@mailbox.com",
                "892342155",
                27000,
                28);
        Person person4 = new Person("Koshkin Andrey",
                "Administration",
                "koshkin@mailbox.com",
                "892390101",
                70000,
                42);
        Person person5 = new Person("Volin Nikita",
                "Driver",
                "driverman@mailbox.com",
                "892350034",
                38000,
                25);

//Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
        System.out.println("Задание 3:");
        System.out.println(person1.toString());
        System.out.println();

        Person[] person = new Person[5];
        person[0] = new Person("Ivanov Ivan",
                "Engineer",
                "ivivan@mailbox.com",
                "892312312",
                30000,
                30);
        person[1] = new Person("Petrov Ilya",
                "Engineer",
                "petrov@mailbox.com",
                "892357890",
                35000,
                41);
        person[2] = new Person("Sidorov Evgeniy",
                "Tester",
                "sidorov@mailbox.com",
                "892342155",
                27000,
                28);
        person[3] = new Person("Koshkin Andrey",
                "Administration",
                "koshkin@mailbox.com",
                "892390101",
                70000,
                42);
        person[4] = new Person("Volin Nikita",
                "Driver",
                "driverman@mailbox.com",
                "892350034",
                38000,
                25);


        System.out.println("Задание 5:");
// 5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
        for (int i = 0; i < person.length; i++) {
            if (person[i].getAge() > 40) {
                System.out.println(person[i]);

            }
        }
    }
}
