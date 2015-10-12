package ch06.ex09;

import java.util.Objects;

/**
 * This represents matrix which has methods for calculation.
 * Created by yukiohta on 2015/10/05.
 */
public class Matrix {
    private final int row;
    private final int col;
    private final int[][] data;

    /**
     * Constructs Matrix object.
     *
     * @param data data with m*n values
     * @throws NullPointerException     if data is null
     * @throws IllegalArgumentException if data length is 0 or data does not have m*n values
     */
    public Matrix(int[][] data) {
        Objects.requireNonNull(data, "data must not be null");

        if (data.length == 0) {
            throw new IllegalArgumentException("data length must not be 0");
        }

        this.row = data.length;
        this.col = data[0].length;
        this.data = new int[row][col];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (data[i].length != col) {
                    throw new IllegalArgumentException("data must have m*n values");
                }

                this.data[i][j] = data[i][j];
            }
        }
    }

    /**
     * Returns value at the position.
     *
     * @param i row index
     * @param j column index
     * @return value
     * @throws IllegalArgumentException if i or j is negative, i is over row, or j is over column.
     */
    public int get(int i, int j) {
        if (i < 0 || i >= row) {
            throw new IllegalArgumentException("i is out of range");
        }

        if (j < 0 || j >= col) {
            throw new IllegalArgumentException("j is out of range");
        }

        return data[i][j];
    }

    /**
     * Returns the number of row.
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the number of column
     *
     * @return column
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns multiplied Matrix.
     *
     * @param other matrix to multiply
     * @return multiplied matrix
     * @throws NullPointerException     if other is null
     * @throws IllegalArgumentException if row number is not equals to column number of multiplied matrix
     */
    public Matrix multiply(Matrix other) {
        Objects.requireNonNull(other, "other must not be null");

        if (col != other.row)
            throw new IllegalArgumentException("row number must be equals to column number of multiplied matrix");

        int[][] calc = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < other.col; j++) {
                for (int k = 0; k < col; k++) {
                    calc[i][j] += data[i][k] * other.data[k][j];
                }
            }
        }

        return new Matrix(calc);
    }
}
