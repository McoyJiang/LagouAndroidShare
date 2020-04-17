import java.util.concurrent.locks.ReentrantLock;

public class LagouFairLockTest implements Runnable{
    private int sharedNumber = 0;
    //创建公平锁
    private static ReentrantLock lock=new ReentrantLock(true);
    public void run() {
        while(sharedNumber < 20){
            try{
                lock.lock();
                sharedNumber++;
                System.out.println(Thread.currentThread().getName() 
                        + " 获得锁, sharedNumber is " + sharedNumber);
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        LagouFairLockTest lft = new LagouFairLockTest();
        Thread th1 = new Thread(lft);
        Thread th2 = new Thread(lft);
        Thread th3 = new Thread(lft);
        th1.start();
        th2.start();
        th3.start();
    }
}
