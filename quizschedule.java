package quizretakes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.time.*;
import java.lang.Long;
import java.lang.String;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;


/**
 * @author Jeff Offutt
 *         Date: January, 2019
 *
 * Wiring the pieces together:
 *    quizschedule.java -- Servlet entry point for students to schedule quizzes
 *    quizReader.java -- reads XML file and stores in quizzes.
 Used by quizschedule.java
 *    quizzes.java -- A list of quizzes from the XML file
 *                    Used by quizschedule.java
 *    quizBean.java -- A simple quiz bean
 *                      Used by quizzes.java and readQuizzesXML.java
 *    retakesReader.java -- reads XML file and stores in retakes.
 Used by quizschedule.java
 *    retakes.java -- A list of retakes from the XML file
 *                    Used by quizschedule.java
 *    retakeBean.java -- A simple retake bean
 *                      Used by retakes.java and readRetakesXML.java
 *    apptBean.java -- A bean to hold appointments
 * 
 *    quizzes.xml -- Data file of when quizzes were given
 *    retakes.xml -- Data file of when retakes are given
 */

public class quizschedule
{
  // Data files
  // location maps to /webapps/offutt/WEB-INF/data/ from a terminal window.
  // These names show up in all servlets
  //private static final String dataLocation    = "/var/www/CS/webapps/offutt/WEB-INF/data/";
  private static final String dataLocation    = "/home/goodkindofwyrd/Documents/College/Classes/SWE_437/quizretakes/";
  static private final String separator = ",";
  private static final String courseBase   = "course";
  private static final String quizzesBase = "quiz-orig";
  private static final String retakesBase = "quiz-retakes";
  private static final String apptsBase   = "quiz-appts";
  
  // Filenames to be built from above and the courseID parameter
  private static String courseFileName;
  private static String quizzesFileName;
  private static String retakesFileName;
  private static String apptsFileName;
  
  // Passed as parameter and stored in course.xml file (format: "swe437")
  private static String courseID;
  // Stored in course.xml file, default 14
  // Number of days a retake is offered after the quiz is given
  private static int daysAvailable = 14;
  
  // To be set by getRequestURL()
  
  
  public static void main(String [] args)
  {
    String tOrS;
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Hello!\nAre you a 'Professor' or 'Student'?\n");
    
    if(args.length == 0)
    {
      tOrS = scanner.next();
    }
    else
    {
      tOrS = args[0];
    }
    
    if(tOrS.toLowerCase().equals("professor"))
    {
      System.out.println("Welcome!\n");
      System.out.println("Enter course Id for class you wish to see retakes for: ");
      if(args.length == 0)
      {
        courseID = scanner.next();
      }
      else
      {
        courseID = args[1];
      }
      int hold = 0;
      
      System.out.println("Here are a list of options:\n" +
                         "Option 1: Schedule new quiz\n" +
                         "Option 2: Schedule new retake\n" +
                         "Option 3: Display retakes and quiz schedules\n" +
                         "Option 4: Exit program\n\n");
      if(args.length == 0)
      {
        hold = scanner.nextInt();
      }
      else
      {
        tOrS = args[2];
      }
      
      if(hold == 1)
      {
        Professor.scheduleQuiz();
      }
      if(hold == 2)
      {
        Professor.scheduleRetake();
      }
      if(hold == 3)
      {
        Professor.displayRetakes(courseID);
      }
      if(hold == 4)
      {
        System.out.println("Good bye, Have a good day!\n");
        return;
      }
      
    } 
    else if(tOrS.toLowerCase().equals("student"))
    {
      System.out.println("Input the course ID (Format:swe437): ");
      courseID = scanner.next();
      
      Student stu = new Student();
      
      stu.doGet(courseID);
    }
  }
  
  protected static void doPost (String courseID, String studentName, String[] allIDs) 
  {
    {
      // No saving if IOException
      boolean IOerrFlag = false;
      String IOerrMessage = "";
      
      // Filename to be built from above and the courseID   
      Student stu = new Student();
      stu.doGet(courseID);
    }
  }
}