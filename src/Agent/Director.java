package Agent;

import java.util.Random;

public class Director extends Agent {

    protected STATE State; // Director's state

    private final int NUM_OF_ROUNDS = 3; // Rounds that the director will make in the day
    private int Round = 1; // Round counter

    /**
     * Director constructor
     */
    public Director() {
        this.State = STATE.OUTSIDE;
    }

    /**
     * Director thread run method
     */
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
            Door.acquire();
        } catch (InterruptedException e) {
        }

        System.out.println("\t The Director starts the round");

        int inside = Students_Inside.intValue();

        if (inside == 0) {
            State = STATE.OUTSIDE;
            printStatus(State);
        } else if (inside < Students_For_Party) {
            try {
                Permits.acquire(Permits.availablePermits());
            } catch (InterruptedException e) {
            }
            Permits.release(Students_For_Party - inside);
            Door.release();

            State = STATE.WAITING;
            printStatus(State);

            while (Permits.availablePermits() != 0) {
            }

            clean();

            Permits.release(Num_Students);
        } else {
            clean();
        }
        Door.release();
    }

    public void post_protocol() {
        Round++;
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function executed when the students starts a party, the director stops the
     * party,
     * waits for everyone to leave and ends the round
     */
    public void clean() {
        SleepThread(3);
        State = STATE.INSIDE;
        printStatus(State);
        cleanStudyRoom();
        System.out.println("\t The Director ends the round " + Round + " of " + NUM_OF_ROUNDS);
    }

    /**
     * Function to make the director wait until all the students leave the study
     * room
     */
    private void cleanStudyRoom() {
        while (Students_Inside.get() > 0) {
        }
    }

    /**
     * Function to print the correspondig output depending on the Director's state
     * 
     * @param state
     */
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