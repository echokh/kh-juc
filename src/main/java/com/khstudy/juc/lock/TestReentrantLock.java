package com.khstudy.juc.lock;

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
