package com.citi.performance.risk;

import java.util.Arrays;
import java.util.concurrent.*;

/**
 * 主执行类：并行执行500个规则，统计耗时
 */
public class RuleExecutionMain {
    // 获取CPU核心数（服务器级通常为16/32核）
    private static final int CPU_CORES = Runtime.getRuntime().availableProcessors();
    // 规则数量
    private static final int num = 500 / 4;
    // 固定线程池：CPU密集型任务，线程数=CPU核心数（避免上下文切换）
    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(
            CPU_CORES,
            CPU_CORES,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(num), // 容量500，匹配规则数
            new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略：主线程兜底执行
    );

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 初始化内存数据（静态代码块已执行）
        System.out.printf("CPU核心数：%d%n", CPU_CORES);


        // 2. 生成500个规则的Future任务（异步执行）
        long start = System.currentTimeMillis();
        CompletableFuture<?>[] futures = new CompletableFuture[num];
        for (int i = 0; i < num; i++) {
            int ruleId = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                RuleExecutor rule = generateRule(ruleId);
                return rule.execute();
            }, EXECUTOR);
        }

        // 3. 等待所有规则执行完成，收集结果
        CompletableFuture.allOf(futures).join();
        long totalTime = System.currentTimeMillis() - start;

        // 4. 输出结果
        System.out.printf(num + "个规则执行完成，总耗时：%dms%n", totalTime);
        if (totalTime < 1000) {
            System.out.println("✅ 满足1秒内返回结果的要求！");
        } else {
            System.out.println("❌ 未满足1秒要求，需进一步优化！");
        }

        // 5. 关闭线程池
        EXECUTOR.shutdown();
    }

    /**
     * 生成单个规则（替代RuleExecutor.generate500Rules，更灵活）
     */
    private static RuleExecutor generateRule(int ruleId) {
        if (ruleId % 2 == 0) {
            // 偶数规则：用户ID最大值
            return () -> {
                long max = Arrays.stream(MemoryDataStore.userIdArray).max().getAsLong();
                return String.format("规则%d-用户ID最大值：%d", ruleId, max);
            };
        } else {
            // 奇数规则：交易金额总和
            return () -> {
                double sum = Arrays.stream(MemoryDataStore.amountArray).sum();
                return String.format("规则%d-交易金额总和：%.2f", ruleId, sum);
            };
        }
    }
}