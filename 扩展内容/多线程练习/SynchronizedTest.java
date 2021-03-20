package com.danny.thread_practice;

public class SynchronizedTest {

    private final String name;

    private final static byte[] lock = new byte[0];

    private SynchronizedTest(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程A并启动
        Thread threadA = new Thread(() -> new SynchronizedTest("A").startConditionLoop());
        threadA.start();

        // 延迟5毫秒
        Thread.sleep(500);

        // 创建线程B并启动
        Thread threadB = new Thread(() -> new SynchronizedTest("B").startConditionLoop());
        threadB.start();

        // 延迟5毫秒
        Thread.sleep(500);

        // 创建线程C并启动
        Thread threadC = new Thread(() -> new SynchronizedTest("C").startConditionLoop());
        threadC.start();
    }

    private void startConditionLoop() {
        try {
            System.out.println("startConditionLoop in Thread: " + Thread.currentThread().getId());
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("this is " + name + " running");
                    Thread.sleep(300);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
