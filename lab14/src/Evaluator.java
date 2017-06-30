import java.util.Stack;
import java.util.function.DoubleToLongFunction;

/**
 * Created by Bruce on 12/5/16.
 */
public class Evaluator {
    public static double evaluate(String rpn) {
        double result;
        String[] array = rpn.split(" ");
        String operators = "+-*/";
        Stack<String> stack = new Stack<>();
        for (String s : array) {
            if (!operators.contains(s)) {
                stack.push(s);
            } else {
                double a = Double.valueOf(stack.pop());
                double b = Double.valueOf(stack.pop());
                int index = operators.indexOf(s);
                switch (index) {
                    case (0):
                        stack.push(String.valueOf((a + b)));
                        break;
                    case(1):
                        stack.push(String.valueOf((b - a)));
                        break;
                    case(2):
                        stack.push(String.valueOf((a * b)));
                        break;
                    case(3):
                        stack.push(String.valueOf((b / a)));
                        break;
                }
            }
        }
        result = Double.valueOf(stack.pop());
        return result;
    }

    public static void main(String[] args) {
        String rpn = "5 1 2 + 4 * + 3 -";
        double result = evaluate(rpn);
        System.out.println(result);
    }
}
