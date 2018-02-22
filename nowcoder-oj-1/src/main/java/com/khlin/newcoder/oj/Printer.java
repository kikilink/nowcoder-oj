package com.khlin.newcoder.oj;

import java.util.*;

/**
 * title: 之字形打印矩阵<br/>
 * desc:
 * 对于一个矩阵，请设计一个算法，将元素按“之”字形打印。具体见样例。
 * <p>
 * 给定一个整数矩阵mat，以及他的维数nxm，请返回一个数组，其中元素依次为打印的数字。
 * <p>
 * 测试样例：
 * [[1,2,3],[4,5,6],[7,8,9],[10,11,12]],4,3
 * 返回：[1,2,3,6,5,4,7,8,9,12,11,10]
 */
public class Printer {
    public int[] printMatrix(int[][] mat, int n, int m) {
        if (!check(mat, n, m)) {
            return null;
        }
        int totalSize = n * m;
        int[] result = new int[totalSize];
        for (int i = 0; i <= mat.length - 1; i++) {
            int[] row = mat[i];
            if (i % 2 == 0) {
                System.arraycopy(row, 0, result, i * m, m);
            } else {
                reverse(row);
                System.arraycopy(row, 0, result, i * m, m);
            }
        }
        return result;
    }


    private static void reverse(int[] array) {
        int size = array.length;
        for (int i = 0; i <= (size - 1) / 2; i++) {
            int temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
    }

    private static boolean check(int[][] matrix, int n, int m) {
        if (null == matrix) return false;

        if (matrix.length != n) {
            return false;
        }
        for (int[] row : matrix) {
            if (null == row || row.length != m) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Printer instance = new Printer();
        int[][] maxtrix1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        int[][] maxtrix2 = new int[][]{{26, 0}, {81, 14}};
        System.out.println(Arrays.toString(instance.printMatrix(maxtrix2, 2, 2)));
        System.out.println(Arrays.toString(instance.printMatrix(maxtrix1, 4, 3)));
    }

}