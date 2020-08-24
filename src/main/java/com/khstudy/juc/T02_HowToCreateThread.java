package com.khstudy.juc;

public class T02_HowToCreateThread {
    private static class T1 extends Thread{
        @Override
        public void run() {
            System.out.println("through extends");
        }
    }

    private static class T2 implements Runnable{

        @Override
        public void run() {
            System.out.println("through runnable");
        }
    }

    public static void main(String[] args) {
        new T1().start();
        new Thread(new T2()).start();

        new Thread(()->{
            System.out.println("through Lambda");
        }).start();
    }

}
