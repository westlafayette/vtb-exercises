package question;

import org.openjdk.jol.vm.VM;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Question {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        /*
           Why a new thread can access a local variable from a main thread,
           seems like the main thread will lose the local variable from its stack.
        */

        for (int i = 0; i < 1; i++) {
            int local = 10;


            System.out.println("The memory address is " + VM.current().addressOf(local));

            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("The memory address is " + VM.current().addressOf(local));

                    System.out.println(local + " - Begin");

                    Thread.sleep(100);

                    System.out.println(local + " - End");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            System.out.println("local is lost: " + local);
        }

        executorService.shutdown();
    }
}
