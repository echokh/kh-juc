package com.khstudy.juc.testvolatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过前面的例子可以看到volatile可以保证可见性，并且禁止指令重排
 * 但是没有办法保证原子性，请证明
 */
public class T02_Volatile_Atomic {

    private  volatile static int count=0;
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 1000; j++) {
                    count++;
                }
            }
        };

        for (int j = 0; j < 10; j++) {
            Thread thread = new Thread(r);
            thread.start();
        }
        Thread.sleep(1000);
        System.out.println(count);
    }
}
