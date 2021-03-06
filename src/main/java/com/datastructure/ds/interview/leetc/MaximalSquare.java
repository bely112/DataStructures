package com.datastructure.ds.interview.leetc;

public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length, res = 0;

        int[][] arr = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    arr[i][j] = Math.min(Math.min(arr[i][j - 1],
                            arr[i - 1][j - 1]), arr[i - 1][j]) + 1;
                    res = Math.max(res, arr[i][j]);
                }
            }
        }

        return res * res;
    }
}
