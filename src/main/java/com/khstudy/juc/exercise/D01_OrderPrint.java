package com.khstudy.juc.exercise;

import java.util.concurrent.locks.ReentrantLock;

//要求用线程顺序打印A1B2C3.....Z26
public class D01_OrderPrint {
//    ReentrantLock lock = new ReentrantLock();

    volatile static boolean t2Start = false;

    static final Object o = new Object();
    static char[] s1 = "ABCDEFG".toCharArray();
    static char[] s2 = "1234567".toCharArray();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (o) {
                while (!t2Start) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : s2) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                for (char c : s1) {
                    System.out.print(c);
                    t2Start=true;
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }
}
