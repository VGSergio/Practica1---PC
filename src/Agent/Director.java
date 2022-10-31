package Agent;

import java.util.Set;

public class Director extends Agent {

    private static final Set<String> VALID_STATUS = Set.of("FORA", "ESPERANT", "DINS");

    private String Status;

    public Director(int id) {
        super(id);
        this.Status = "FORA";
    }

    public String getStatus() {
        return this.Status;
    }

    public void getStatus(String Status) {
        if (VALID_STATUS.contains(Status)) {
            this.Status = Status;
        } else {
            System.err.println("Invalid director status" + Status);
        }
    }

    public String toString() {
        return "\t Director, ID: " + this.getID();
    }
}