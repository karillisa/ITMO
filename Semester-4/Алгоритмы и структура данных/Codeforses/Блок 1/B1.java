import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B1 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        long shovelCount = inputScanner.nextLong();

        long totalValue = 2 * shovelCount - 1;
        long maxDigits = String.valueOf(totalValue).length() - 1;

        if (totalValue == Long.parseLong("9".repeat(String.valueOf(shovelCount).length()))) {
            System.out.println("1");
        } else if (totalValue < 9) {
            System.out.println(shovelCount * (shovelCount - 1) / 2);
        } else {
            List<Long> nineValues = new ArrayList<>();
            long result = 0;
            StringBuilder nineBuilder = new StringBuilder();
            
            for (int j = 0; j < maxDigits; j++) {
                nineBuilder.append("9");
            }
            String nineString = nineBuilder.toString();

            for (long i = 0; i < 9; i++) {
                long nineValue = Long.parseLong(i + nineString);
                nineValues.add(nineValue);
            }

            for (long nineValue : nineValues) {
                if (nineValue <= shovelCount + 1) {
                    result += nineValue / 2;
                } else if (nineValue > 2 * shovelCount - 1) {
                    continue;
                } else {
                    result += (shovelCount - (nineValue - shovelCount) + 1) / 2;
                }
            }
            System.out.println(result);
        }
        inputScanner.close();
    }
}