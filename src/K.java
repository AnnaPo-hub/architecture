import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class K {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int length = Integer.parseInt(reader.readLine());
            String firstLine = reader.readLine().replaceAll("\\s", "");
            String secondLine = reader.readLine();

            int lengthFL = firstLine.length();
            int lengthSL = secondLine.length();
            if (lengthFL < lengthSL) {
                char[] array = new char[lengthSL - lengthFL];
                Arrays.fill(array, '0');
                firstLine = new String(array) + firstLine;

            } else if (lengthFL > lengthSL) {
                char[] array = new char[lengthFL - lengthSL];
                Arrays.fill(array, '0');
                secondLine = new String(array) + secondLine;
            }

            final String[] splitA = firstLine.split("");
            final ArrayList<String> stringsA = new ArrayList<>(Arrays.asList(splitA));

            final String[] splitB = secondLine.split("");
            final ArrayList<String> stringsB = new ArrayList<>(Arrays.asList(splitB));

            StringBuilder builder = new StringBuilder();

            int add = 0;
            final int size = Math.max(stringsB.size(), stringsA.size());
            for (int i = size - 1; i >= 0; i--) {

                final int sum = Integer.parseInt(stringsB.get(i)) + Integer.parseInt(stringsA.get(i)) + add;
                if (sum < 10) {
                    builder.append(sum + " ");
                    add = 0;
                } else if (sum == 10) {
                    builder.append("0" + " ");
                    add = 1;
                } else if (sum > 10) {
                    builder.append(sum - 10 + " ");
                    add = 1;
                }
            }

            if (add != 0) {
                builder.append(add);
            }
            System.out.println(builder.reverse().toString().trim());
        }
    }
}
