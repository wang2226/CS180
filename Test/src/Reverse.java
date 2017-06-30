/**
 * Created by Bruce on 11/5/16.
 */
public class Reverse {
    public static void main(String args[]) {
        String example = "sky is blue red";
    String[] words = example.split(" ");
    /*
        int start = 0;
        int end = words.length - 1;
        String temp = "";
        while(start < end) {
            temp = words[start];
            words[start] = words[end];
            words[end] = temp;
            start++;
            end--;
        }
        */
    String temp = "";
    for(int i=0; i<words.length/2;i++){
        temp = words[i];
        words[i] = words[words.length-1-i];
        words[words.length-1-i] = temp;
    }
    String s ="";
    for (int j=0; j < words.length; j++) {
        s = s + words[j];
        if(j != words.length - 1)
            s = s + " ";
    }
    System.out.print(s);
    }
}
