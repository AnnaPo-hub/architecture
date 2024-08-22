package sprint8;

import java.io.*;

public class L {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String string = reader.readLine();

            int[] ints = prefixFunction(string);

            for (int i = 0; i < ints.length; i++) {
                writer.write(ints[i] + " ");
            }
        }
    }

    public static int[] prefixFunction(String s) {
        // Функция возвращает массив длины |s|
        int n = s.length();
        int[] pi = new int[n];
        pi[0] = 0;
        for (int i = 1; i < n; i++) {
            int k = pi[i - 1];
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = pi[k - 1];
            }
            if (s.charAt(k) == s.charAt(i)) {
                k++;
            }
            pi[i] = k;
        }
        return pi;
    }
}
