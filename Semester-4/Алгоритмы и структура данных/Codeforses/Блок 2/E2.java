import java.util.*;

public class E2 {
    public static void solve(Iterator<String> str) {
        String firstLine = str.next();
        String secondLine = str.next();

        String[] firstParts = firstLine.split(" ");
        int n = Integer.parseInt(firstParts[0]);
        int I = Integer.parseInt(firstParts[1]);

        String[] secondParts = secondLine.split(" ");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(secondParts[i]);
        }

        Arrays.sort(a);
        I *= 8;
        int k = (int) Math.pow(2, I / n);

        List<Integer> b = new ArrayList<>();
        b.add(0);
        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1]) {
                b.add(i);
            }
        }

        if (b.size() <= k) {
            System.out.println(0);
            return;
        } else {
            int maxDifference = 0;
            for (int i = 0; i <= b.size() - k - 1; i++) {
                maxDifference = Math.max(maxDifference, b.get(i + k) - b.get(i));
            }
            System.out.println(n - maxDifference);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String f = scanner.nextLine();
        String s = scanner.nextLine();

        List<String> lines = new ArrayList<>();
        lines.add(f);
        lines.add(s);
        
        solve(lines.iterator());
        
        scanner.close();
    }
}