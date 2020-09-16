package com.khstudy.juc.sync;

import java.util.concurrent.TimeUnit;

/**
 * 锁优化：有两种，锁细化和锁粗化
 * 锁细化：如果需要进行同步的代码比较少，只需要同步需要的代码即可，不用把整个方法都同步，提高效率
 * 锁粗化：
 * 在某个处理逻辑中需要进行同步的代码零散的分布而且数量较多，此时就需要进行锁粗化，
 * 因为每次加锁，释放锁都要消耗资源，降低效率，所以将多个小锁合并成一个大锁提高效率
 */
public class T13_Synchronized_Optimization {
    int count = 2;

    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于整个业务处理过程，只有count需要进行同步，锁细化
        count++;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void m2(){
        synchronized (this){
            count++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            count++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            count++;
        }
    }
}
