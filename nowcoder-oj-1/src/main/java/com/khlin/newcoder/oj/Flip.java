package com.khlin.newcoder.oj;

import java.util.*;

/**
 * title:棋子翻转<br/>
 * desc:在4x4的棋盘上摆满了黑白棋子，黑白两色的位置和数目随机其中左上角坐标为(1,1),右下角坐标为(4,4),现在依次有一些翻转操作，要对一些给定支点坐标为中心的上下左右四个棋子的颜色进行翻转，请计算出翻转后的棋盘颜色。
 * <p>
 * 给定两个数组A和f,分别为初始棋盘和翻转位置。其中翻转位置共有3个。请返回翻转后的棋盘。
 * <p>
 * 测试样例：
 * [[0,0,1,1],[1,0,1,0],[0,1,1,0],[0,0,1,0]],[[2,2],[3,3],[4,4]]
 * 返回：[[0,1,1,1],[0,0,1,0],[0,1,1,0],[0,0,1,0]]
 */
public class Flip {
    //[2,3]表示第二行第三列
    public int[][] flipChess(int[][] A, int[][] f) {
        int n = A.length;
        for (int[] point : f) {
            int y = point[0] - 1;
            int x = point[1] - 1;
            int left = x - 1;
            int right = x + 1;
            int top = y - 1;
            int bottom = y + 1;
            if (left >= 0) {
                A[y][left] = A[y][left] == 0 ? 1 : 0;
            }
            if (right <= n - 1) {
                A[y][right] = A[y][right] == 0 ? 1 : 0;
            }
            if (top >= 0) {
                A[top][x] = A[top][x] == 0 ? 1 : 0;
            }
            if (bottom <= n - 1) {
                A[bottom][x] = A[bottom][x] == 0 ? 1 : 0;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[][] maxtrix = new int[][]{{0,1,0,0},{1,0,1,0},{1,1,0,0},{1,0,0,1}};
        int[][] points = new int[][]{{2, 3}, {4, 2}, {2, 3}};
        int[][] result = new Flip().flipChess(maxtrix, points);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
