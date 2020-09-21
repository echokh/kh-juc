package com.khstudy.juc.S04_newlock.d01_reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是可重入锁，synchronized也是可重入锁,两个都是可重入锁，
 * 有synchronized的地方都可以替换为reentrantLock.lock(),但是synchronized是自动解锁，而ReentrantLock 需要手动解锁
 * 当 线程 在synchronized发生异常会自动释放，如果不想释放，只要catch住异常就好
 * 测试synchronized可重入
 */
public class T01_Sycn_Reentrant {
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        T01_Sycn_Reentrant t = new T01_Sycn_Reentrant();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }

    //    synchronized void m1() {
//        for (int i = 0; i < 10; i++) {
//            try {
//                System.out.println("m1..." + i);
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (i == 2) m2();
//        }
//    }
    void m1() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("m1..." + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 2) m2();
        }
        lock.unlock();
    }

    //    synchronized void m2(){
//        System.out.println("m2....");
//    }
    void m2() {
        lock.lock();
        System.out.println("m2....");
        lock.unlock();
    }

}
