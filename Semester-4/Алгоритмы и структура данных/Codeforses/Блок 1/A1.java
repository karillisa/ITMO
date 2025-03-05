import java.util.Scanner;

public class A1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int totalBrackets = scanner.nextInt();
            String bracketSequence = scanner.next();
            System.out.println(calculateUnbalancedBrackets(totalBrackets, bracketSequence.split("")));
        }
        scanner.close();
    }

    private static int calculateUnbalancedBrackets(int totalCount, String[] bracketArray) {
        int openCount = 0;
        int closeCount = 0;
        int unbalancedCount = 0;

        for (int i = 0; i < totalCount; i++) {
            if (bracketArray[i].equals("(")) {
                openCount++;
            } else if (bracketArray[i].equals(")")) {
                closeCount++;
            }

            int balance = openCount - closeCount;
            if (balance < 0) {
                unbalancedCount += 1;
                closeCount--;
            }
        }
        return unbalancedCount;
    }
}