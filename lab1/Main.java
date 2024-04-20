import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] c = new int[9];
        int value = 20;
        for (int i = 0; i < c.length; i++) {
            c[i] = value;
            value -= 2;
        }

        System.out.println("Array c:");
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
        System.out.println();

        double[] x = new double[15];
        Random random = new Random();
        for (int i = 0; i < x.length; i++) {
            x[i] = -2.0 + (11.0 - (-2.0)) * random.nextDouble();
        }

        System.out.println("Array x:");
        DecimalFormat df = new DecimalFormat("#.#####");
        for (int i = 0; i < x.length; i++) {
            System.out.println(df.format(x[i]));
        }
        System.out.println();

        double[][] result = new double[9][15];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (c[i] == 6) {
                    result[i][j] = Math.tan(Math.cos(Math.log(Math.abs(x[j]))));
                } else if (c[i] == 8 || c[i] == 14 || c[i] == 18 || c[i] == 20) {
                    double numerator = Math.pow(Math.E, Math.pow(0.25 / x[j], 0.333));
                    double denominator = Math.sin(x[j]) - 1;
                    result[i][j] = numerator / denominator;
                } else {
                    result[i][j] = Math.pow(Math.cos(Math.cos(Math.cos(x[j]))), 2) *
                            (3 + Math.cos(Math.atan((x[j] + 4.5 / 13) / 1 / 3)));
                }
            }
        }

        System.out.println("Matrix result:");
        for (int i = 0; i < 9; i++) {
            Arrays.sort(result[i]);
            for (int j = 0; j < 15; j++) {
                System.out.printf("%10.5f\t", result[i][j]);
            }
            System.out.println();
        }
    }
}
