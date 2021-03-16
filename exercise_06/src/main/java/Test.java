import java.util.Arrays;

public class Test {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {

        simpleRun();

        runWithTreads();
    }

    public static void simpleRun() {
        float[] arr = new float[SIZE];

        Arrays.fill(arr, 1);

        long start = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }

        System.out.println("Simple run took " + (System.currentTimeMillis() - start));
    }

    public static void runWithTreads() {
        float[] arr = new float[SIZE];

        Arrays.fill(arr, 1);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
        });

        Thread thread2 = new Thread(() -> {
            for (int i = HALF; i < SIZE; i++)
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                        Math.cos(0.4f + i / 2));
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Two threads run took " + (System.currentTimeMillis() - start));
    }
}
