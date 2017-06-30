/**
 * Created by Bruce on 11/6/16.
 */
public class AddDiaganol {
    public static void main(String[] args) {
        int[][] test = {{1,2,3},{4,5,6},{7,8,9}};
        int sum = 0;
        for(int i = 0; i < test.length; i++){
            sum = sum + test[i][i];
        }
        System.out.println(sum);
    }
}
