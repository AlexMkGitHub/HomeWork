package hw3;

public class Main_PhoneBook {


    public static void main(String[] args) {

        SimplePhoneBook book = new SimplePhoneBook();
        book.add("Иванов", 2257000);
        book.add("Иванов", 2257030);
        book.add("Иванов", 2255000);
        book.add("Иванов", 2257080);
        book.add("Петров", 4257000);
        book.add("Петров", 2257020);
        book.add("Петров", 2297000);
        book.add("Петров", 2257011);
        book.add("Сидоров", 7257000);
        book.add("Васильев", 2257200);
        book.add("Васильев", 2269000);

        System.out.println();

        System.out.println("Васильев: " + book.get("Васильев"));
        System.out.println("Иванов: " + book.get("Иванов"));
        System.out.println("Сидоров: " + book.get("Сидоров"));
        System.out.println("Петров: " + book.get("Петров"));

    }
}

