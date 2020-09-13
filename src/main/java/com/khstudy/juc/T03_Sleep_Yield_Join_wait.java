package com.khstudy.juc;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

public class T03_Sleep_Yield_Join_wait {
    public static void main(String[] args) throws IOException {
        //sleep
//        testSleep();
        //yield
//        testYield();
        //join
//        testJoin();
        //wait
        testWait();
    }

    private static void testWait() throws IOException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
//            try {
//                synchronized (lock)
//                {
//                    Thread.currentThread().wait();
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            for (int i = 0; i < 1010; i++) {
                System.out.println("A" + i);
                while (i == 5) {
                    try {
                      LockSupport.park();
//                        synchronized (lock) {//因为这个锁加在了object上，并没有加在当前线程上所以会报错IllegalMonitorStateException
                        //使用LockSupport.lock()方法就不会报错
                            Thread.currentThread().wait();
//                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("B" + i);
            }
        }).start();
        //阻塞不能避免报错
//        System.in.read();
    }

    private static void testJoin() {
//        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                synchronized (T03_Sleep_Yield_Join_wait.class) {
                    System.out.println("A" + i);
                }
            }
        });
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    synchronized (T03_Sleep_Yield_Join_wait.class) {
                        System.out.println("B" + i);
                        if (i == 5) {
                            t1.join();
                        }
                    }
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
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("A" + i);
                    if (i == 10) Thread.yield();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("B" + i);
                }
            }

        }).start();
    }

}
