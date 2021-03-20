package com.danny.thread_practice;

public class OneCountingTest {

    public static void main(String[] args) {
        OneCountingTest oct = new OneCountingTest();
        int count = oct.fastOneCount(137);
        System.out.println("count is " + count);
    }

    private int fastOneCount(int number) {
        printNumberAsBinary(number);
        int count = 0;
        while (number != 0) {
            count++;
            number = number & (number - 1);
        }
        return count;
    }

    private int oneCounting(int number) {
        printNumberAsBinary(number);
        int i = 1;
        int count = 0;
        while (i != 0) {
            if ((i & number) != 0) count++;
            i = i << 1;
        }
        return count;
    }

    private void printNumberAsBinary(int number) {
        StringBuilder s = new StringBuilder();
        while (number > 0){
            s.insert(0, (number % 2));
            number /= 2;
        }
        System.out.println(s);
    }
}
