package sprint3.training;

public class Student implements Comparable<Student> {

    private final String name;
    final int score;
    final int penalty;

    public Student(String name, int score, int penalty) {
        this.name = name;
        this.score = score;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student s) {
        if (this.score > s.score) {
            return -1;
        }
        if (this.score < s.score) {
            return 1;
        } else {
            if (this.penalty < s.penalty) {
                return -1;
            }
            if (this.penalty > s.penalty) {
                return 1;
            } else {
                return this.name.compareTo(s.getName());
            }
        }
    }
}
