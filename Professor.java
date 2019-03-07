package quizretakes;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;


public class Professor {
    private static final String apptsBase   = "quiz-appts";
    private static final String dataLocation  = "/home/goodkindofwyrd/Documents/College/Classes/SWE_437/quizretakes/";

    public Professor()
    {
    }

    public static void displayRetakes(String courseID) {
        Scanner scan = new Scanner(System.in);
        String in = dataLocation + apptsBase + "-" + courseID + ".txt";
        File input = new File(in);

        try {
            System.out.println("Here is the current schedule of quiz retakes:\n");
            Scanner fileScan = new Scanner(input);
            int count = 1;
            while (fileScan.hasNextLine()) {
                String retake = fileScan.nextLine();
                System.out.println(count + ". " + retake);
                count++;
            }
            System.out.println("\nHave a nice day!");
        } catch (FileNotFoundException e){
            System.out.println("Oops I couldn't read the file...");
        }
    }

    public static void scheduleQuiz() {
        Scanner scan = new Scanner(System.in);
        String newQuiz = "";

        System.out.print("What\'s the quiz ID?\n> ");
        String quizID = scan.nextLine();
        newQuiz += ("quizID: " + quizID + ", ");

        System.out.print("What\'s the quiz date?\n> ");
        String date = scan.nextLine();
        newQuiz += ("date: " + date + ", ");

        System.out.print("What\'s the quiz time?\n> ");
        String time = scan.nextLine();
        newQuiz += ("time: " + time + ", ");

        System.out.print("What\'s the quiz location?\n> ");
        String location = scan.nextLine();
        newQuiz += ("location: " + location);

        System.out.println("\nAll set! Here is your new quiz:\n" + newQuiz);

        writeToFile("quizzes", newQuiz);
    }

    public static void scheduleRetake() {
        Scanner scan = new Scanner(System.in);
        String newRetake = "";

        System.out.print("What\'s the retake ID?\n> ");
        String retakeID = scan.nextLine();
        newRetake += ("quizID: " + retakeID + ", ");

        System.out.print("What\'s the retake location?\n> ");
        String location = scan.nextLine();
        newRetake += ("location: " + location + ", ");

        System.out.print("What\'s the retake month?\n> ");
        String month = scan.nextLine();
        newRetake += ("month: " + month + ", ");

        System.out.print("What\'s the retake day?\n> ");
        String day = scan.nextLine();
        newRetake += ("day: " + day + ", ");

        System.out.print("What\'s the retake hour?\n> ");
        String hour = scan.nextLine();
        newRetake += ("hour: " + hour + ", ");

        System.out.print("What\'s the retake minute?\n> ");
        String minute = scan.nextLine();
        newRetake += ("minute: " + minute);

        System.out.println("\nAll set! Here is your new retake:\n" + newRetake);

        //write to file
        writeToFile("retakes", newRetake);
    }

    public static void writeToFile(String form, String data) {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter((form+".txt")));
            out.write(data);
            out.close();
        } catch (IOException e){
            System.out.println("Oops I couldn't find " + form);
        }
    }

    public static void readFile(String form) {
        System.out.println("\nReading " + form + ":");
        int count = 0;

        try {
            Scanner scan = new Scanner(new File((form+".txt")));
            while (scan.hasNextLine()) {
                String retake = scan.nextLine();
                System.out.println(count + ": " + retake);
                count++;
            }
            System.out.println("\nReading complete.");
        } catch (FileNotFoundException e){
            System.out.println("Oops I couldn't read " + form);
        }
    }
}
