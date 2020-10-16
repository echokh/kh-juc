package com.khstudy.juc.exercise;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockingQueue实现交替打印
 */
public class D11_OrderPrint_BlockingQueue {


    public static void main(String[] args) {
        BlockingQueue bq1 = new ArrayBlockingQueue(1);
        BlockingQueue bq2 = new ArrayBlockingQueue(1);

        char[] a1 = "ABCDEFG".toCharArray();
        char[] a2 = "1234567".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                System.out.print(a1[i]);
                try {
                    bq1.put("ok");
                    bq2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < a2.length; i++) {
                try {
                    bq1.take();
                    System.out.print(a2[i]);
                    bq2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
