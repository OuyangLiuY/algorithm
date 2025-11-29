package com.citi.performance.risk;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 内存数据层：用原始类型数组存储千万级数据，避免对象开销
 */
public class MemoryDataStore {
    // 1千万条数据的原始类型数组（核心优化点）
    public static final int DATA_SIZE = 10_000_000;
    public static long[] userIdArray;       // 用户ID（8字节/条，总计80MB）
    public static double[] amountArray;    // 交易金额（8字节/条，总计80MB）

    // 静态初始化：加载1千万条随机数据到内存
    static {
        long start = System.currentTimeMillis();
        Random random = new Random(42); // 固定种子，确保可复现
        userIdArray = new long[DATA_SIZE];
        amountArray = new double[DATA_SIZE];
        for (int i = 0; i < DATA_SIZE; i++) {
            userIdArray[i] = random.nextLong(1_000_000_000L); // 随机用户ID
            amountArray[i] = random.nextDouble() * 10000;     // 随机交易金额（0~1万）
        }
        System.out.printf("数据加载完成，耗时：%dms，数据量：%d条%n",
                System.currentTimeMillis() - start, DATA_SIZE);
    }
}