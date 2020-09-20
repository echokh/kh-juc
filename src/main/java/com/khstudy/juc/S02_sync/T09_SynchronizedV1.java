package com.khstudy.juc.S02_sync;

public class T09_SynchronizedV1 implements Runnable {
    private volatile int count = 10;

    public static void main(String[] args) {
        T09_SynchronizedV1 t = new T09_SynchronizedV1();
        for (int i = 0; i < 5; i++) {
            new Thread(t, "Thread" + i).start();
        }
    }

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "--count" + count);
    }
}
