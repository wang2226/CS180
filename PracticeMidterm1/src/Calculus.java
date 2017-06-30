/**
 * Created by Bruce on 10/3/16.
 */
public class Calculus {
    public String differentiate(String func) {
        if (func == null) {
            return null;
        }
        if (func.length() == 0) {
            return "";
        }
        String sa = func.substring(0,func.indexOf("x"));
        int a = Integer.parseInt(sa);
        String sb = func.substring(func.indexOf("^")+1,func.length());
        int b = Integer.parseInt(sb);
        int c = a * b;
        int d = b - 1;
        return c + "x^" + d;
    }
}
