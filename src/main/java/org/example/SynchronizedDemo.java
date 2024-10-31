package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizedDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter cnt = new Counter();
        Thread t1 = new Thread(()->{
            for(int i=0; i<1000; i++){
                cnt.increment();
            }
        });
        Thread t2 = new Thread(()-> {
            for(int i=0; i<1000; i++){
                cnt.decrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(cnt.getCnt());
    }
}
