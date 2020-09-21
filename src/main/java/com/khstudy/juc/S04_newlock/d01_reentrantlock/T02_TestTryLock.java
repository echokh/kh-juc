package com.khstudy.juc.S04_newlock.d01_reentrantlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 比synchronized更强大的地方在于可以用tryLock()尝试锁
 * 不管锁定与否程序都会继续执行，并不会阻塞
 */
public class T02_TestTryLock {
    Lock lock = new ReentrantLock();

    void m1() {

        try {
            lock.lock();
            for (int i = 0; i <10; i++) {
                System.out.println(i);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 不管tryLock()是否能获得锁程序都会继续进行
     */
    void m2() {
        boolean b = lock.tryLock();
        System.out.println("m2....locked" + b);
        if (b) lock.unlock();
    }

    /**
     * tryLock(time) 指定time时间内尝试获取锁，
     */
    void m3() {
        try {
            boolean locked = lock.tryLock(7, TimeUnit.SECONDS);
            System.out.println("m3....."+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        T02_TestTryLock t = new T02_TestTryLock();
        new Thread(t::m1).start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(t::m2).start();
        new Thread(t::m3).start();
    }
}
