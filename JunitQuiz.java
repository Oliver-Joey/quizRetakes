import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.*;
import java.time.*;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JunitQuiz {
  
  @Test
  public void testPrintAll() {
    
    //SETUP: create a new print stream to view the printed schedule from printQuizScheduleForm()
    //*would've used @Before setUp() method but @Before wasn't working for some reason...
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    LocalDate today  = LocalDate.now();
    LocalDate endDay = today.plusDays(new Long(21));
    
    quizretakes.quizzes quizList = new quizretakes.quizzes();
    quizretakes.retakes retakesList = new quizretakes.retakes();
    quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    
    //ASSUMPTIONS: these 3 paramemters should never be null
    assumeTrue(quizList != null);
    assumeTrue(retakesList != null);
    assumeTrue(course != null);
    
    //CONTROLLABILITY: -----------------------------------------------------------------------------
    //For controllability, it is just a matter of copying and pasting inputs from test to test.
    //To create new inputs, we simply add new quizzes and retakes of different times to the list below 
    
    //QUIZLIST: Sets the list of quizzes taken
    quizretakes.quizBean q1 = new quizretakes.quizBean (1234, 3, 1, 12, 30);
    quizList.addQuiz(q1);
    
    //RETAKESLIST: Sets the list of available retakes
    quizretakes.retakeBean r1 = new quizretakes.retakeBean (1234, "Robinson", 3, 1, 12, 30);
    retakesList.addRetake(r1);
    //---------------------------------------------------------------------------------------------
    
    //Calls printQuizScheduleForm
    quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course);
    
    //Compare the printed schedule with what we're expecting from the test inputs
    String expected =("\n\n******************************************************************************\n");
    expected +=("GMU quiz retake scheduler for class " + course.getCourseTitle() + "\n");
    expected +=("******************************************************************************\n\n\n");
    expected +=("You can sign up for quiz retakes within the next two weeks. \n");
    expected +=("Enter your name (as it appears on the class roster), \n");
    expected +=("then select which date, time, and quiz you wish to retake from the following list.\n\n");
    expected +=(("Today is ")+(today.getDayOfWeek()) + ", " + today.getMonth() + " " + today.getDayOfMonth() +"\n");
    expected +=("Currently scheduling quizzes for the next two weeks, until ");
    expected +=((endDay.getDayOfWeek()) + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() +"\n");
    
    //Build specific information for a retake
    LocalDate retakeDay1 = r1.getDate();
    String retake1 = ("RETAKE: " + retakeDay1.getDayOfWeek() + ", " + retakeDay1.getMonth() + " " +
                      retakeDay1.getDayOfMonth() + ", at " + r1.timeAsString() + " in " +
                      r1.getLocation() +("\n    1) Quiz 1234 from FRIDAY, MARCH 1\n\n"));
    expected += retake1;
    
    
    //Clean up the stream
    System.out.flush();
    System.setOut(system_output);
    
    //OBSERVABILITY: -------------------------------------------------
    //Through output.toString() we can compare it to our expected value
    
    //ASSERTION: actual output is the same as expected output
    //Print output to screen regardless of pass/fail
    try{
      assertTrue(expected.equals(output.toString()));
      
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
    }catch(AssertionError e){
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
      throw e;
    }
    //----------------------------------------------------------------
  }
  
  @Test
  public void testPrintRetakesinRange() {
    
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    LocalDate today  = LocalDate.now();
    LocalDate endDay = today.plusDays(new Long(21));
    
    quizretakes.quizzes quizList = new quizretakes.quizzes();
    quizretakes.retakes retakesList = new quizretakes.retakes();
    quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    
    //ASSUMPTIONS: these 3 paramemters should never be null
    assumeTrue(quizList != null);
    assumeTrue(retakesList != null);
    assumeTrue(course != null);
    
    //CONTROLLABILITY: -----------------------------------------------------------------------------
    //For controllability, it is just a matter of copying and pasting inputs from test to test.
    //To create new inputs, we simply add new quizzes and retakes of different times to the list below 
    
    //QUIZLIST: Sets the list of quizzes taken
    quizretakes.quizBean q1 = new quizretakes.quizBean (0001, 2, 7, 10, 30);
    quizretakes.quizBean q2 = new quizretakes.quizBean (0002, 2, 21, 10, 30);
    quizretakes.quizBean q3 = new quizretakes.quizBean (0003, 2, 28, 10, 30);
    quizList.addQuiz(q1);
    quizList.addQuiz(q2);
    quizList.addQuiz(q3);
    
    //RETAKESLIST: Sets the list of available retakes
    quizretakes.retakeBean r1 = new quizretakes.retakeBean (1234, "Robinson", 3, 1, 12, 30);
    quizretakes.retakeBean r2 = new quizretakes.retakeBean (5678, "Fenwick", 3, 9, 4, 00);
    quizretakes.retakeBean r3 = new quizretakes.retakeBean (9012, "Engineering", 3, 11, 10, 30);
    quizretakes.retakeBean r4 = new quizretakes.retakeBean (1010, "Engineering", 4, 1, 1, 30);
    retakesList.addRetake(r1);
    retakesList.addRetake(r2);
    retakesList.addRetake(r3);
    retakesList.addRetake(r4);
    //----------------------------------------------------------------------------------------------
    
    //Calls printQuizScheduleForm
    quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course);
    
    //Compare the printed schedule with what we're expecting from the test inputs
    String expected =("\n\n******************************************************************************\n");
    expected +=("GMU quiz retake scheduler for class " + course.getCourseTitle() + "\n");
    expected +=("******************************************************************************\n\n\n");
    expected +=("You can sign up for quiz retakes within the next two weeks. \n");
    expected +=("Enter your name (as it appears on the class roster), \n");
    expected +=("then select which date, time, and quiz you wish to retake from the following list.\n\n");
    expected +=(("Today is ")+(today.getDayOfWeek()) + ", " + today.getMonth() + " " + today.getDayOfMonth() +"\n");
    expected +=("Currently scheduling quizzes for the next two weeks, until ");
    expected +=((endDay.getDayOfWeek()) + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() +"\n");
    
    LocalDate retakeDay1 = r1.getDate();
    String retake1 = ("RETAKE: " + retakeDay1.getDayOfWeek() + ", " + retakeDay1.getMonth() + " " +
                      retakeDay1.getDayOfMonth() + ", at " + r1.timeAsString() + " in " +
                      r1.getLocation() +("\n    1) Quiz 2 from THURSDAY, FEBRUARY 21\n    2) Quiz 3 from THURSDAY, FEBRUARY 28\n"));
    expected += retake1;
    
    LocalDate retakeDay2 = r2.getDate();
    String retake2 = ("RETAKE: " + retakeDay2.getDayOfWeek() + ", " + retakeDay2.getMonth() + " " +
                      retakeDay2.getDayOfMonth() + ", at " + r2.timeAsString() + " in " +
                      r2.getLocation() +("\n    3) Quiz 3 from THURSDAY, FEBRUARY 28\n"));
    expected += retake2;
    
    LocalDate retakeDay3 = r3.getDate();
    String retake3 = ("RETAKE: " + retakeDay3.getDayOfWeek() + ", " + retakeDay3.getMonth() + " " +
                      retakeDay3.getDayOfMonth() + ", at " + r3.timeAsString() + " in " +
                      r3.getLocation() +("\n    4) Quiz 3 from THURSDAY, FEBRUARY 28\n\n"));
    expected += retake3;
    
    //Clean up the stream
    System.out.flush();
    System.setOut(system_output);
    
    //OBSERVABILITY: -------------------------------------------------
    //Through output.toString() we can compare it to our expected value
    
    //ASSERTION: actual output is the same as expected output
    //Print output to screen regardless of pass/fail
    try{
      assertTrue(expected.equals(output.toString()));
      
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
    }catch(AssertionError e){
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
      throw e;
    }
    //----------------------------------------------------------------
  }
  
  @Test
  public void testPrintQuizzesInRange() {
    
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    LocalDate today  = LocalDate.now();
    LocalDate endDay = today.plusDays(new Long(21));
    
    quizretakes.quizzes quizList = new quizretakes.quizzes();
    quizretakes.retakes retakesList = new quizretakes.retakes();
    quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    
    //ASSUMPTIONS: these 3 paramemters should never be null
    assumeTrue(quizList != null);
    assumeTrue(retakesList != null);
    assumeTrue(course != null);
    
    //CONTROLLABILITY: -----------------------------------------------------------------------------
    //For controllability, it is just a matter of copying and pasting inputs from test to test.
    //To create new inputs, we simply add new quizzes and retakes of different times to the list below 
    
    //QUIZLIST: Sets the list of quizzes taken
    quizretakes.quizBean q1 = new quizretakes.quizBean (0001, 2, 7, 10, 30);
    quizretakes.quizBean q2 = new quizretakes.quizBean (0002, 2, 21, 10, 30);
    quizretakes.quizBean q3 = new quizretakes.quizBean (0003, 2, 28, 10, 30);
    quizretakes.quizBean q4 = new quizretakes.quizBean (0004, 4, 28, 10, 30);
    quizList.addQuiz(q1);
    quizList.addQuiz(q2);
    quizList.addQuiz(q3);
    quizList.addQuiz(q4);
    
    //RETAKESLIST: Sets the list of available retakes
    quizretakes.retakeBean r1 = new quizretakes.retakeBean (1234, "Robinson", 3, 1, 12, 30);
    quizretakes.retakeBean r2 = new quizretakes.retakeBean (5678, "Fenwick", 3, 9, 4, 00);
    quizretakes.retakeBean r3 = new quizretakes.retakeBean (9012, "Engineering", 3, 11, 10, 30);
    quizretakes.retakeBean r4 = new quizretakes.retakeBean (1010, "Engineering", 4, 1, 1, 30);
    retakesList.addRetake(r1);
    retakesList.addRetake(r2);
    retakesList.addRetake(r3);
    retakesList.addRetake(r4);
    //----------------------------------------------------------------------------------------------
    
    //Calls printQuizScheduleForm
    quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course);
    
    //Compare the printed schedule with what we're expecting from the test inputs
    String expected =("\n\n******************************************************************************\n");
    expected +=("GMU quiz retake scheduler for class " + course.getCourseTitle() + "\n");
    expected +=("******************************************************************************\n\n\n");
    expected +=("You can sign up for quiz retakes within the next two weeks. \n");
    expected +=("Enter your name (as it appears on the class roster), \n");
    expected +=("then select which date, time, and quiz you wish to retake from the following list.\n\n");
    expected +=(("Today is ")+(today.getDayOfWeek()) + ", " + today.getMonth() + " " + today.getDayOfMonth() +"\n");
    expected +=("Currently scheduling quizzes for the next two weeks, until ");
    expected +=((endDay.getDayOfWeek()) + ", " + endDay.getMonth() + " " + endDay.getDayOfMonth() +"\n");
    
    LocalDate retakeDay1 = r1.getDate();
    String retake1 = ("RETAKE: " + retakeDay1.getDayOfWeek() + ", " + retakeDay1.getMonth() + " " +
                      retakeDay1.getDayOfMonth() + ", at " + r1.timeAsString() + " in " +
                      r1.getLocation() +("\n    1) Quiz 2 from THURSDAY, FEBRUARY 21\n    2) Quiz 3 from THURSDAY, FEBRUARY 28\n"));
    expected += retake1;
    
    LocalDate retakeDay2 = r2.getDate();
    String retake2 = ("RETAKE: " + retakeDay2.getDayOfWeek() + ", " + retakeDay2.getMonth() + " " +
                      retakeDay2.getDayOfMonth() + ", at " + r2.timeAsString() + " in " +
                      r2.getLocation() +("\n    3) Quiz 3 from THURSDAY, FEBRUARY 28\n"));
    expected += retake2;
    
    LocalDate retakeDay3 = r3.getDate();
    String retake3 = ("RETAKE: " + retakeDay3.getDayOfWeek() + ", " + retakeDay3.getMonth() + " " +
                      retakeDay3.getDayOfMonth() + ", at " + r3.timeAsString() + " in " +
                      r3.getLocation() +("\n    4) Quiz 3 from THURSDAY, FEBRUARY 28\n\n"));
    expected += retake3;
    
    //Clean up the stream
    System.out.flush();
    System.setOut(system_output);
    
    //OBSERVABILITY: -------------------------------------------------
    //Through output.toString() we can compare it to our expected value
    
    //ASSERTION: actual output is the same as expected output
    //Print output to screen regardless of pass/fail
    try{
      assertTrue(expected.equals(output.toString()));
      
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
    }catch(AssertionError e){
      System.out.println("expected:" + expected);
      System.out.println("actual:" + output.toString());
      
      throw e;
    }
    //----------------------------------------------------------------
  }
}