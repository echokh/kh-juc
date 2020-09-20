package com.khstudy.juc.S01_hello_thread;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class T03_1_InterruptedDemo {
    volatile static AtomicInteger i = new AtomicInteger();
    public static void main(String[] args) {

        Thread t=new Thread(() -> {
            while (true) {
//                try {
//                    Thread.currentThread().sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(i.incrementAndGet());
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        System.out.println(t.isInterrupted());//false
        //将标志位设为true，但并不会打断线程的进行
        //如果此时线程因为sleep、join、wait处于阻塞状态，则会抛出InterruptedException
        t.interrupt();
        System.out.println(t.isInterrupted());//true
        Thread.interrupted();
        System.out.println(t.isInterrupted());//false
//        try {
//            t.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(t.isInterrupted());

//        while (i.intValue()==10)
//        {
//
//        }
    }
}
