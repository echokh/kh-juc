package com.khstudy.juc.S04_newlock.d01_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1、lock()方法也是可以被interrupt的
 */
public class T03_LockInterruptibly {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
//        Thread t1 = new Thread(() -> {
//            try {
//                lock.lock();
//                System.out.println("t1 start....");
//                for (int i = 0; i < 10; i++) {
//                    System.out.println("1:::::" + i);
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        });
//        t1.start();

        Thread t2 = new Thread(() -> {
            try {
//                lock.lock();
                lock.lockInterruptibly();
                System.out.println("t2 start....");
                for (int i = 0; i < 10; i++) {
                    System.out.println("2:::::" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();

        Thread t3 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t3 start....");
                for (int i = 0; i < 10; i++) {
                    System.out.println("3:::::" + i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t3.start();
        //从输出结果来看
//        t1.interrupt();
//       Thread t2= new Thread(() -> {
//            try {
////                lock.lock();
//                lock.lockInterruptibly();
//
//                System.out.println(Thread.currentThread().isInterrupted());
//                Thread.currentThread().interrupt();
//                System.out.println(Thread.currentThread().isInterrupted());
//
//                System.out.println("t1 start....");
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        });
//        t2.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t2.interrupt();
    }
}
