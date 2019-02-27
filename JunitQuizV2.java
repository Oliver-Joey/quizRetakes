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

public class JunitQuiz extends TestCase {

    //Sets up the course
    @Before
    public void setUp() {
        //Retrieves System.out output to use for later testing
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(output);
        PrintStream system_output = System.out;
        System.setOut(ps);

        //Sets date for the retakes
        LocalDate today  = LocalDate.now();
        LocalDate endDay = today.plusDays(new Long(14));

        quizretakes.quizzes quizList = new quizretakes.quizzes();
        quizretakes.retakes retakesList = new quizretakes.retakes();
        quizretakes.courseBean course = new quizretakes.courseBean("SWE437", "Software testing", "2 Weeks" , today, endDay, "Buchanan Hall");
    }

    //Testing printQuizScheduleForm
    @Test
    public void testPrintQuizzesInRange() {

        assumeTrue(quizList != null)
        assumeTrue(retakesList != null)
        assumeTrue(course != null)

        //QUIZLIST: Sets the list of quizzes taken
        quizretakes.quizBean q1 = new quizretakes.quizBean (1234, 1, 1, 1, 1);
        quizList.addQuiz(q1);

        //RETAKESLIST: Sets the list of available retakes
        quizretakes.retakeBean r1 = new quizretakes.retakeBean (1234, "Robinson", 3, 1, 12, 30);
        retakesList.addRetake(r1);

        assertTrue(quizretakes.quizschedule.printQuizScheduleForm(quizList, retakesList, course).equals(output.toString()))
    }

    //After printQuizScheduleForm prints to System.out, stores and prints out what was printed to System.out
    @After
    public void tearDown() {
        System.out.flush();
        System.setOut(system_output);
        System.out.println("TESTING OUTPUT: " + output.toString());
    }
}
