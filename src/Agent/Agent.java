package Agent;

public abstract class Agent implements Runnable {

    private final String NAME;
    private final int ID;

    protected Agent(String name, int id) {
        this.NAME = name;
        this.ID = id;
    }

    public String getName() {
        return this.NAME;
    }

    public int getID() {
        return this.ID;
    }

    public void run() {
        System.out.println(this);
    }

    public abstract String toString();
}
