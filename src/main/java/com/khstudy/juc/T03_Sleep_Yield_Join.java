package com.khstudy.juc;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        //sleep
        testSleep();
        //yield
        testYield();
        //join
        testJoin();
    }

    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println("A" + i);
            }
        });
        t1.start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                if (i == 100) {
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    private static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i == 10) Thread.yield();
            }
        });
    }
}
