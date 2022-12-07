package Agent;

import java.util.Random;

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
    }

    public void critical_section() {
        try {
            Classroom.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\t The Director starts the round");

        int inside = Students_Inside.intValue();

        if (inside == 0) {
            State = STATE.OUTSIDE;
            printStatus(State);
            Classroom.release();
        } else if (inside < Students_For_Party) {
            State = STATE.WAITING;
            printStatus(State);
            Classroom.release();
        } else {
            State = STATE.INSIDE;
            printStatus(State);
            cleanStudyRoom();
            System.out.println("\t The Director ends the round " + Round + " of " + NUM_OF_ROUNDS);
            Classroom.release();
        }
    }

    public void post_protocol() {
        Round++;
    }

    private void cleanStudyRoom() {
        while (Students_Inside.get() > 0) {
        }
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printStatus(STATE state) {
        switch (state) {
            case OUTSIDE:
                System.out.println("\t The Director sees nobody in the study room");
                System.out.println("\t The Director ends the round " + Round + " of " + NUM_OF_ROUNDS);
                break;
            case WAITING:
                System.out.println("\t The Director waits to enter. Doesn't disturb the students");
                break;
            case INSIDE:
                System.out.println("\t The Director enters the study room: THE PARTY IS OVER!");
                break;
            default:
                System.err.print("Director -> Error at printStatus");
                break;
        }
    }

}