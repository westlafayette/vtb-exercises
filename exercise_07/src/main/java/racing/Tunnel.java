package racing;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore constraint;

    public Tunnel(Semaphore constraint) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.constraint = constraint;
    }

    @Override
    public void go(Car c) {
        try {
            constraint.acquire();
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            constraint.release();
        }
    }
}