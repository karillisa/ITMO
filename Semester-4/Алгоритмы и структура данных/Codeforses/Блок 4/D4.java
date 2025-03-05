import java.io.*;
import java.util.*;

public class D4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[] array = new int[n + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] coords = new int[n + 1][2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            coords[i][0] = Integer.parseInt(st.nextToken());
            coords[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dist = new int[n + 1][n + 1];
        computeDistances(n, d, array, coords, dist);
        
        floydWarshall(n, dist);
        
        System.out.print(dist[1][n]);
    }

    private static void computeDistances(int n, int d, int[] array, int[][] coords, int[][] dist) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    int distance = Math.abs(coords[i][0] - coords[j][0]) + Math.abs(coords[i][1] - coords[j][1]);
                    dist[i][j] = distance * d - array[i];
                }
            }
        }
    }

    private static void floydWarshall(int n, int[][] dist) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}
