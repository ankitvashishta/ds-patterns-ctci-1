package com.ankit.problems.chapter1;


/**
 * Problem Statement :
 * Given an image represented by an N*N matrix, where each pixel in the image is 4 bytes.
 * Write a method to rotate the matrix by 90 degrees.
 * Bonus : Can you do it in place without using extra space.
 * <p>
 * Hint 1 : Try rotating it layer by layer.
 * Hint 2 : Rotating a specific layer would just mean swapping the values in four arrays. Start with two arrays and
 * extend to 4 arrays.
 * <p>
 * Since we're rotating the matrix by 90 degrees, easiest way to do this is to implement the rotation in layers.
 * We perform a circular rotation on each layer, moving the top edge to the right edge, right edge to the bottom edge,
 * the bottom edge to the left edge, and the left edge to the top edge.
 * <p>
 * One solution is to copy the top edge to an array, and then move the left to the top, the bottom to the left, and so on.
 * This requires O(N) memory, which is unnecessary.
 * <p>
 * Better way to do this is to implement the swap index by index.
 */
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5}
        };
        printMatrix(rotateMatrix(matrix));
    }

    /**
     * We perform the swap layer by layer, starting from outermost layer and working our way inwards. Alternatively,
     * we can start from the inner layer and work outwards.
     * <p>
     * Runtime Complexity : O(n^2) where n is the length of the side of the matrix. For a matrix of size n*n.
     */
    public static int[][] rotateMatrix(int[][] matrix) {
        int matrixLength = matrix.length;
        for (int layer = 0; layer < matrixLength / 2; layer++) {
            int first = layer;
            int last = matrixLength - 1 - layer;
            for (int i = first; i < last; i++) {
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - i][first]; // top
                matrix[last - i][first] = matrix[last][last - i]; // left
                matrix[last][last - i] = matrix[first + i][last]; // bottom
                matrix[first + i][last] = top; // right
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
