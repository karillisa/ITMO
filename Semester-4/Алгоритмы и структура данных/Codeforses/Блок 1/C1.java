import java.util.*;

public class C1 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String equation = inputScanner.nextLine().replace(" ", "");
        long targetValue = Long.parseLong(equation.split("=")[1]);
        long additionCount = equation.chars().filter(c -> c == '+').count() + 1;
        long subtractionCount = equation.chars().filter(c -> c == '-').count();
        
        long minPossibleValue = additionCount - targetValue * subtractionCount;
        long maxPossibleValue = additionCount * targetValue - subtractionCount;

        if (targetValue < minPossibleValue || targetValue > maxPossibleValue) {
            System.out.println("Impossible");
            return;
        }
        
        System.out.println("Possible");
        StringBuilder result = new StringBuilder();
        long currentValue = 0;

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == '?') {
                if (i > 0 && equation.charAt(i - 1) == '-') {
                    if (subtractionCount == 1 && additionCount == 0) {
                        result.append("- ").append(currentValue - targetValue).append(" ");
                        break;
                    }
                    subtractionCount--;
                    for (long x = 1; x <= targetValue; x++) {
                        if (currentValue - x + additionCount - targetValue * subtractionCount <= targetValue && 
                            targetValue <= currentValue - x + targetValue * additionCount - subtractionCount) {
                            currentValue -= x;
                            result.append("- ").append(x).append(" ");
                            break;
                        }
                    }
                } else {
                    if (additionCount == 1 && subtractionCount == 0) {
                        result.append("+ ").append(targetValue - currentValue).append(" ");
                        break;
                    }
                    additionCount--;
                    for (long x = 1; x <= targetValue; x++) {
                        if (currentValue + x + additionCount - targetValue * subtractionCount <= targetValue && 
                            targetValue <= currentValue + x + targetValue * additionCount - subtractionCount) {
                            currentValue += x;
                            result.append("+ ").append(x).append(" ");
                            break;
                        }
                    }
                }
            }
        }
        
        result.append("= ").append(targetValue);
        System.out.println(result.toString().substring(2));
    }
}