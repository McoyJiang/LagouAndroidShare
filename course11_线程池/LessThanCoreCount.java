package material.danny_jiang.com.bmwtest.course11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Danny 姜
 *
 * 当前线程池中运行的线程数量还没有达到 corePoolSize 大小时，
 * 线程池会创建一个新线程执行提交的任务，
 * 无论之前创建的线程是否处于空闲状态。
 */
public class LessThanCoreCount {

    public static void main(String[] args) throws InterruptedException{
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            final int taskId = i;

            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程：" + Thread.currentThread().getName()
                                + " 正在执行 task: " + taskId);
                        // 任务耗时100毫秒
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {
                    }
                }
            });
            // 延迟2s向线程池中提交任务
            Thread.sleep(2000);
        }
        fixedThreadPool.shutdown();
    }
}
