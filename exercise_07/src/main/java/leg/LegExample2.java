package leg;


import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LegExample2 implements Runnable {

    private final String name;
    private static final Object monitor = new Object();
    private static String currentName = null;
    private static Map<String, String> map = Map.of("left", "right",
            "right", "left");

    public LegExample2(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                if (currentName == null || currentName.equals(name)) {
                    System.out.println(name);
                    currentName = map.get(name);
                }
            }
        }
    }

    public static void main(String[] args) {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(new LegExample2("left")),
                CompletableFuture.runAsync(new LegExample2("right"))
        ).join();
    }
}