package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Danny 姜.
 */
public class FixedThreadPoolOOM {

    public static void main(String[] args) throws InterruptedException{
        ThreadPoolExecutor fixedThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        for (int i = 1; i <= 1000000; i++) {
            final int taskId = i;
            System.out.println("execute tast:" + taskId);

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时10秒
                        Thread.sleep(10000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
        }
        fixedThreadPool.shutdownNow();
    }
}
