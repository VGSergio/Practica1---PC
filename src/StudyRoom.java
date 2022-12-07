import java.util.Scanner;

import Agent.*;

public class StudyRoom {

    private static int Num_Students;
    private static int Students_For_Party;
    private static Thread[] Students;
    private static Thread Director;

    private static final int MIN_STUDENTS = 1;
    private static final int MIN_STUDENTS_FOR_PARTY = 2;

    public static void main(String[] args) {
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

    private static void getStudentsData() {
        Scanner scanner = new Scanner(System.in);
        Num_Students = 0;
        do {
            System.out.print("Amount of students: ");
            String scan = scanner.nextLine();
            // Check if there is text and if it's only numbers
            if (scan.matches("[0-9]+") && scan.length() > 0) {
                Num_Students = Integer.parseInt(scan);
                if (Num_Students < MIN_STUDENTS) {
                    System.err.println("Invalid value, the minimum amount of students is " + MIN_STUDENTS);
                }
            } else {
                System.err.println("Invalid value, try again");
            }
        } while (Num_Students < MIN_STUDENTS);

        Students_For_Party = 0;
        do {
            System.out.print("Max amount of students: ");
            String scan = scanner.nextLine();
            // Check if there is text and if it's only numbers
            if (scan.matches("[0-9]+") && scan.length() > 0) {
                Students_For_Party = Integer.parseInt(scan);
                if (Students_For_Party < MIN_STUDENTS_FOR_PARTY) {
                    System.err.println(
                            "Invalid value, the minimum amount of students for a party is " + MIN_STUDENTS_FOR_PARTY);
                }
            } else {
                System.err.println("Invalid value, try again");
            }
        } while (Students_For_Party < MIN_STUDENTS_FOR_PARTY);

        scanner.close();
    }

    private static void createThreads() {
        // Creates the director
        Director director = new Director();
        Agent.setGlobalVariables(Num_Students, Students_For_Party, director);

        // Create Director Thread
        Director = new Thread(director, "Director");

        // Initialize threads array
        Students = new Thread[Num_Students];

        // Create students threads
        for (int i = 0; i < Num_Students; i++) {
            String name = "Student " + (i+1);
            Student student = new Student(name);
            Thread s = new Thread(student, name);
            Students[i] = s;
        }
    }

    private static void startThreads() {
        Director.start();
        for (Thread student : Students) {
            student.start();
        }
    }

    private static void joinThreads() {
        try {
            Director.join();
            for (Thread thread : Students) {
                thread.join();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
