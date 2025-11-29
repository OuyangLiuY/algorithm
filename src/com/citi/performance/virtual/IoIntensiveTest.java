package com.citi.performance.virtual;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IoIntensiveTest {

    /**
     * [传统线程池(IO密集)] 执行10000个任务，耗时：10120毫秒
     * [虚拟线程(IO密集)] 执行10000个任务，耗时：244毫秒
     * @param args
     */
    public static void main(String[] args) {
        // 测试参数：10000个IO任务，每个任务阻塞100毫秒
        int taskCount = 10000;
        long ioSleepMs = 100;

        // ========== 传统线程池测试 ==========
        // IO密集型场景，传统线程池设100个线程（避免创建过多平台线程导致OOM）
        ExecutorService threadPool = Executors.newFixedThreadPool(100);
        PerformanceTestUtil.testPerformance(
            "传统线程池(IO密集)",
            taskCount,
            threadPool,
            () -> PerformanceTestUtil.simulateIoTask(ioSleepMs)
        );

        // ========== 虚拟线程测试 ==========
        // 虚拟线程执行器：每个任务创建一个虚拟线程
        ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        PerformanceTestUtil.testPerformance(
            "虚拟线程(IO密集)",
            taskCount,
            virtualThreadExecutor,
            () -> PerformanceTestUtil.simulateIoTask(ioSleepMs)
        );
    }
}