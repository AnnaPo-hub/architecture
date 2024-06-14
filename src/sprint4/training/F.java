package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class F {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int base = Integer.parseInt(reader.readLine());
            final int mod = Integer.parseInt(reader.readLine());
            final String inputLine = reader.readLine();
            final int intervalQuantity = Integer.parseInt(reader.readLine());

            final HashMap<Integer, Long> separateHashMap = new HashMap<>();

            for (int i = 0; i < inputLine.length(); i++) {
                separateHashMap.put(i, getHash(inputLine.substring(i, i + 1), base, mod));
            }


            for (int i = 0; i < intervalQuantity; i++) {
                final List<Integer> interval = readList(reader);
                System.out.println(getIntervalHash(interval.get(0), interval.get(1), separateHashMap, base));
            }
        }
    }

    private static double getIntervalHash(int startIndex, int endIndex, HashMap<Integer, Long> separateHashMap, int base) {
        long hash = 0;
        for (int i = startIndex; i <= endIndex; i++) {

            hash+= separateHashMap.get(i);
        }

        return hash * Math.pow(base, startIndex);
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
