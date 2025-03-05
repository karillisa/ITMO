import java.util.*;

public class D3 {

    public static int calculateLogarithm(int number) {
        return (int) (Math.log10(number) + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        
        while (testCases-- > 0) {
            int size = scanner.nextInt();
            PriorityQueue<Integer> firstQueue = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> secondQueue = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < size; i++) {
                firstQueue.add(scanner.nextInt());
            }
            for (int i = 0; i < size; i++) {
                secondQueue.add(scanner.nextInt());
            }

            int operationCount = 0;

            while (!firstQueue.isEmpty()) {
                int firstMax = firstQueue.peek();
                int secondMax = secondQueue.peek();

                if (firstMax < secondMax) {
                    secondQueue.poll();
                    secondQueue.add(calculateLogarithm(secondMax));
                    operationCount++;
                } else if (firstMax > secondMax) {
                    firstQueue.poll();
                    firstQueue.add(calculateLogarithm(firstMax));
                    operationCount++;
                } else {
                    firstQueue.poll();
                    secondQueue.poll();
                }
            }
            System.out.println(operationCount);
        }
    }
}