package quizretakes;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Professor {

    public Professor()
    {
    }
    
    protected static void displayRetakes() {
        Scanner scan = new Scanner(System.in);
        File input = new File("input.txt");

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
