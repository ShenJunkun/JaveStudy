package com.example.multiprocesstest.BasicGrammer;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal num = new BigDecimal(0.1);
        System.out.println(num.toString());
        BigDecimal num2 = new BigDecimal(0.2);

        System.out.println(num2.add(num));
    }
}
