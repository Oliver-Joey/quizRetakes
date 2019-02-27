//JunitQuiz.java

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.*;
import java.time.*;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class JunitQuiz 
{
  //Testing printQuizScheduleForm
  @Test
  public void testPrintQuizzesInRange() 
  {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    LocalDate today  = LocalDate.now();
    LocalDate endDay = today.plusDays(new Long(21));
    
    quizretakes.quizzes quizList = new quizretakes.quizzes();
    quizretakes.retakes retakesList = new quizretakes.retakes();
    quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    
    assumeTrue(quizList != null);
    assumeTrue(retakesList != null);
    assumeTrue(course != null);
    
    //QUIZLIST: Sets the list of quizzes taken
    quizretakes.quizBean q1 = new quizretakes.quizBean (1234, 3, 1, 12, 30);
    quizList.addQuiz(q1);
    
    //RETAKESLIST: Sets the list of available retakes
    quizretakes.retakeBean r1 = new quizretakes.retakeBean (1234, "Robinson", 3, 1, 12, 30);
    retakesList.addRetake(r1);
    
    //Calls printQuizScheduleForm
    quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course);
    
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
                      r1.getLocation() +("\n    1) Quiz 1234 from FRIDAY, MARCH 1\n\n"));
    expected += retake1;
    
    System.out.flush();
    System.setOut(system_output);
    
    //Asserts that actual output is the same as expected output
    assertTrue(expected.equals(output.toString()));
  } 
}