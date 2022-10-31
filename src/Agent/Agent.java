package Agent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Agent implements Runnable {

    protected static Semaphore StudyRoom = new Semaphore(1);
    protected static AtomicInteger Students_Inside = new AtomicInteger(0);
    protected static AtomicInteger Students_Count = new AtomicInteger(0);
    protected static int Num_Students;
    protected static int Students_For_Party;
    protected static Director Director;

    private final int ID;

    protected Agent(int id) {
        this.ID = id;
    }

    public static void setGlobalVariables(int num_students, int students_for_party, Director director) {
        Num_Students = num_students;
        Students_For_Party = students_for_party;
        Director = director;
    }

    protected int getID() {
        return this.ID;
    }

    public abstract void run();

    public abstract void pre_protocol();

    public abstract void critical_section();

    public abstract void post_protocol();

    public abstract String toString();
}
