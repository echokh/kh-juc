package com.khstudy.juc.S02_sync;

/**
 * 锁和异常：synchronized在出现异常的情况下会默认释放锁，如果不想释放锁，catch异常继续运行
 */
public class T12_Synchronized_Exception {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public synchronized void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":::::" + i);
                    if (i == 5) {
//                        double a = 5 / 0;
                        try {
                            double a = 5 / 0;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(r, "t1");
        Thread t2 = new Thread(r, "t2");
        t1.start();
        t2.start();


    }
}
