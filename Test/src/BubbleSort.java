/**
 * Created by Bruce on 11/7/16.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {17,3,25,14,20,9};
        BubbleSort sorter  = new BubbleSort();
        sorter.sort(array);
        for(int i : array) {
            System.out.print("=>" + i);
        }
    }
    public void sort(int[] array) {
        for(int i = 1; i <array.length; i++) {
            for(int j = 0; j < array.length-i; j++) {
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}
