package com.khstudy.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * aqs源码阅读
 */
public class TestAbstractQueuedSynchronizer {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }
}
