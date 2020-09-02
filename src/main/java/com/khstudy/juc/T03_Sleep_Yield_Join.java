package com.khstudy.juc;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        //sleep
        testSleep();
        //yield
//        testYield();
        //join
//        testJoin();
    }

    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A" + i);
            }
        });
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("B" + i);
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        t1.start();
    }


    private static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.currentThread().sleep(500);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                if (i == 10) Thread.yield();
            }
        });
    }
}
