package hw1;

public class Cat extends Actions {

    public Cat() {
    }

    public Cat(String name, int maxJump, int maxRun) {
        this.name = name;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    public String getName() {
        return name;
    }

    public int getMaxJump() {
        return maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }

    @Override
    public boolean jump(int jump) {
        System.out.println("Кот " + name + " подпрыгнул на " + jump + " сантиметров.");
        return false;
    }

    @Override
    public boolean run(int run) {
        System.out.println("Кот " + name + " пробежал " + run + " метров.");
        return false;
    }
}
