package com.citi.performance.risk.optimize.optimize2;


/**
 * SIMD向量计算工具类（Java 21 Vector API）
 */
public class SimdVectorCalculator {
    // 适配CPU的向量物种（自动选择最大支持的向量长度）
//    private static final VectorSpecies<Long> LONG_SPECIES = LongVector.SPECIES_PREFERRED;
//    private static final VectorSpecies<Double> DOUBLE_SPECIES = DoubleVector.SPECIES_PREFERRED;
//
//    /**
//     * 向量计算用户ID最大值（SIMD加速）
//     */
//    public static long vectorMaxUserId(long[] userIdArray) {
//        int length = userIdArray.length;
//        long max = -1;
//        int i = 0;
//        // 向量循环：每次处理LONG_SPECIES.length()个long值
//        for (; i < length - LONG_SPECIES.length(); i += LONG_SPECIES.length()) {
//            LongVector v = LongVector.fromArray(LONG_SPECIES, userIdArray, i);
//            max = Math.max(max, v.reduceLanes(VectorOperators.MAX));
//        }
//        // 处理剩余数据（非向量部分）
//        for (; i < length; i++) {
//            max = Math.max(max, userIdArray[i]);
//        }
//        return max;
//    }
//
//    /**
//     * 向量计算交易金额总和（SIMD加速）
//     */
//    public static double vectorSumAmount(double[] amountArray) {
//        int length = amountArray.length;
//        double sum = 0.0;
//        int i = 0;
//        // 向量循环：每次处理DOUBLE_SPECIES.length()个double值
//        for (; i < length - DOUBLE_SPECIES.length(); i += DOUBLE_SPECIES.length()) {
//            DoubleVector v = DoubleVector.fromArray(DOUBLE_SPECIES, amountArray, i);
//            sum += v.reduceLanes(VectorOperators.ADD);
//        }
//        // 处理剩余数据
//        for (; i < length; i++) {
//            sum += amountArray[i];
//        }
//        return sum;
//    }
//
//    /**
//     * 8核分片+SIMD混合加速（规则内最终版）
//     */
//    public static long shardSimdMaxUserId(long[] userIdArray) {
//        return EightCoreDataSharder.RULE_INNER_POOL.submit(() ->
//            IntStream.range(0, EightCoreDataSharder.SHARD_COUNT)
//                    .parallel()
//                    .mapToLong(shardIndex -> {
//                        int start = shardIndex * EightCoreDataSharder.SHARD_SIZE;
//                        int end = (shardIndex == EightCoreDataSharder.SHARD_COUNT - 1)
//                                ? EightCoreDataSharder.DATA_SIZE : (shardIndex + 1) * EightCoreDataSharder.SHARD_SIZE;
//                        // 分片内使用SIMD计算
//                        long[] shardArray = Arrays.copyOfRange(userIdArray, start, end);
//                        return vectorMaxUserId(shardArray);
//                    })
//                    .max()
//                    .orElse(-1)
//        ).join();
//    }
//
//    public static double shardSimdSumAmount(double[] amountArray) {
//        return EightCoreDataSharder.RULE_INNER_POOL.submit(() ->
//            IntStream.range(0, EightCoreDataSharder.SHARD_COUNT)
//                    .parallel()
//                    .mapToDouble(shardIndex -> {
//                        int start = shardIndex * EightCoreDataSharder.SHARD_SIZE;
//                        int end = (shardIndex == EightCoreDataSharder.SHARD_COUNT - 1)
//                                ? EightCoreDataSharder.DATA_SIZE : (shardIndex + 1) * EightCoreDataSharder.SHARD_SIZE;
//                        double[] shardArray = Arrays.copyOfRange(amountArray, start, end);
//                        return vectorSumAmount(shardArray);
//                    })
//                    .sum()
//        ).join();
//    }
}