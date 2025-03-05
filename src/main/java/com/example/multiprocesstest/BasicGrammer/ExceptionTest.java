package com.example.multiprocesstest.BasicGrammer;

import java.io.IOException;
import java.sql.SQLException;

public class ExceptionTest {
    public static void main(String[] args) {

        try {
            if (Math.random() > 0.5)
                throw new IOException("IO Exception");
            else {
                throw new SQLException("SQL Exception");
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("This block will always execute.");
        }
        System.out.println("final value" + exceptTest());
    }

    static String exceptTest() {
        try {
            if (Math.random() > 0.5) {
                System.out.println("before throw");

                throw new IOException("IO Exception");

            } else {
                throw new SQLException("SQL Exception");
            }
//            System.out.println("after try");


        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return "ioe";
//            System.out.println("after ioe");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return "sqle";
//            System.out.println("after ioe");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("This block will always execute.");
            return "finally";
//            System.out.println("after finally");
        }

    }
}
