package com.khstudy.juc.S04_newlock.d01_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以是公平锁，默认是非公平锁
 */
public class T04_ReentrantLock_FairLock {
    //公平锁
    Lock fairLock=new ReentrantLock(true);
    //非公平锁--默认
    static Lock nonFairLock=new ReentrantLock();

    public static void main(String[] args) {
        nonFairLock.lock();
    }

}
