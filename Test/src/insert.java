/**
 * Created by Bruce on 11/6/16.
 */
public class insert {
    public static void main(String args[]) {
        int[] example = {1,3,5,6};
        int index = search(example,0);
        int[] result;
        if(index == -1) {
            result = insert(example, 0);
            for(int i=0; i < result.length; i++) {
                System.out.print(result[i]+" ");
            }
        }
        else
            System.out.println(index);

    }
    public static int search(int[] example, int target) {
        int index = -1;
        for (int i = 0; i < example.length; i++) {
            if (example[i] == target)
                index = i;
        }
        return index;
    }

    public static int[] insert (int[] example, int target) {
        int[] copy = new int[example.length+1];

        if (target < example[0]) {
            copy[0] = target;
            for (int i = 0; i < example.length; i++) {
                copy[i+1] = example[i];
            }
            return copy;
        }

        if(target > example[example.length-1]) {
            copy[copy.length-1] = target;
            for (int i = 0; i < example.length; i++) {
                copy[i] = example[i];
            }
            return copy;
        }

        if(target > example[0] && target < example[example.length-1]) {
            for (int j = 0; j < example.length; j++) {
                    if (target > example[j] && target < example[j+1])
                        for(int i  = 0; i < j+1; i++) {
                            copy[i] = example[i];
                    }
                            copy[j+1] = target;
                        for(int i = j+1; i<example.length; i++ ) {
                            copy[i+1] = example[i];
                        }
                return copy;
            }
        }
        return null;
    }

}
