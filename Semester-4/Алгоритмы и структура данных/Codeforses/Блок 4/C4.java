import java.io.*;
import java.util.*;

public class C4{
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(new File("input.txt"));
		PrintWriter output = new PrintWriter("output.txt");
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		int[] x = new int[K];
		int[] y = new int[K];
		for(int i = 0 ; i < K ; i++){
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}
		int max = -1, max_x = -1, max_y = -1;
		for(int i = 1 ; i <= N ; i++){
			for(int j = 1 ; j <= M ; j++){
				int min = Integer.MAX_VALUE;
				for(int k = 0 ; k < K ; k++){
					min = Math.min(min, Math.abs(x[k] - i) + Math.abs(y[k] - j));
				}
				if(min > max){
					max = min;
					max_x = i;
					max_y = j;
				}
			}
		}
		output.println(max_x + " " + max_y);
		output.flush();
	}
}