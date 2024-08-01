package sprint7;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Lesson {
    //class Lesson implements Comparable<Lesson> {
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

//    @Override
//    public int compareTo(@NotNull Lesson lesson) {
//        return  Double.compare(start, lesson.start);
//    }
}

public class B {

    List<Lesson> schedule;
    int counter;
    Lesson previousLesson;

    public B() {
        this.schedule = new ArrayList<>();
        counter = 0;
        previousLesson = null;
    }

    private boolean addToSchedule(Lesson lesson) {
        if (schedule.isEmpty()) {
            schedule.add(lesson);
            previousLesson = lesson;
            return true;
        } else if (lesson.end > previousLesson.end && lesson.start >= previousLesson.end) {
            schedule.add(lesson);
            previousLesson = lesson;
            return true;
        } else if (lesson.end < previousLesson.start) {
            schedule.add(lesson);
            previousLesson = lesson;
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
