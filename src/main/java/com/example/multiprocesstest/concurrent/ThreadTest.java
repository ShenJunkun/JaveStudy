package com.example.multiprocesstest.concurrent;

import java.util.concurrent.ExecutorService;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable");
    }
}

class MyCallable implements java.util.concurrent.Callable<String> {
    @Override
    public String call() throws Exception {
        return "MyCallable";
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        System.out.println("Task");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        MyCallable myCallable = new MyCallable();
        java.util.concurrent.FutureTask<String> futureTask = new java.util.concurrent.FutureTask<>(myCallable);
        Thread thread1 = new Thread(futureTask);
        thread1.start();
        try {
            System.out.println(futureTask.get());
        } catch (Exception e) {
            e.printStackTrace();

        }

        ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(100);
        long startTimestamp = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println("Time elapsed: " + (endTimestamp - startTimestamp) + "ms");

    }
}
