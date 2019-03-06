import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.util.*;
import java.time.*;
import junit.framework.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class TDDTests 
{
  @Test
  public void testOptions() 
  {
    //This block of code records all the outputs to System.out.
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(output);
    PrintStream system_output = System.out;
    System.setOut(ps);
    
    //Creates the prompts that triggers the professor options of the program
    String[] args = new String[2];
    args[0] = "Professor";
    args[1] = "SWE437"; 
      
    //Tests the professor's options functionality
    quizretakes.quizschedule.main(args);
    
    System.out.flush();
    System.setOut(system_output);

    //Expected output string
    String expected = "";
    expected += "Hello!\n";
    expected += "Are you a 'Professor' or 'Student'?\n\n";
    expected += "Welcome!\n";
    expected += "Here are a list of options:\n";
    expected += "Option 1: Schedule new quiz\n";
    expected += "Option 2: Schedule new retake\n";
    expected += "Option 3: Display retakes and quiz schedules\n";
    expected += "Option 4: Exit program\n\n";

    //Assertions to check the expected output to what was actually outputed
    try
    {
      assertTrue(expected.equals(output.toString()));
      
      System.out.println("EXPECTED:\n" + expected);
      System.out.println("ACTUAL:\n" + output.toString());
      
    }catch(AssertionError e)
    {
      System.out.println("EXPECTED:\n" + expected);
      System.out.println("ACTUAL:\n" + output.toString());
      
      throw e;
    }
  }
}