import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    static CountDownLatch raceFinish = new CountDownLatch(CARS_COUNT);
    static CountDownLatch raceReady = new CountDownLatch(1);
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);
    static ArrayBlockingQueue<String> winner = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(CARS_COUNT);
        try {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
            }
            for (Car car : cars) {
                ex.execute(car);
            }
            raceReady.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            ex.shutdown();
            while (!ex.isTerminated()) {
                raceFinish.await();
            }
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
            System.out.println("Победитель: " + winner);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
