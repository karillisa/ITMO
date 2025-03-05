import java.util.Scanner;

public class C2 {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int size = inputScanner.nextInt();
        int requiredCount = inputScanner.nextInt();
        int[] values = new int[size];

        for (int index = 0; index < size; index++) {
            values[index] = inputScanner.nextInt();
        }

        int leftBoundary = 1, rightBoundary = size + 1;

        while (leftBoundary + 1 < rightBoundary) {
            int midValue = (leftBoundary + rightBoundary + 1) / 2;
            int[] prefixSum = new int[size];

            for (int index = 0; index < size; index++) {
                prefixSum[index] = values[index] >= midValue ? 1 : -1;
                prefixSum[index] += index > 0 ? prefixSum[index - 1] : 0;
            }

            int currentDifference = prefixSum[requiredCount - 1], minPrefixSum = 0;
            for (int index = requiredCount; index < size; index++) {
                minPrefixSum = Math.min(minPrefixSum, prefixSum[index - requiredCount]);
                currentDifference = Math.max(currentDifference, prefixSum[index] - minPrefixSum);
            }

            if (currentDifference > 0) {
                leftBoundary = midValue;
            } else {
                rightBoundary = midValue;
            }
        }
        System.out.println(leftBoundary);
    }
}
