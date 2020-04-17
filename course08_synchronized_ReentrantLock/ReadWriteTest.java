package material.danny_jiang.com.bmwtest;

import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * Created by Danny 姜 on 2020-03-27.
 */
public class ReadWriteTest {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static String number = "0";

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new Reader(),"读线程 1");
        Thread t2 = new Thread(new Reader(),"读线程 2");
        Thread t3 = new Thread(new Writer(),"写线程");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
    }

    static class Reader implements Runnable {
        public void run() {
            for(int i = 0; i<= 10; i ++) {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + " ---> Number is " + number);
                lock.readLock().unlock();
            }
        }
    }
    static class Writer implements Runnable {
        public void run() {
            for(int i = 1; i<= 7; i+=2) {
                try {
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName()  + " 正在写入 " + i);
                    number = number.concat("" + i);
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }
}