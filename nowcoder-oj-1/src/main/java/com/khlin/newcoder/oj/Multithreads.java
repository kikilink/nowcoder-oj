package com.khlin.newcoder.oj;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * title:多线程<br/>
 * desc:有4个线程和1个公共的字符数组。线程1的功能就是向数组输出A，线程2的功能就是向字符输出B，线程3的功能就是向数组输出C，线程4的功能就是向数组输出D。要求按顺序向数组赋值ABCDABCDABCD，ABCD的个数由线程函数1的参数指定。[注：C语言选手可使用WINDOWS SDK库函数]
 * 输入描述:
 * 输入一个int整数
 * <p>
 * 输出描述:
 * 输出多个ABCD
 * <p>
 * 示例1
 * 输入
 * 10
 * 输出
 * ABCDABCDABCDABCDABCDABCDABCDABCDABCDABCD
 */
public class Multithreads {
    public static void main(String[] args) {
        int threadNumber = 4;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int times = sc.nextInt();
            //计数器,用来记录当前添加数组元素的个数,从1开始计
            AtomicInteger counter = new AtomicInteger(1);
            int totalTimes = times * threadNumber;
            AtomicReferenceArray<String> strArray = new AtomicReferenceArray<String>(
                    totalTimes);
            Object lock = new Object();

            ExecutorService es = Executors.newFixedThreadPool(threadNumber);
            for (int i = 0; i <= threadNumber - 1; i++) {
                Thread thread = new Thread(
                        new WriteThread((i + 1) % threadNumber, counter,
                                totalTimes, lock, threadNumber, strArray,
                                String.valueOf((char) (65 + i))),
                        "WriteThread-" + i);
                es.submit(thread);
            }
            es.shutdown();
            try {
                es.awaitTermination(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            StringBuilder builder = new StringBuilder(totalTimes);
            for (int i = 0; i <= strArray.length() - 1; i++) {
                builder.append(strArray.get(i));
            }

            System.out.println(builder.toString());
        }
    }

    private static class WriteThread implements Runnable {
        private int offset;
        private AtomicInteger counter;
        private int total;
        private Object lock;
        private int threadNumber;
        private volatile AtomicReferenceArray<String> strArray;
        private String mark;

        WriteThread(int offset, AtomicInteger counter, int total, Object lock,
                    int threadNumber, AtomicReferenceArray<String> strArray,
                    String mark) {
            //偏移量,即余数
            this.offset = offset;
            this.counter = counter;
            this.total = total;
            this.lock = lock;
            this.threadNumber = threadNumber;
            this.strArray = strArray;
            this.mark = mark;
        }

        @Override
        public void run() {
            if (this.strArray.length() != total) {
                System.out.println("Number error.");
                return;
            }

            while (this.counter.get() <= total) {
                synchronized (this.lock) {
                    int current = this.counter.get();
                    //当余数相等时,说明轮到该线程添加元素
                    if (current % threadNumber == offset) {
                        strArray.set(current - 1, this.mark);
                        this.counter.getAndIncrement();
                        this.lock.notifyAll();
                    } else {
                        try {
                            this.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
