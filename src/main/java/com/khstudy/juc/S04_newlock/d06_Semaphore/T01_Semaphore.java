package com.khstudy.juc.S04_newlock.d06_Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 信号灯，获取到信号灯之后才能继续运行，否则阻塞住
 * 主要作用是限流，默认是非公平锁
 */
public class T01_Semaphore {
    public static void main(String[] args) {
        //默认非公平锁
//        Semaphore semaphore = new Semaphore(2);
        Semaphore s = new Semaphore(1, true);
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T1 start....");
                Thread.sleep(1000);
                System.out.println("T1 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 start...");
                Thread.sleep(1000);
                System.out.println("T2 end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                s.release();
            }
        }).start();

    }
}
