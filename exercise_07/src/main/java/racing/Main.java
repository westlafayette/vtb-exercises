package racing;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        final CountDownLatch toStartCount = new CountDownLatch(CARS_COUNT);
        final CountDownLatch toFinishCount = new CountDownLatch(CARS_COUNT);
        final CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT + 1);
        final AtomicInteger finalist = new AtomicInteger(0);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(new Semaphore(CARS_COUNT / 2)), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), toStartCount, startBarrier, toFinishCount, finalist);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            // START
            toStartCount.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            startBarrier.await();

            // FINISH
            toFinishCount.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

    }
}
