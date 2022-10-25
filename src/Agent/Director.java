package Agent;

public class Director extends Agent {

    public Director(int id) {
        super("Director", id);
    }

    public String toString() {
        return "Director: " + this.getName() + ", ID: " + this.getID();
    }
}