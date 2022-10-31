package Agent;

import java.util.Random;

public class Student extends Agent {

    private final String NAME;
    private final int STUDY_TIME;

    public Student(String name) {
        this.NAME = name;
        this.STUDY_TIME = new Random().nextInt(1000);
    }

    public void run() {
        while (Students_Count.intValue() < Num_Students) {
            pre_protocol();
            critical_section();
            post_protocol();
        }
    }

    @Override
    public void pre_protocol() {
        
    }

    @Override
    public void critical_section() {
        Students_Count.getAndAdd(1);
        Students_Inside.getAndAdd(1);
    }

    @Override
    public void post_protocol() {
        System.out.println(this.NAME + "enters the study room, number of students: " + Students_Inside);
        if (Students_Inside.intValue() <= Students_For_Party) {
            System.out.println(this.NAME + "studies");
        } else {
            System.out.println(this.NAME + ": PARTY!!!!!");
        }
    }
}
