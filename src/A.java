import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException { try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int emptyPlaceQuant = Integer.parseInt(reader.readLine());
            ArrayList<Integer> emptyPlaces = new ArrayList<Integer>();
            int value;
            int position = -1;
            boolean previousIsSpace = true;

            while ((value = reader.read()) > 0) {
                if (previousIsSpace && value != 32) {
                    ++position;
                    if (value == 48) {
                        emptyPlaces.add(position);
                    }
                }
                previousIsSpace = (value == 32);
                if (position == emptyPlaceQuant - 1) break;
            }

            int lastPrevZero = -1;
            int lastNextZero = -1;
            int lastNextIndex = 0;


            for (int i = 0; i < emptyPlaceQuant; ++i) {
                if (lastNextZero == -1 || i > lastNextZero) {
                    if (lastNextZero >= 0) {
                        lastPrevZero = lastNextZero;
                        lastNextZero = -1;
                    }

                    for (int j = lastNextIndex; j < emptyPlaces.size(); ++j) {
                        if (emptyPlaces.get(j) >= i) {
                            lastNextZero = emptyPlaces.get(j);
                            lastNextIndex = j;
                            break;
                        }
                    }


                }

                if (lastNextZero >= 0 && lastPrevZero >= 0) {
                    System.out.printf("%d ", Math.min(lastNextZero - i, i - lastPrevZero));
                }
                if (lastNextZero < 0 && lastPrevZero >= 0) {
                    System.out.printf("%d ", i - lastPrevZero);
                }
                if (lastNextZero >= 0 && lastPrevZero < 0) {
                    System.out.printf("%d ", lastNextZero - i);
                }
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
