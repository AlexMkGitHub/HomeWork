package Java1.hw5;

public class Person {

    private String name;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person() {

    }

    public Person(String name, String position, String email, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName(String name) {
        this.name = name;
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Position: " + position + ", Email: " + email + ", Phone: " + phone + ", Salary: " + salary +
                ", Age: " + age;
    }

    public int getAge() {
        this.age = age;
        return age;
    }
}
