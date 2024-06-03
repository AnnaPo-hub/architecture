package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class D {
    long hash_value;

    D(String st, int p, int m) {
        long hash_so_far = 0;
        final char[] s = st.toCharArray();
        long p_pow = 1;
        final int n = s.length;
        for (int i = 0; i < n; i++) {
            hash_so_far = (long) ((hash_so_far + (int) s[i] * Math.pow(p, n - (i + 1))) % m);
            p_pow = (p_pow * p) % m;
        }
        hash_value = hash_so_far;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int base = Integer.parseInt(reader.readLine()); //основание, по которому считается хеш
            final int mod = Integer.parseInt(reader.readLine()); //модуль, то, на что будем делить

            String s = reader.readLine();
            D h = new D(s, base, mod);
            System.out.println(h.hash_value);
        }
    }
}

