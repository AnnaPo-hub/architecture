package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class D {
    long hash_value;

    D(String st, long p, long m) {
        long hash_so_far = 0;
        final char[] s = st.toCharArray();
        final int n = s.length;
        long a = 1;
        for (int i = n; i > 0; --i) {
            long ch = (long) s[i - 1];
            hash_so_far = (hash_so_far + ch * a) % m;
            a = a * p % m;
        }
        hash_value = hash_so_far % m;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final long base = Long.parseLong(reader.readLine()); //основание, по которому считается хеш
            final long mod = Long.parseLong(reader.readLine()); //модуль, то, на что будем делить
            String s = reader.readLine();
            String s2 = reader.readLine();



               D h = new D(s, base, mod);
              System.out.println(h.hash_value);


        }
    }
}