package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class E {
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

    private static long getLine(String st, long p, long m) {
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




    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final long base = Long.parseLong(reader.readLine()); //основание, по которому считается хеш
            final long mod = Long.parseLong(reader.readLine()); //модуль, то, на что будем делить
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            System.out.println("первая случайная строка  " + line1);
            System.out.println("хэш  строки 1  из input " + getHash(line1, base, mod));

            System.out.println("строка без первой буквы " + line2);
            System.out.println("хэш  строки 2  из input " + getHash(line2, base, mod));



        }
    }
}