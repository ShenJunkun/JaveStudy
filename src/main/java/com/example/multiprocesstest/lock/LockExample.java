package com.example.multiprocesstest.lock;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

//代码测试结果
//Time taken by synchronized method: 44221 milliseconds
//Time taken by reentrantLock method: 44381 milliseconds
//Time taken by atomicLock method: 38879 milliseconds
//2000000
//1000000


public class LockExample {
    private static final int THREAD_NUM = 1000000;
    private static Long long_value = 0L;

    //悲观锁的调用方式
    // 使用synchronized
    public synchronized void testSynMethod() {
        LockExample.long_value++;
    }

    //使用ReentrantLock
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void addValueWithReentrantLock() {
        reentrantLock.lock();
        try {
            LockExample.long_value++;
        } finally {
            reentrantLock.unlock();
        }
    }

    //使用乐观锁
    private AtomicLong atomicLong = new AtomicLong();

    public void addValueWithAtomicLock() {
        atomicLong.incrementAndGet();
    }


    public static void main(String[] args) {
        LockExample lockExample = new LockExample();

        Thread[] threads = new Thread[THREAD_NUM];

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->lockExample.testSynMethod());
        }

        long startTime, endTime;

        //测试synchronized
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by synchronized method: " + (endTime - startTime) + " milliseconds");

        //测试reentrantlock
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->lockExample.addValueWithReentrantLock());
        }

        startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();
        System.out.println("Time taken by reentrantLock method: " + (endTime - startTime) + " milliseconds");

        //测试乐观锁
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(()->lockExample.addValueWithAtomicLock());
        }
        startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by atomicLock method: " + (endTime - startTime) + " milliseconds");

        System.out.println(LockExample.long_value);
        System.out.println(lockExample.atomicLong.get());
    }
}
