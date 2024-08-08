Иван, привет
Я провела несколько тестов для выяснения производительности и получила следующие результаты:

1. Стандартное считывание занимает **825 ms** https://contest.yandex.ru/contest/22450/run-report/112649576/#test-1
   в этом тесте я считала в массив строку и вывела нулевой элемент, чтобы обмануть оптимизатор

```
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int emptyPlaceQuant = Integer.parseInt(reader.readLine());

            ArrayList<String> inputLine = new ArrayList<>(List.of(reader.readLine().split(" ")));
            int leftEmpty = -1;
            int rightEmpty = -1;
            PrintWriter pw =
                    new PrintWriter(System.out, true);
            pw.print(inputLine.get(0));
            pw.flush();
            pw.close();
        }
    }
}
```

2. Стандартное считывание и вывод считанного обратно - **1373 ms**.
   Этот вариант можно считать обязательными затратами на ввод/вывод. Таким образом на весь остально алгоритм остаетс *
   *223 ms**, то есть всего 15% времени.
   Это очень мало(
   посылка https://contest.yandex.ru/contest/22450/run-report/112649948/

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int emptyPlaceQuant = Integer.parseInt(reader.readLine());

            ArrayList<String> inputLine = new ArrayList<>(List.of(reader.readLine().split(" ")));
            int leftEmpty = -1;
            int rightEmpty = -1;
            PrintWriter pw =
                    new PrintWriter(System.out, true);
            for (int i =0; i<emptyPlaceQuant;++i)
             pw.print(inputLine.get(i)+" ");
            pw.flush();
            pw.close();
        }
    }
}
```

3. Рабочий вариант со стандартным считыванием и использованием PrintWriter **1682 ms**.
   посылка https://contest.yandex.ru/contest/22450/run-report/112647386/

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class A {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int emptyPlaceQuant = Integer.parseInt(reader.readLine());

            ArrayList<String> inputLine = new ArrayList<>(List.of(reader.readLine().split(" ")));
            int leftEmpty = -1;
            int rightEmpty = -1;

            PrintWriter pw = new PrintWriter(System.out, true);
            for (int i = 0; i < emptyPlaceQuant; ++i) {
                if (inputLine.get(i).equals("0")) {
                    pw.print("0 ");
                    rightEmpty = i;
                } else {
                    if (!inputLine.get(i).equals("0") && rightEmpty < leftEmpty && leftEmpty > 0) {
                        pw.print(rightEmpty > 0 ? Math.min(i - rightEmpty, leftEmpty - i) + " " :
                                leftEmpty - i + " ");
                        continue;
                    }

                    if (leftEmpty <= rightEmpty || leftEmpty < 0) {
                        for (int j = i; j < emptyPlaceQuant; ++j) {
                            if (inputLine.get(j).equals("0")) {
                                leftEmpty = j;
                                pw.print(rightEmpty > -1 ? Math.min(i - rightEmpty, leftEmpty - i) + " "
                                        : leftEmpty - i + " ");
                                break;
                            }
                            if (j == emptyPlaceQuant - 1) {
                                pw.print(i - rightEmpty + " ");
                            }
                        }
                    }
                }
            }
            pw.flush();
            pw.close();
        }
    }
}
```

4. Рабочий вариант со стандартным считыванием и БЕЗ использования PrintWriter **1691 ms**.
   посылка https://contest.yandex.ru/contest/22450/run-report/112646362/
   Таким образом использование PrintWriter дает выгоду только в 9 мс.

---

Иван, добрый вечер

После внедрения предложенных шагов по оптимизации, скорость работы всё равно остаётся недостаточной для прохода
автоматических тестов.
Посылки: https://contest.yandex.ru/contest/22450/run-report/112759975/, https://contest.yandex.ru/contest/22450/run-report/112759592/, https://contest.yandex.ru/contest/22450/run-report/112759385/

На основании этого прошу повторно рассмотреть возможность зачесть направленный первоначальный
результат https://contest.yandex.ru/contest/22450/run-report/112294826/, который соответствует фукциональным и системным
требованиям.
В случае отказа, прошу конструктивно указать имеющиеся недостатки. 
