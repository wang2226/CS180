/**
 * Question 2 -- Complete the method haveSameChars
 */

public class StringManipulate {


    public static boolean haveSameChars(String s1, String s2) {

        int iCounter = 0;

        if(s1 == null || s2 == null ){
            return false;
        }
        if( s1.length() == 0 && s2.length() == 0){
            return true;
        }
        if(s1.length() != s2.length()) {
            return false;
        }

        for(int i=0; i<s1.length(); i++){
            for(int j=0; j<s2.length(); j++){
                if(s1.charAt(i) == s2.charAt(j)){
                    iCounter++;
                }
            }
        }
        if(iCounter == s1.length()){
            return true;
        }else {
            return false;
        }
    }
}
