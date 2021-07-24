package hw3;

import java.util.*;

public class SimplePhoneBook {

    private String name;
    private int phone;
    private Map<Integer, String> phoneMap = new HashMap<>();

    public void add(String name, int phone) {
        this.name = name;
        this.phone = phone;

        phoneMap.put(phone, name);
        System.out.printf("Фамилия: %s. Телефонный номер: %d\n", name, phone);

    }

    public void get(String name) {
        HashSet<Integer> setPhone = new HashSet<>();
        this.name = name;

        phoneMap.forEach((key, value) -> {
            if (value.equals(name)) {
                setPhone.add(key);

            }
        });
        System.out.println("За абонентом по фамилии " + name + " числятся следующие номера: " + setPhone);


    }
}