package Agent;

import java.util.Random;

public class Student extends Agent {

    private final String NAME; // Student's name
    private final int STUDY_TIME; // Random time for leaving the study room
    private final Director Director; // Director

    /**
     * Student constructor
     * 
     * @param name
     * @param director
     */
    public Student(String name, Director director) {
        this.NAME = name;
        this.STUDY_TIME = new Random().nextInt(1000);
        this.Director = director;
    }

    /**
     * Student's thread run method
     */
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
        } catch (

        InterruptedException e) {
            e.printStackTrace();
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
                    System.out.println(
                            this.NAME + ": goodbye Mr. Director, you can enter if you want, no one is inside.");
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

    /**
     * Function that outputs the student's status depending on the study room status
     */
    private void output() {
        int inside = Students_Inside.addAndGet(1);

        printStudent("enters", inside);
        if (inside < Students_For_Party) {
            System.out.println(this.NAME + " studies");
            Door.release();
        } else {
            System.out.println(this.NAME + ": PARTY!!!!!");
            if (Director.State == STATE.WAITING) {
                System.out.println(this.NAME + ": Carefull, the Director is coming!!!!!!!!!");
                Director.State = STATE.INSIDE;
            } else {
                Door.release();
            }
        }
    }

    /**
     * Function that prints the state of the study room when the student enters it
     * 
     * @param status
     * @param students_inside
     */
    private void printStudent(String status, int students_inside) {
        System.out.println(this.NAME + " " + status + " the study room, number of students: " + students_inside);
    }
}
