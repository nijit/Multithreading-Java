package org.example;

public class Counter {
    private int cnt;
    String lock = "OneLockForAll";

    //Two ways - you can use only one way.
    public synchronized void increment(){
        synchronized (this){ //synchronized will be converted to this internally
            cnt++;
        }
    }
    public synchronized static void example(){
        synchronized (Counter.class){
            //static method does not belong to the object but belongs to the class. So this won't work here.
            //so use Classname.class here. Internally compiler do like this.
        }
    }
    public synchronized void decrement(){
        synchronized(this) {
            //known as critical section
            cnt--;
        }
    }
    public int getCnt(){
        return cnt;
    }
}
