package test;

import java.util.*;



class Father {
    public static void main(String[] args) {
        
    }

    public void test() throws RuntimeException, Exception {
        System.out.println("father");
    }

    static class Child extends Father {
        @Override
        public void test() throws Exception {
            System.out.println("child");
        }
    }
}