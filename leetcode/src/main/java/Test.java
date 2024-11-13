import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        String s = "addfdadfghdjskajdddjshs";
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        stack.push(s.charAt(0));
        for (int i = 1; i < length; i++) {
           char c = stack.peek();
           if (c == s.charAt(i)) {
               stack.pop();
           } else  {
               stack.push(s.charAt(i));
           }
        }
        stack.iterator();
        System.out.println(stack);
    }
}
