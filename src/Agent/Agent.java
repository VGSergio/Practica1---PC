package Agent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Agent implements Runnable {

    protected static Semaphore Permits;
    protected static Semaphore Door = new Semaphore(1);
    protected static AtomicInteger Students_Inside = new AtomicInteger(0);
    protected static int Num_Students;
    protected static int Students_For_Party;
    protected static Director Director;

    protected enum STATE {
        OUTSIDE, WAITING, INSIDE
    };

    public static void setGlobalVariables(int num_students, int students_for_party, Director director) {
        Num_Students = num_students;
        Students_For_Party = students_for_party;
        Director = director;
        Permits = new Semaphore(num_students, true);
    }

    public abstract void run();

    public abstract void pre_protocol();

    public abstract void critical_section() throws InterruptedException;

    public abstract void post_protocol();
}
