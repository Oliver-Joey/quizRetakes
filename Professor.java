package quizretakes;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Professor {

<<<<<<< HEAD
    private static final String apptsBase   = "quiz-appts";
    private static final String dataLocation  = "/home/goodkindofwyrd/Documents/College/Classes/SWE_437/quizretakes/";
	
=======
>>>>>>> baa75c531fb63c0761285042723a9cfcc04a1ccc
    public Professor()
    {
    }
    
<<<<<<< HEAD
    protected static void displayRetakes(String courseID) {
    	Scanner scan = new Scanner(System.in);
	String in = dataLocation + apptsBase + "-" + courseID + ".txt";
        File input = new File(in);
=======
    protected static void displayRetakes() {
        Scanner scan = new Scanner(System.in);
        File input = new File("input.txt");
>>>>>>> baa75c531fb63c0761285042723a9cfcc04a1ccc

        try {
	    System.out.println("\nWelcome!\nHere is the current schedule of quiz retakes:\n");
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
}
