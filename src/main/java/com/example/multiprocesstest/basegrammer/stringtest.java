package com.example.multiprocesstest.basegrammer;

public class stringtest {

    public static void testStringBuffer() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("hello");
        stringBuffer.append(" ");
        stringBuffer.append("world");
        System.out.println(stringBuffer.toString());
        String s;
        Object o;
    }

    public static void testStringBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello");
        stringBuilder.append(" ");
        stringBuilder.append("world");
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) {
        testStringBuffer();
        testStringBuilder();
    }
}
