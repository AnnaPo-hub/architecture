package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


//class Lesson {
class Lesson implements Comparable<Lesson> {
    double start;
    double end;

    public Lesson(double start, double end) {
        this.start = start;
        this.end = end;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(start) + " " + format.format(end);
    }

    @Override
    public int compareTo(Lesson lesson) {
        return Double.compare(start, lesson.start);
    }
}

public class B {


    ArrayList<Lesson> schedule;
    int counter;
    Lesson previousLesson;

    ArrayList<Double> starts;
    ArrayList<Double> ends;

    public B() {
        this.schedule = new ArrayList<>();
        counter = 0;
        previousLesson = null;
        starts = new ArrayList<>();
        ends = new ArrayList<>();
    }

    private boolean isStartOk(double start) {
        for (int i = 0; i < starts.size(); i++) {
            if (start > starts.get(i) && start < ends.get(i))
                return false;
        }
        return true;
    }

    private boolean isEndOk(double end) {
        for (int i = 0; i < starts.size(); i++) {
            if (end > starts.get(i) && end <= ends.get(i))
                return false;

        }
        return true;
    }


    //каждый урок  пара  чисел
    // при получении очередного урока проверить, что  внутри его границ нет других уроков
    private boolean isSpaceOk(Lesson lesson) {
        double start = lesson.getStart();
        double end = lesson.getEnd();

        for (int i = 0; i < starts.size(); i++) {
            if (starts.get(i) >= start && ends.get(i) <= end)
                return false;
        }
        return true;
    }


    private boolean addToSchedule(Lesson lesson) {
        if (isEndOk(lesson.end) && isStartOk(lesson.start) && isSpaceOk(lesson)) {
            //  if (isEndOk(lesson.end) && isStartOk(lesson.start)) {
            schedule.add(lesson);
            starts.add(lesson.start);
            ends.add(lesson.end);
            return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int lessonsQuantity = Integer.parseInt(reader.readLine());
            createSchedule(reader, lessonsQuantity);

        }
    }

    private static void createSchedule(BufferedReader reader, int lessonsQuantity) throws IOException {
        B b = new B();

        for (int i = 0; i < lessonsQuantity; i++) {
            final double[] hours = readList(reader);
            final boolean isAdded = b.addToSchedule(new Lesson(hours[0], hours[1]));
            if (isAdded)
                ++b.counter;
        }

        System.out.println(b.counter);

        Collections.sort(b.schedule);
        b.schedule.forEach(System.out::println);
    }


    private static double[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToDouble(Double::parseDouble).toArray();
    }
}
