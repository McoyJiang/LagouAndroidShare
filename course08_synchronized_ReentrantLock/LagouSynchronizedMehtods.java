public class LagouSynchronizedMehtods{
    private Object lock = new Object();

    public static void main(String[] args){
        LagouSynchronizedMehtods l1 = new LagouSynchronizedMehtods();

        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run(){
                l1.printLog();
            }
        });

        Thread t2 = new Thread(new Runnable(){
            @Override
            public void run(){
                l1.printLog();
            }
        });

        t1.start();
        t2.start();
    }

    public  void printLog(){
        synchronized(lock) {
            for(int i = 0; i < 5; i ++) {
                System.out.println(Thread.currentThread().getName() + " is printing " + i);
            }
        }
    }
}
