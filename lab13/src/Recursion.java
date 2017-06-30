import java.io.File;

/**
 * Created by Bruce on 11/30/16.
 */
public class Recursion {
    /**
     * Calculates the determinant of the argument matrix recursively.
     *
     * @param matrix The matrix to find the determinant of
     * @return The determinant of the argument matrix
     */
    public static int determinant(int[][] matrix) {
        // TODO: implement this method
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        }

        int d = 0;
        int sign = 0;
        int length = matrix.length;
        int[][] A = new int[length - 1][length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int m = 1; m < matrix.length; m++) {
                for (int n = 0; n < matrix.length; n++) {
                    if (n > i) {
                        A[m - 1][n - 1] = matrix[m][n];
                    }
                    if (n < i) {
                        A[m - 1][n] = matrix[m][n];
                    }
                }
            }
            if (i % 2 == 0) {
                sign = 1;
            } else {
                sign = -1;
            }
            d = d + sign * matrix[0][i] * determinant(A);
        }
        return d;
    }

    /**
     * Counts the total number of files in the given directory recursively.
     *
     * @param f The current file or directory
     * @return The total number of files in f
     */
    public static int filecount(File f) {
        // TODO: implement this method
        int count = 0;
        File[] fileArray = f.listFiles();
        try {
            for (File file : fileArray) {
                if (file.isFile()) {
                    count++;
                }
                if (file.isDirectory()) {
                    count = count + filecount(file);
                }
            }
        } catch (NullPointerException e) {
            if (fileArray == null && f.canRead())
                count++;
        }
        return count;
    }
}
