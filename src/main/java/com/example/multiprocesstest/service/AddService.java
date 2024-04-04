package com.example.multiprocesstest.service;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    private static Integer i = 10;

    // 在需要满足线程安全的情况下，需要加上synchronized, 否则的话，使用jmeter进行并发测试的时候，会出现问题
    public synchronized boolean add(int addI) {
        AddService.i += addI;
        System.out.println("现在的数字是：" + AddService.i);
        return true;
    }

    public void printI() {
        System.out.println("打印数字：" + AddService.i);
    }
}
