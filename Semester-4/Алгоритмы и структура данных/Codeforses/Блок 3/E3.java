import java.util.*;

public class E3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; ++i) {
            v[i] = scanner.nextInt();
        }
        
        TreeSet<Integer> elements = new TreeSet<>();
        Map<Integer, Boolean> hasLeft = new HashMap<>();
        Map<Integer, Boolean> hasRight = new HashMap<>();
        
        elements.add(v[0]);
        List<Integer> parents = new ArrayList<>();
        
        for (int i = 1; i < n; ++i) {
            int x = v[i];
            Integer pred = elements.lower(x);
            Integer succ = elements.higher(x);
            
            int parent = -1;
            if (pred != null && !hasRight.getOrDefault(pred, false)) {
                parent = pred;
                hasRight.put(pred, true);
            } else if (succ != null && !hasLeft.getOrDefault(succ, false)) {
                parent = succ;
                hasLeft.put(succ, true);
            }
            
            parents.add(parent);
            elements.add(x);
        }
        
        for (int p : parents) {
            System.out.print(p + " ");
        }
        
        scanner.close();
    }
}