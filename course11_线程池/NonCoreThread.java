package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Danny 姜.
 */
public class NonCoreThread {

    public static void main(String[] args) throws InterruptedException{
        // 核心线程为2、最大线程数为10、等待队列长度为2
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时6秒
                        Thread.sleep(5000);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            System.out.println("此时等待队列中有 " + threadPool.getQueue().size() + " 个元素");
        }
        threadPool.shutdown();
    }
}
