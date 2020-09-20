package com.khstudy.juc.S01_hello_thread;

public class T04_ThreadState {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
