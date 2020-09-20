package com.khstudy.juc.S04_newlock.d01_reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private static int i=0;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        i++;
        lock.unlock();
    }
}
