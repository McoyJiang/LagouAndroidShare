package com.danny.thread_practice;

import java.util.concurrent.Semaphore;

/**
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * <p>
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * <p>
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * <p>
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。
 * <p>
 * 你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * <p>
 * 换句话说:
 * <p>
 * 如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢线程到达。
 * 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 */
public class H2OFactory {

    public static void main(String[] args) {
        H2OFactory h2OFactory = new H2OFactory();
        h2OFactory.produceH2O();
    }

    volatile int hNum = 0;
    Semaphore hSmp = new Semaphore(2);
    Semaphore oSmp = new Semaphore(0);

    private void produceH2O() {
        Thread oxThread = new Thread(() -> {
            while (true) {
                try {
                    hSmp.acquire();
                    System.out.println("produce oxygen");
                    Thread.sleep(500);
                    hNum++;
                    if (hNum == 2) {
                        oSmp.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread hyThread = new Thread(() -> {
            while (true) {
                try {
                    oSmp.acquire();
                    System.out.println("produce hydrogen");
                    Thread.sleep(500);
                    hNum = 0;
                    hSmp.release(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        oxThread.start();
        hyThread.start();
    }
}
