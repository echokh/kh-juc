package com.khstudy.juc.S04_newlock.d08_LockSupport;

import javax.swing.plaf.TableHeaderUI;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T01_testLockSupport {
    public static void main(String[] args) {
        new Thread(() -> {
            LockSupport.park();
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LockSupport.unpark(Thread.currentThread());
        }).start();
        new Thread(() -> {
            LockSupport.unpark(Thread.currentThread());

            for (int i = 0; i < 10; i++) {
                System.out.println("B" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LockSupport.park();
        }).start();
    }
}
