package algo;




import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SlidingWindowRateLimiter {
    private final int windowSize; // 窗口大小（毫秒）
    private final int granularity; // 粒度（毫秒）
    private final int threshold; // 限流阈值
    private final int[] buckets; // 每个桶存储的请求数
    private int currentBucket; // 当前桶的索引
    private long lastBucketTime; // 处于末尾的通的开始时间

    public SlidingWindowRateLimiter(int windowSize, int granularity, int threshold) {
        this.windowSize = windowSize;
        this.granularity = granularity;
        this.threshold = threshold;
        this.buckets = new int[windowSize / granularity];
        this.currentBucket = 0;
        this.lastBucketTime = System.currentTimeMillis();
    }

    public synchronized boolean addRequest() {
        trySliderWindow();
        if (getCount() >= threshold) {
            return false;
        }
        buckets[currentBucket]++;
        return true;
    }

    public synchronized int getCount() {
        int total = 0;
        for (int count : buckets) {
            total += count;
        }
        return total;
    }

    /**
     * 尝试滑动滑动窗口
     */
    private void trySliderWindow() {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - lastBucketTime;
        if (elapsed >= granularity) {
            int bucketsToAdvance = (int) (elapsed / granularity);
            for (int i = 0; i < bucketsToAdvance && i < buckets.length; i++) {
                // 基于循环数组的实现
                currentBucket = (currentBucket + 1) % buckets.length;
                buckets[currentBucket] = 0;
            }
            lastBucketTime = currentTime - (elapsed % granularity);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(1000, 100, 20); // 1秒窗口，100ms秒粒度，阈值20次请求
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                long end = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(3);
                // 模拟请求
                int j = 0;
                while (System.currentTimeMillis() < end) {
                    if (slidingWindowRateLimiter.addRequest()) {
                        System.out.println("currentTime：" + (new Date()) + " thread" + Thread.currentThread().getName() + " 第" + j + "次请求通过 当前窗口请求数：" + slidingWindowRateLimiter.getCount());
                    } else {
                        System.err.println("currentTime：" + (new Date()) + " thread" + Thread.currentThread().getName() + " 第" + j + "次请求被限流了 当前窗口请求数" + slidingWindowRateLimiter.getCount());
                    }
                    j++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("finished = "+slidingWindowRateLimiter.getCount());
    }

}
