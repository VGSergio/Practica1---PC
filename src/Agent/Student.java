package Agent;

public class Student extends Agent {

    private final String NAME;

    public Student(int id, String name) {
        super(id);
        this.NAME = name;
    }

    public String getName(){
        return this.NAME;
    }

    public String toString() {
        return "Student: " + this.getName() + ", ID: " + this.getID();
    }
}
