package com.khstudy.juc.S04_newlock.d02_countDownLunch;

import java.util.concurrent.CountDownLatch;

/**
 * 倒数门闩，首先定义门闩的额度，等额度用完了门就开了，线程也就继续运行下去了，
 * 门闩的加减由实际业务决定，每个线程根据业务需要countDown，当数量减为0，则await可以继续执行否则一直阻塞
 */
public class T01_TestCountDownLunch {

    static volatile int result = 0;

    public static void main(String[] args) throws InterruptedException {
//        usingjoin();
        usingCountDownLunch();
    }


    /**
     * 使用CountDownLunch()方法
     */
    private static void usingCountDownLunch() {
        CountDownLatch latch = new CountDownLatch(101);

        Thread[] ths = new Thread[100];
        for (int i = 0; i < 100; i++) {
            ths[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    result += 1;
                }
                latch.countDown();

            });
            ths[i].start();
            System.out.println("loop end"+i);
        }
        try {
            latch.await();
            System.out.println("lunch end "+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        try {
//            latch.await();
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 100个线程，每个线程求1-10000的和
     */
    private static void usingjoin() throws InterruptedException {

        Thread[] ths = new Thread[100];
        for (int i = 0; i < 100; i++) {
            ths[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        result = result + 1;
                    }
                }
            });
            //线程必须先启动才能join
            ths[i].start();
            ths[i].join();
        }
        System.out.println(result);
    }
}
