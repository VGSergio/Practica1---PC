package Agent;

public abstract class Agent implements Runnable {

    private final int ID;

    protected Agent(int id) {
        this.ID = id;
    }


    public int getID() {
        return this.ID;
    }

    public void run() {
        System.out.println(this);
    }

    public abstract String toString();
}
