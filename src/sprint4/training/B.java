package sprint4.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int n = Integer.parseInt(reader.readLine());

            final HashMap<String, Integer> gamesScore = new HashMap<>();
            gamesScore.put("0", 0);
            gamesScore.put("1", 0);

            final String[] scores = reader.readLine().split(" ");

            for (int i = 0; i < n; i++) {

                switch (scores[i]) {
                    case "0":
                        Integer value0 = gamesScore.get("0");
                        gamesScore.put("0", ++value0);
                        break;
                    case "1":
                        Integer value1 = gamesScore.get("1");
                        gamesScore.put("1", ++value1);
                        break;
                    default:
                        break;
                }
            }
            System.out.println(n > 0 ? gamesScore.values().stream().min(Integer::compare).get() * 2 : 0);
        }
    }
}
