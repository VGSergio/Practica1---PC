package Agent;

import java.util.Random;

public class Student extends Agent {

    private final String NAME;
    private final int STUDY_TIME;

    public Student(String name) {
        this.NAME = name;
        this.STUDY_TIME = new Random().nextInt(1000);
    }

    public void run() {
        pre_protocol();
        critical_section();
        post_protocol();
    }

    @Override
    public void pre_protocol() {
    }

    @Override
    public void critical_section() {
        try {
            Door.acquire();
            Permits.acquire();
            output();
            Door.release();
        } catch (

        InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void output() {
        int inside = Students_Inside.addAndGet(1);

        try {
            Thread.sleep(new Random().nextInt(10));
        } catch(Exception e) {}
        printStudent("enters", inside);
        if (inside < Students_For_Party) {
            System.out.println(this.NAME + " studies");
        } else {
            System.out.println(this.NAME + ": PARTY!!!!!");
            if (Director.State == STATE.WAITING) {
                System.out.println(this.NAME + ": Carefull, the Director is coming!!!!!!!!!");
            }
        }
    }

    @Override
    public void post_protocol() {
        try {
            Thread.sleep(STUDY_TIME);
            int inside = Students_Inside.addAndGet(-1);
            printStudent("leaves", inside);
            if (Director.State == STATE.WAITING) {
                Permits.release();
            }
            if (inside == 0) {
                if (Director.State == STATE.WAITING) {
                    System.out.println(this.NAME + ": goodbye Mr. Director, you're left alone");
                    Permits.acquire(Permits.availablePermits());
                }
                if (Director.State == STATE.INSIDE) {
                    System.out.println(this.NAME + ": goodbye Mr. Director, you're left alone");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printStudent(String status, int students_inside) {
        System.out.println(this.NAME + " " + status + " the study room, number of students: " + students_inside);
    }
}
