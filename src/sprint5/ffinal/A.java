package sprint5.ffinal;

    /*
-- ПРИНЦИП РАБОТЫ --
Используемая структура данных :

-- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --


-- ВРЕМЕННАЯ СЛОЖНОСТЬ --


-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --

--ID успешной посылки--

     */

import java.io.*;

public class A {
    private Participant[] participantsHeap;


    public A(int size) {
        this.participantsHeap = new Participant[size];
    }

    public void insert(Participant participant, int index) {
        participantsHeap[index] = participant;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            final int participantNumber = Integer.parseInt(reader.readLine());

            final A heap = new A(participantNumber);

            for (int i = 0; i < participantNumber; i++) {
                final String[] currentParticipant = reader.readLine().trim().split(" ");
                heap.insert(new Participant(currentParticipant[0], Integer.parseInt(currentParticipant[1]),
                        Integer.parseInt(currentParticipant[2])), i);
            }
        }
    }

    public static class Participant implements Comparable<Participant> {

        private final String name;
        private final int score;
        private final int penalty;

        public Participant(String name, int score, int penalty) {
            this.name = name;
            this.score = score;
            this.penalty = penalty;
        }

        public String getName() {
            return name;
        }

        @Override
        //TODO доделать поправить -1 как было в ревью во втором спринте
        public int compareTo(Participant s) {
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
}
