package racing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class Car implements Runnable {

    private static AtomicInteger CARS_COUNT;

    static {
        CARS_COUNT = new AtomicInteger(0);
    }

    private Race race;
    private int speed;
    private String name;
    private CountDownLatch toStartCount;
    private CyclicBarrier startBarrier;
    private CountDownLatch toFinishCount;
    private AtomicInteger finalist;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch toStartCount, CyclicBarrier startBarrier, CountDownLatch toFinishCount, AtomicInteger finalist) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + CARS_COUNT.getAndAdd(1);
        this.toStartCount = toStartCount;
        this.startBarrier = startBarrier;
        this.toFinishCount = toFinishCount;
        this.finalist = finalist;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            toStartCount.countDown();
            startBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        if (finalist.incrementAndGet() == 1) {
            System.out.println("Участник #" + this.name + " победитель!");
        }

        toFinishCount.countDown();
    }
}
