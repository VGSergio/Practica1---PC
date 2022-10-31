package Agent;

public class Director extends Agent {

    static private final int NUM_OF_ROUNDS = 3;

    static public enum STATE {
        OUTSIDE, WAITING, INSIDE
    }

    private int Round = 1;
    private STATE State;

    public Director(int id) {
        super(id);
        this.State = STATE.OUTSIDE;
    }

    public void run() {
        while (Round <= NUM_OF_ROUNDS) {
            pre_protocol();
            critical_section();
            post_protocol();
        }
    }

    public void pre_protocol() {
        System.out.println("\t Mr. Director starts the round");
    }

    public void critical_section() {

    }

    public void post_protocol() {
        System.out.println("\t The Director ends the round " + Round + " of " + NUM_OF_ROUNDS);
        Round++;
    }

    protected STATE getStatus() {
        return this.State;
    }

    public String toString() {
        return "\t Director, ID: " + this.getID();
    }
}