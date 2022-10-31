package Agent;

public class Director extends Agent {

    protected STATE State;

    private final int NUM_OF_ROUNDS = 3;
    private int Round = 1;

    public Director() {
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
        System.out.println("\t The Director starts the round");
        int inside = Students_Inside.intValue();
        if (inside == 0) {
            State = STATE.OUTSIDE;
        } else if (inside >= Students_For_Party) {
            State = STATE.INSIDE;
        } else {
            State = STATE.WAITING;
        }
    }

    public void critical_section() {
        if (State==STATE.OUTSIDE){
            System.out.println("\t The Director sees nobody in the study room");
        } else if(State==STATE.WAITING){
            System.out.println("\t The Director waits to enter. Doesn't disturb the students");
            // TO DO
        } else {
            System.out.println("\t The Director enters the study room: THE PARTY IS OVER!");
            cleanStudyRoom();
        }
    }

    public void post_protocol() {
        System.out.println("\t The Director ends the round " + Round + " of " + NUM_OF_ROUNDS);
        Round++;
    }

    protected STATE getStatus() {
        return this.State;
    }

    private void cleanStudyRoom(){

    }

}