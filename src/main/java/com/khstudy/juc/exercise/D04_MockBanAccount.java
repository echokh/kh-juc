package com.khstudy.juc.exercise;

/**
 * 模拟银行账户
 * 有名称、余额
 * 给账户设置金额的时候一定要加锁，但是读的时候要不要加锁？
 * 同步方法和非同步方法是可以同时调用，就看业务是否允许脏读
 * 脏读：数据中间态。
 * 允许脏读，则可以不用加锁，毕竟加锁会影响效率。
 */
public class D04_MockBanAccount {
    String name;
    double balance;

    public static void main(String[] args) {
        D04_MockBanAccount a = new D04_MockBanAccount();
        new Thread(() -> a.setBalance("lisi", 100)).start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance());
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(String name, double balance) {
        this.name = name;
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }
}
