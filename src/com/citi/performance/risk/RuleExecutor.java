package com.citi.performance.risk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 规则执行接口：定义规则的计算逻辑
 */
@FunctionalInterface
public interface RuleExecutor {
    /**
     * 执行规则并返回结果
     * @return 规则计算结果（Object类型，可根据实际返回Long/Double等）
     */
    Object execute();

    /**
     * 生成500个测试规则（模拟简单计算）
     * @return 500个规则的列表
     */
    static ExecutorService generate500Rules() {
        // 规则类型：分为两类，交替生成（共500个）
        // 类型1：统计用户ID的最大值/最小值/总和
        // 类型2：统计交易金额的总和/平均值/最大值
        ExecutorService rulePool = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 250; i++) {
            int ruleId = i;
            RuleExecutor rule;
            if (i % 2 == 0) {
                // 偶数规则：处理用户ID
                rule = () -> {
                    long max = -1;
                    for (long userId : MemoryDataStore.userIdArray) {
                        if (userId > max) max = userId;
                    }
                    return String.format("规则%d-用户ID最大值：%d", ruleId, max);
                };
            } else {
                // 奇数规则：处理交易金额
                rule = () -> {
                    double sum = 0;
                    for (double amount : MemoryDataStore.amountArray) {
                        sum += amount;
                    }
                    return String.format("规则%d-交易金额总和：%.2f", ruleId, sum);
                };
            }
            rulePool.submit(() -> rule.execute());
        }
        return rulePool;
    }
}