import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore smp = new Semaphore(Math.toIntExact(MainClass.CARS_COUNT/2));

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
                c.raceStages--;
                if (c.raceStages == 0) {
                    MainClass.winner.offer(c.getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                smp.release();
                System.out.println(c.getName() + " закончил этап: " + description);
                MainClass.raceFinish.countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
