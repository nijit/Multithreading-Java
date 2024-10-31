package org.example;
public class InterruptExample {
    public static void main(String[] args) {
        // Creating a thread that will sleep for some time
        Thread sleepyThread = new Thread(() -> {
            try {
                System.out.println("Sleepy thread is going to sleep for 5 seconds...");
                Thread.sleep(5000);  // Thread sleeps for 5 seconds
                System.out.println("Sleepy thread woke up naturally.");
            } catch (InterruptedException e) {
                System.out.println("Sleepy thread was interrupted while sleeping!");
            }
        });

        // Start the sleepy thread
        sleepyThread.start();

        // Main thread waits for 2 seconds before interrupting sleepyThread
        try {
            Thread.sleep(2000);  // Main thread waits for 2 seconds - if you make it 6 seconds it cannot interrupt
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupting sleepyThread
        System.out.println("Main thread is interrupting sleepy thread...");
        sleepyThread.interrupt();
    }
}
