package leg;


import java.util.concurrent.CompletableFuture;

public class LegExample1 implements Runnable {

    private final String name;
    private static Object monitor = new Object();

    public LegExample1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            synchronized (monitor) {
                while (true) {
                    System.out.println(name);
                    monitor.notifyAll();
                    monitor.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new LegExample1("left")),
                CompletableFuture.runAsync(new LegExample1("right"))
        ).join();
    }
}