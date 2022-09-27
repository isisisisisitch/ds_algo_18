package ca.bytetube._00_leetcode._00_array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 * @author dal
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return null;

        List<Integer> res = new ArrayList<>();
        int top = 0;
        int left = 0;
        int bottom = matrix.length - 1;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            //left top ---> right top
            for (int i = left; i <= right; i++) res.add(matrix[top][i]);
            top++;

            //right top ---> right bottom
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;

            if (top > bottom || left > right) break;

            //right bottom ---> left bottom
            for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
            bottom--;

            //left bottom ---> left top
            for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
            left++;
        }

        return res;
    }
}
