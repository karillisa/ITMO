import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class C3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(validateBrackets(input));
    }

    public static String validateBrackets(String s) {
        Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put('(', ')');
        bracketPairs.put('{', '}');
        bracketPairs.put('[', ']');
        bracketPairs.put('<', '>');

        Map<Character, Character> reversedBrackets = new HashMap<>();
        for (Map.Entry<Character, Character> entry : bracketPairs.entrySet()) {
            reversedBrackets.put(entry.getValue(), entry.getKey());
        }

        Stack<Character> stack = new Stack<>();
        int mistakeCount = 0;

        for (char c : s.toCharArray()) {
            if (bracketPairs.containsKey(c)) {
                stack.push(c);
            } else if (reversedBrackets.containsKey(c)) {
                if (stack.isEmpty()) {
                    return "Impossible";
                } else if (reversedBrackets.get(c).equals(stack.peek())) {
                    stack.pop();
                } else {
                    mistakeCount++;
                    stack.pop(); 
                }
            }
        }

        return stack.isEmpty() ? String.valueOf(mistakeCount) : "Impossible";
    }
}
