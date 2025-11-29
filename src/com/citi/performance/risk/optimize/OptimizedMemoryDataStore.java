package com.citi.performance.risk.optimize;

/**
 * 优化后的内存数据层（缓存行对齐）
 */
public class OptimizedMemoryDataStore {
    public static final int DATA_SIZE = 10_000_000;
    public static long[] userIdArray;
    public static double[] amountArray;

    static {
        long start = System.currentTimeMillis();
        // 使用缓存行对齐的数组
        userIdArray = CacheLineOptimizer.allocateAlignedLongArray(DATA_SIZE);
        amountArray = CacheLineOptimizer.allocateAlignedDoubleArray(DATA_SIZE);

        java.util.Random random = new java.util.Random(42);
        for (int i = 0; i < DATA_SIZE; i++) {
            userIdArray[i] = random.nextLong(1_000_000_000L);
            amountArray[i] = random.nextDouble() * 10000;
        }
        System.out.printf("对齐后数据加载完成，耗时：%dms%n", System.currentTimeMillis() - start);
    }
}