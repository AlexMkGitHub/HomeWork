package Java2.hw1;

public class Main_Object_Interface {

    public static void main(String[] args) {

        Actions[] actions = {
                new Human("Dmitriy", 150, 250),
                new Human("Vladimir", 170, 270),
                new Cat("Barsik", 200, 150),
                new Cat("Murzik", 190, 210),
                new Robot("Ar2D2", 250, 450),
                new Robot("Nomber5", 320, 550)
        };

        Wall wall = new Wall(200);
        RunningTrack runningTrack = new RunningTrack(250);

        Obstacles[] obstacles = {
                wall,
                runningTrack
        };

     //3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.
     // Объекты просто проходят препятствия без условий.

        for (int i = 0, obstaclesLength = obstacles.length; i < obstaclesLength; i++) {
            Obstacles obstacle = obstacles[i];
            if (obstacle == wall) {
                for (Actions action : actions) {
                    action.jump(wall.getWallHeight());
                }
            }
            if (obstacle == runningTrack) {
                for (Actions action : actions) {
                    action.run(runningTrack.getTrackLength());
                }
            }
        }
        System.out.println();

        // Объекты взаимодействуют с препятствиями согласно своим данным.

        for (int i = 0, actionsLength = actions.length; i < actionsLength; i++) {
            Actions value = actions[i];
            String h = "";
            if (value instanceof Human) {
                h = "Человек ";
            }
            if (value instanceof Cat) {
                h = "Кот ";
            }
            if (value instanceof Robot) {
                h = "Робот ";
            }
            if (wall.getWallHeight() > value.maxJump) {
                System.out.println("СТОП! Стена высотой " + wall.getWallHeight() + " сантиметров! A "
                        + h + value.name + " может пригнуть только на " + value.maxJump + " сантиметров!");
            } else {
                System.out.println("Отлично! " + h + value.name + " подпрыгнул на " + value.maxJump +
                        " сантиметров и перепрыгнул стену, высотой " + wall.getWallHeight() + " сантиметров!");
            }
            if (runningTrack.getTrackLength() > value.maxRun) {
                System.out.println("СТОП! Дорожка длиной  " + runningTrack.getTrackLength() + " метров! A "
                        + h + value.name + " может пробежать только " + value.maxRun + " метров!");
            } else {
                System.out.println("Отлично! " + h + value.name + " пробежал " + value.maxRun +
                        " метров! Это больше чем длина дорожки " + runningTrack.getTrackLength() + " метров!");
            }
        }

        System.out.println();


        /* 4. У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
                Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.*/

        for (int i = 0, actionsLength = actions.length; i < actionsLength; i++) {
            Actions action = actions[i];
            String h = "";
            if (action instanceof Human) {
                h = "Человек ";
            }
            if (action instanceof Cat) {
                h = "Кот ";
            }
            if (action instanceof Robot) {
                h = "Робот ";
            }
            if (wall.getWallHeight() > action.maxJump) {
                System.out.println("СТОП! Стена высотой " + wall.getWallHeight() + " сантиметров! A "
                        + h + action.name + " может пригнуть только на " + action.maxJump + " сантиметров!");
                continue;
            } else {
                System.out.println("Отлично! " + h + action.name + " подпрыгнул на " + action.maxJump +
                        " сантиметров и перепрыгнул стену, высотой " + wall.getWallHeight() + " сантиметров!");
            }
            if (runningTrack.getTrackLength() > action.maxRun) {
                System.out.println("СТОП! Дорожка длиной  " + runningTrack.getTrackLength() + " метров! A "
                        + h + action.name + " может пробежать только " + action.maxRun + " метров!");
            } else {
                System.out.println("Отлично! " + h + action.name + " пробежал " + action.maxRun +
                        " метров! Это больше чем длина дорожки " + runningTrack.getTrackLength() + " метров!");
            }

        }
    }
}


