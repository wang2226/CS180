public class replaceCharacter {

    public static String replaceChar(String str, Character ch){
        String result = "";
        if(str == null || str.length() <= 0){
            return result;
        }

        for(int i=0; i<str.length();i++){
            if(str.charAt(i) == ch){
                result=result + (char)(str.length() - i + 48 -1 );
            } else {
                result=result + str.charAt(i);
            }
        }
        return result;

    }

    public static void main (String args[]){
        String testString = "cucumber";

        testString = replaceChar(testString, 'c');
        System.out.println(testString);
        char c = '2';
        System.out.println((int)(c-48));

    }
}