package Agent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Agent implements Runnable {

    protected static Semaphore Permits; // Permits available to enter the study room
    protected static Semaphore Door = new Semaphore(1); // Door occupied semaphore
    protected static AtomicInteger Students_Inside = new AtomicInteger(0); // Students inside counter
    protected static int Num_Students; // Students that will enter the study room
    protected static int Students_For_Party; // Students needed to start the party

    // States of the director
    protected enum STATE {
        OUTSIDE, WAITING, INSIDE
    };

    /**
     * Function to set or initialize shared variables
     * 
     * @param num_students
     * @param students_for_party
     */
    public static void setGlobalVariables(int num_students, int students_for_party) {
        Num_Students = num_students;
        Students_For_Party = students_for_party;
        Permits = new Semaphore(num_students, true);
    }

    /**
     * Function to make a thread wait a time amount of time
     * 
     * @param time
     */
    protected void SleepThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public abstract void run();

    public abstract void pre_protocol();

    public abstract void critical_section();

    public abstract void post_protocol();
}
