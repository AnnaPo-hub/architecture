package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class F2 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int base = Integer.parseInt(reader.readLine());
            final int mod = Integer.parseInt(reader.readLine());
            final String inputLine = reader.readLine();
            final int intervalQuantity = Integer.parseInt(reader.readLine());

            final HashMap<String, Long> separateHashMap = new HashMap<>();

            long hash = 0;


            for (int k = 0; k <  inputLine.length(); k++) {
                final String substring = inputLine.substring(k, k+1);
                hash = getHash(substring, base, mod);
                separateHashMap.put(substring, hash);
                for (int i = 1; i < inputLine.length(); i++) {
                    int pow = (int) Math.pow(base, i);
                    hash = (hash * pow + (int) inputLine.charAt(i)) % mod;
                    separateHashMap.put(inputLine.substring(0, i + 1), hash);
                }
            }

            System.out.println(separateHashMap.entrySet());

            for (int i = 0; i < intervalQuantity; i++) {
                final List<Integer> indexes = readList(reader);
                final String substring = inputLine.substring(indexes.get(0), indexes.get(1));
                System.out.println(separateHashMap.get(substring));
            }

        }

    }

    private static long getHash(String st, long p, long m) {
        long hash_so_far = 0;
        final char[] s = st.toCharArray();
        final int n = s.length;
        long a = 1;
        for (int i = n; i > 0; --i) {
            long ch = (long) s[i - 1];
            hash_so_far = (hash_so_far + ch * a) % m;
            a = a * p % m;
        }
        return hash_so_far % m;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().trim().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
