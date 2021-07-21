package Java2.hw1;

public class Robot extends Actions {

    public Robot() {

    }

    ;

    public Robot(String name, int maxJump, int maxRun) {
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
        System.out.println("Робот " + name + " подпрыгнул на " + jump + " сантиметров.");
        return false;
    }

    @Override
    public boolean run(int run) {
        System.out.println("Робот " + name + " пробежал " + run + " метров.");
        return false;
    }
}
