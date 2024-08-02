package sprint7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Lesson implements Comparable<Lesson> {
    double start;
    double end;

    public Lesson(double start, double end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("0.##");
        return format.format(start) + " " + format.format(end);
    }

    @Override
    public int compareTo(Lesson lesson) {
        if (this.end < lesson.end)
            return -1;
        else if (this.end > lesson.end)
            return 1;
        else {
            if (this.start < lesson.start)
                return -1;
            else if (this.start > lesson.start)
                return 1;

        }
        return 0;
    }
}


public class B {

    ArrayList<Lesson> schedule;
    ArrayList<Lesson> lessons;
    int counter;
    Lesson previousLesson;

    public B() {
        this.schedule = new ArrayList<>();
        lessons = new ArrayList<>();
        counter = 0;
        previousLesson = null;
    }

    private void readAndSaveAllLessons(BufferedReader reader, int lessonsQuantity) throws IOException {
        for (int i = 0; i < lessonsQuantity; i++) {
            final double[] hours = readList(reader);
            lessons.add(new Lesson(hours[0], hours[1]));
        }
    }

    private void createSchedule(List<Lesson> lessons) {
        for (Lesson currLesson : lessons) {
            if (schedule.isEmpty()) {
                schedule.add(currLesson);
                previousLesson = currLesson;
                ++counter;
            } else if (currLesson.start >= previousLesson.end) {
                schedule.add(currLesson);
                previousLesson = currLesson;
                ++counter;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int lessonsQuantity = Integer.parseInt(reader.readLine());

            B b = new B();
            b.readAndSaveAllLessons(reader, lessonsQuantity);
            Collections.sort(b.lessons);

            b.createSchedule(b.lessons);

            System.out.println(b.counter);

            Collections.sort(b.schedule);
            b.schedule.forEach(System.out::println);

        }
    }


    private static double[] readList(BufferedReader reader) throws IOException {
        return Arrays.stream(reader.readLine().split(" "))
                .mapToDouble(Double::parseDouble).toArray();
    }
}
