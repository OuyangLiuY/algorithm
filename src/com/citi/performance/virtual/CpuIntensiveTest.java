package com.citi.performance.virtual;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CpuIntensiveTest {
    /**
     * [传统线程池(CPU密集)] 执行1000个任务，耗时：14487毫秒
     * [虚拟线程(CPU密集)] 执行1000个任务，耗时：14209毫秒
     * @param args
     */
    public static void main(String[] args) {
        // 测试参数：1000个CPU任务，每个任务循环1亿次
        int taskCount = 1000;
        long loopCount = 100_000_000;

        int cor = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU核心数: " + cor);


        // ========== 传统线程池测试 ==========
        // CPU密集型场景，线程数设为CPU核心数（4），避免上下文切换
        ExecutorService threadPool = Executors.newFixedThreadPool(cor);
        PerformanceTestUtil.testPerformance(
            "传统线程池(CPU密集)",
            taskCount,
            threadPool,
            () -> PerformanceTestUtil.simulateCpuTask(loopCount)
        );

        // ========== 虚拟线程测试 ==========
        ExecutorService virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        PerformanceTestUtil.testPerformance(
            "虚拟线程(CPU密集)",
            taskCount,
            virtualThreadExecutor,
            () -> PerformanceTestUtil.simulateCpuTask(loopCount)
        );
    }
}