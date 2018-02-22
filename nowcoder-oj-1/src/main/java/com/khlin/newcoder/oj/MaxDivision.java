package com.khlin.newcoder.oj;

import java.util.*;

/**
 * title:相邻最大差值<br/>
 * desc:
 * 请设计一个复杂度为O(n)的算法，计算一个未排序数组中排序后相邻元素的最大差值。
 * <p>
 * 给定一个整数数组A和数组的大小n，请返回最大差值。保证数组元素个数大于等于2小于等于500。
 * <p>
 * 测试样例：
 * [9,3,1,10],4
 * 返回：9
 */
public class MaxDivision {
    public int findMaxDivision(int[] A, int n) {
        // write code here
        Arrays.sort(A);
        int maxGap = Math.abs(A[0] - A[1]);
        for (int i = 1; i <= A.length - 2; i++) {
            int gap = Math.abs(A[i] - A[i + 1]);
            if (gap > maxGap) {
                maxGap = gap;
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 3, 1, 10};
        System.out.println(new MaxDivision().findMaxDivision(numbers, numbers.length));
    }
}
