import java.util.Scanner;

import Agent.*;

public class App {

    public static int Num_Students;
    public static int Students_For_Party;
    public static Thread[] threads;

    public static void main(String[] args) throws Exception {
        System.out.println("STUDY ROOM SIMULATION");

        // Gets amounts
        getStudentsData();

        // Creates the threads
        createThreads();

        // Starts the threads
        startThreads();

        // Joins the threads
        joinThreads();
    }

    static private void getStudentsData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Amount of students: ");
        Num_Students = Integer.parseInt(scanner.nextLine());

        System.out.print("Max amount of students: ");
        Students_For_Party = Integer.parseInt(scanner.nextLine());

        scanner.close();
    }

    static private void createThreads() {
        // Initialize threads array
        threads = new Thread[Num_Students + 1];

        // Creates the director
        Director director = new Director(1);

        // Create Director Thread
        Thread d = new Thread(director, "director");
        threads[0] = d;

        // Create students threads
        for (int i = 1; i <= Num_Students; i++) { // i <= Num_Students due to the director
            String name = "Student" + i;
            Student student = new Student(name, i);
            Thread s = new Thread(student, name);
            threads[i] = s;
        }
    }

    static private void startThreads() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    static private void joinThreads() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
}
