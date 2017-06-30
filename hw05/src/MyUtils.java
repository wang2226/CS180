/**
 * Created by Bruce on 9/26/16.
 */
public class MyUtils {
    public static boolean isNumeric(String str) {

        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }
    public static String formatStr(String str) {
        String formatStr;
        formatStr = str.substring(0,1).toUpperCase() + str.substring(1,str.length()).toLowerCase();
        return formatStr;
    }
}