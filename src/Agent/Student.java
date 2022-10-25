package Agent;

public class Student extends Agent {

    public Student(String name, int id) {
        super(name, id);
    }

    public String toString() {
        return "Student: " + this.getName() + ", ID: " + this.getID();
    }
}
