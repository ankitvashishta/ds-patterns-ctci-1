package com.ankit.problems.chapter1;

/**
 * Problem Statement :
 * Write an algorithm such that if an element in an M*N matrix is 0, its entire row and column are set to 0.
 * <p>
 * At first, this problem looks easy - just iterate through the matrix & every time we see a cell with zero value,
 * set its row and column to 0. However, there's a problem - when we see come across other cells in that row OR column,
 * we'll see the zeros and change their row and column to zero. Pretty soon, our entire matrix will be set to zeroes.
 * <p>
 * One work around is to keep a second matrix which flags the zero locations. We would then do a second pass through the
 * matrix to set the zeroes. This would take O(MN) space.
 * <p>
 * To optimise this storage to O(M+N), see the method : convertMatrix
 * <p>
 * To optimise the storage further to O(1), see the method : convertMatrix_optimised.
 */
public class ZeroMatrix {

    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };
        int[][] matrix2 = {
                {1, 2, 3},
                {0, 4, 5},
                {6, 7, 8}
        };
        printMatrix(matrix1);
        System.out.println();
        printMatrix(convertMatrix(matrix1));
        System.out.println();
        printMatrix(convertMatrix_optimised(matrix2));

    }


    /**
     * We don't care what exactly cell was zero, as we are going to set the entire row and column to zero.
     * We just need to now that row i and column j has a zero somewhere, and we'll set the whole row and column to zero.
     * <p>
     * Hence, we use two arrays to keep track of all the rows and columns with zeroes. We then zerofy rows and columns
     * based on values in these arrays.
     * <p>
     * Runtime Complexity : O(M*N) as we need to scan all the cells.
     * Space Complexity : O(M + N)
     */
    public static int[][] convertMatrix(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] columns = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    columns[j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (rows[i]) {
                zerofyRows(matrix, i);
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (columns[j]) {
                zerofyColumns(matrix, j);
            }
        }
        return matrix;

    }

    /**
     * We can reduce the space to O(1) by using the first row and column as a replacement for the row array and
     * the column array.
     *
     * Process :
     * 1. Check if the first row OR column has zero and set the variables accordingly.
     * 2. Iterate the rest of matrix and populate the matrix[i][0] & matrix[0][j] as zero for a zero in matrix[i][j].
     * 3. Iterate through the matrix and zerofy the row i if there's a zero in matrix[i][0]
     * 4. Iterate through the matrix and zerofy the column j if there's a zero in matrix[0][j]
     * 5. Zerofy the first row and column if applicable.
     *
     * Runtime Complexity : O(M*N) as we scan all the cells
     * Space Complexity : O(1) as no additional memory is used.
     */
    public static int[][] convertMatrix_optimised(int[][] matrix) {
        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnHasZero = true;
                break;
            }
        }


        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                zerofyRows(matrix, i);
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                zerofyColumns(matrix, j);
            }
        }

        if (firstRowHasZero) zerofyRows(matrix, 0);
        if (firstColumnHasZero) zerofyColumns(matrix, 0);
        return matrix;

    }

    private static void zerofyRows(int[][] matrix, int row) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private static void zerofyColumns(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][column] = 0;
        }
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
