//JunitQuiz.java


import org.junit.*;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;

import java.util.*;
import java.time.*;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith (Theories.class)

public class JunitQuiz extends TestCase 
{
  
  //Fake datapoint
  @DataPoint public static int input1 = 1;
  
  @Theory public void test_printQuizScheduleForm(int input1)
  {
    //Assumptions
    
    //Retrieves System.out output to use for later testing
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    //Sets date for the retakes
    LocalDate today  = LocalDate.now();
    LocalDate endDay = today.plusDays(new Long(14));
    
    
    //QUIZLIST: Sets the list of quizzes taken
    quizretakes.quizzes quizList = new quizretakes.quizzes();
    quizretakes.quizBean q1 = new quizretakes.quizBean (11, 3, 1, 11, 15);
    quizretakes.quizBean q2 = new quizretakes.quizBean (22, 3, 1, 6, 20);
    quizretakes.quizBean q3 = new quizretakes.quizBean (33, 3, 1, 10, 25);
    quizList.addQuiz(q1);
    quizList.addQuiz(q2);
    quizList.addQuiz(q3);
    
    //RETAKESLIST: Sets the list of available retakes
    quizretakes.retakes retakesList = new quizretakes.retakes() ;
    quizretakes.retakeBean r1 = new quizretakes.retakeBean (1, "Robinson", 3, 1, 12, 30);
    quizretakes.retakeBean r2 = new quizretakes.retakeBean (2, "Fenwick", 3, 2, 7, 30);
    quizretakes.retakeBean r3 = new quizretakes.retakeBean (3, "Engineering", 3, 3, 11, 30);
    retakesList.addRetake(r1);
    retakesList.addRetake(r2);
    retakesList.addRetake(r3);
    
    //Sets up the course
    quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    
    //Testing printQuizScheduleForm
    quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course);
    
    //After printQuizScheduleForm prints to System.out, stores and prints out what was printed to System.out
    System.out.flush();
    System.setOut(system_output);
    System.out.println("TESTING OUTPUT: " + output.toString());
    
    //String samplestring = "
    
    //assertTrue(output.toString().equals(samplestring));
    
    //Assertions
    assertTrue(quizList != null);
    assertTrue(retakesList != null);
    assertTrue(course != null);
    
  }
}