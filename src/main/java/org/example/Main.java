package org.example;

public class Main {

    public static void main(String[] args) {
        System.out.println("main-> start");
        createAndStartT1();
        createAndStartT2();
        System.out.println("main-> end");
    }
    public static void createAndStartT1(){
        Thread t1 = new Thread(() -> {
            for(int i=0; i<5; i++){
                System.out.println("t1 thread = "+Thread.currentThread() +" " + Thread.currentThread().getName() +" "+i);
            }
        }
        );
        t1.setName("thread one ");
        t1.setDaemon(false);
        t1.start();
    }
    public static void createAndStartT2(){
        Thread t2 = new Thread(() -> {
            for(int i=0; i<5; i++){
                System.out.println("t2 thread = "+Thread.currentThread() +" " + Thread.currentThread().getName() +" "+i);
            }
        }, "thread two"
        );
        t2.setDaemon(false);
        t2.start();
    }

}