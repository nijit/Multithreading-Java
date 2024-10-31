package org.example;

public class ThreadStateDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(1);
                System.out.println("I am Runnable now.");
                for (int i =0; i<7000; i++);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        while (true){
            Thread.State state = t1.getState();
            System.out.println(state);
            if(state == Thread.State.TERMINATED) break;
        }
    }
}

/*
In this code, the `BLOCKED` state appears because the main thread is frequently checking the state of `t1` in a tight loop. The tight loop may be attempting to access the state of `t1` concurrently as `t1` tries to execute its own code, particularly after the sleep period ends and `t1` is ready to proceed.

        Here’s a breakdown of why this `BLOCKED` state occurs:

        1. **Main Thread Polling**: The main thread continuously checks `t1`'s state with `t1.getState()` inside an infinite `while` loop. Since there is no pause or delay in the loop, the main thread polls very frequently.

        2. **Thread `t1` Execution**:
        - When `t1` starts, it goes into the `RUNNABLE` state, then immediately transitions to `TIMED_WAITING` due to the `Thread.sleep(1);`.
        - Once the sleep period ends, `t1` transitions back to `RUNNABLE` as it attempts to resume execution.

        3. **Blocked State Occurrence**:
        - After `t1` wakes up from `TIMED_WAITING`, the main thread’s frequent access to `t1.getState()` might briefly block `t1` as `t1` tries to run its own code.
        - This happens because `getState()` and the thread’s code may use the same underlying system resources, resulting in a short-lived contention where the main thread blocks `t1` momentarily, placing `t1` in the `BLOCKED` state until it can continue.

        ### Solution
        To avoid this issue, add a small delay in the main thread’s polling loop, like `Thread.sleep(10);` or `Thread.yield();`, to reduce frequent access to `t1.getState()`. This allows `t1` more breathing room to run smoothly without interference from the main thread. Here’s how you can modify it:

        ```java
        while (true) {
        Thread.State state = t1.getState();
        System.out.println(state);
        if (state == Thread.State.TERMINATED) break;
        try {
        Thread.sleep(10); // Adding sleep to avoid rapid polling
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        }
        ```

        This will reduce the likelihood of seeing `BLOCKED` and give `t1` a clearer execution path.
*/
