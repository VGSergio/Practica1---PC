package Agent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Agent implements Runnable {

    protected static Semaphore Permits;
    protected static Semaphore Door = new Semaphore(1);
    protected static AtomicInteger Students_Inside = new AtomicInteger(0);
    protected static int Num_Students;
    protected static int Students_For_Party;

    protected enum STATE {
        OUTSIDE, WAITING, INSIDE
    };

    public static void setGlobalVariables(int num_students, int students_for_party) {
        Num_Students = num_students;
        Students_For_Party = students_for_party;
        Permits = new Semaphore(num_students, true);
    }

    protected void SleepThread(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    public abstract void run();

    public abstract void pre_protocol();

    public abstract void critical_section() throws InterruptedException;

    public abstract void post_protocol();
}
