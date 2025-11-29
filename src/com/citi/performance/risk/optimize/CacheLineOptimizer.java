package com.citi.performance.risk.optimize;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * CPU缓存行优化工具类（64字节缓存行对齐）
 */
public class CacheLineOptimizer {
    // CPU缓存行大小（64字节）
    public static final int CACHE_LINE_SIZE = 64;
    private static final Unsafe UNSAFE;

    static {
        // 初始化Unsafe（Java 21需开启--add-exports java.base/sun.misc=ALL-UNNAMED）
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分配缓存行对齐的long数组（用于存储用户ID）
     */
    public static long[] allocateAlignedLongArray(int length) {
        // 计算所需内存：length * 8字节（long） + 64字节（缓存行对齐）
        long size = (long) length * 8 + CACHE_LINE_SIZE;
        long address = UNSAFE.allocateMemory(size);
        // 对齐到64字节
        long alignedAddress = (address + CACHE_LINE_SIZE - 1) & ~(CACHE_LINE_SIZE - 1);
        // 创建数组并关联对齐后的内存
        long[] array = new long[length];
        UNSAFE.copyMemory(null, alignedAddress, array, UNSAFE.arrayBaseOffset(long[].class), length * 8);
        return array;
    }

    /**
     * 分配缓存行对齐的double数组（用于存储交易金额）
     */
    public static double[] allocateAlignedDoubleArray(int length) {
        long size = (long) length * 8 + CACHE_LINE_SIZE;
        long address = UNSAFE.allocateMemory(size);
        long alignedAddress = (address + CACHE_LINE_SIZE - 1) & ~(CACHE_LINE_SIZE - 1);
        double[] array = new double[length];
        UNSAFE.copyMemory(null, alignedAddress, array, UNSAFE.arrayBaseOffset(double[].class), length * 8);
        return array;
    }

    /**
     * 缓存行填充的统计结果类（避免伪共享）
     */
    public static class ShardResult {
        // 缓存行填充：占用64字节，避免与其他线程的变量共享缓存行
        private long p1, p2, p3, p4, p5, p6, p7; // 7*8=56字节
        private long maxUserId; // 8字节，总计64字节
        private double sumAmount; // 8字节，需再填充56字节（若单独使用）

        private long p8, p9, p10, p11, p12, p13, p14; // 再填充56字节

        // getter/setter
        public long getMaxUserId() { return maxUserId; }
        public void setMaxUserId(long maxUserId) { this.maxUserId = maxUserId; }
        public double getSumAmount() { return sumAmount; }
        public void setSumAmount(double sumAmount) { this.sumAmount = sumAmount; }
    }
}