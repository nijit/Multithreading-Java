package org.example;

public class DeadLock {
    public static void main(String[] args) {
        System.out.println("main started.");
        String lock1 = "one";
        String lock2 = "two";
        Thread t1 = new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2){
                    System.out.println("t1 lock acquired");
                }
            }
        });

        Thread t2 = new Thread(()->{
            synchronized (lock2){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){
                    System.out.println("t2 lock acquired");
                }
            }
        });
        t1.start(); t2.start();


        // Deadlock detection loop
        while (true) {
            if (t1.getState() == Thread.State.BLOCKED && t2.getState() == Thread.State.BLOCKED) {
                System.out.println("Deadlock started");
                break;
            }
        }

        System.out.println("main end");
    }
}
