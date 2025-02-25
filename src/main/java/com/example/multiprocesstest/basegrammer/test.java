package com.example.multiprocesstest.basegrammer;

public class test {
    public static void main(String[] args) {
        int i = 20;
        Integer integer = Integer.valueOf(i);

        Integer integer1 = 20;
        System.out.println(integer == integer1);

        i = 200;
        integer = Integer.valueOf(i);

        integer1 = 200;
        System.out.println(integer == integer1);
        System.out.println(integer.equals(integer1));
    }
}
