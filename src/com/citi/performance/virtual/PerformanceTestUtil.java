package com.citi.performance.virtual;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * 性能测试工具类
 */
public class PerformanceTestUtil {
    /**
     * 统计任务执行耗时
     * @param taskName 任务名称
     * @param taskCount 任务总数
     * @param executor 执行器（线程池/虚拟线程执行器）
     * @param task 待执行的任务
     */
    public static void testPerformance(String taskName, int taskCount, ExecutorService executor, Runnable task) {
        Instant start = Instant.now();
        CountDownLatch latch = new CountDownLatch(taskCount);

        // 提交任务
        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    task.run();
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有任务完成
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

        // 计算耗时
        Duration duration = Duration.between(start, Instant.now());
        System.out.printf("[%s] 执行%d个任务，耗时：%d毫秒%n", taskName, taskCount, duration.toMillis());

        // 关闭执行器
        executor.shutdown();
    }

    /**
     * 模拟IO密集型任务：使用Thread.sleep模拟网络/磁盘IO阻塞
     * @param sleepMs IO阻塞时间
     */
    public static void simulateIoTask(long sleepMs) {
        try {
            Thread.sleep(sleepMs); // IO阻塞，虚拟线程会在此处卸载
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 模拟CPU密集型任务：计算大整数累加，持续占用CPU
     * @param loopCount 循环次数
     */
    public static void simulateCpuTask(long loopCount) {
        long sum = 0;
        for (long i = 0; i < loopCount; i++) {
            sum += i;
        }
        // 避免JIT优化掉无意义计算
        if (sum < 0) System.out.println(sum);
    }
}