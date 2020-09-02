package com.khstudy.juc.test;

/**
 * 创建一个后台线程，不断地打印hello，之后睡眠100ms，在睡了2s之后，默认主线程应该中断后台线程
 * 此线程在打印出interrupted之后跳出循环
 */
public class IntSleep {
    static class myThread extends Thread{
        @Override
        public void run() {
            while (true)
            {
                System.out.println("hello");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread t=new myThread();
        t.start();
        try {
            Thread.sleep(2000);
            t.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
