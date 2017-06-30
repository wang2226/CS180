/**
 * Created by Bruce on 10/3/16.
 */
public class Matrix {

    public boolean isSymmetric(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isDiagonal(int[][] matrix) {
        int p = 0;
        int q = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i!=j && matrix[i][j]!=0) {
                    p = 1;
                    break;
                } if (i==j & matrix[i][j]==0) {
                    q++;
                }
            }
        }
        if (p==0 && q<matrix.length) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isIdentity(int[][] matrix) {
        int p = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j< matrix.length; j++) {
                if ((i==j && matrix[i][j]!=1) || (i!=j && matrix[i][j]!=0)) {
                    p = 1;
                    break;
                }
            }
            if (p == 1)
                break;
        }
        if (p == 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isUpperTriangular(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int cell = matrix[i][j];
                if (i > j) {
                    if (cell != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isTriDiagonal(int[][] matrix) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                int cell = matrix[x][y];

                if ((x==y) || (x-1==y) || (x+1==y)) {
                    if (cell == 0) {
                        return false;
                    }
                } else {
                    if (cell != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Matrix m = new Matrix();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("is symmetric: " + m.isSymmetric(matrix));
        System.out.println("is diagonal: " + m.isDiagonal(matrix));
        System.out.println("is identity: " + m.isIdentity(matrix));
        System.out.println("is upper triangular: " + m.isUpperTriangular(matrix));
        System.out.println("is tri diagonal: " + m.isTriDiagonal(matrix));
    }
}
