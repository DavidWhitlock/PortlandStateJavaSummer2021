package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  public static final String USAGE_MESSAGE = "usage: java Student name gender gpa classes";
  static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments";
  static final String MISSING_GENDER = "Missing Gender";
  static final String MISSING_GPA = "Missing GPA";
  static final String GPA_OUT_OF_BOUNDS = "GPA is out of bounds.  Range is 0 to 4.";

  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male", "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, ArrayList<String> classes, double gpa, String gender) {
    super(name);
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    return getName();
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      printErrorMessageAndExit(MISSING_COMMAND_LINE_ARGUMENTS);

    } else if (args.length == 1) {
      printErrorMessageAndExit(MISSING_GENDER);

    } else if (args.length == 2) {
      String gender = args[1];
      if (!isRecognizedGender(gender)) {
        printErrorMessageAndExit("Unrecognized gender: \"" + gender + "\"");

      } else {
        printErrorMessageAndExit(MISSING_GPA);
      }

    } else if (args.length == 3) {
      String gpa = args[2];
      validateGpa(gpa);
    }

    System.exit(0);
  }

  private static void printErrorMessageAndExit(String message) {
    System.err.println(message);
    System.err.println(USAGE_MESSAGE);
    System.exit(1);
  }

  private static void validateGpa(String gpaAsString) {
    try {
      double gpa = Double.parseDouble(gpaAsString);
      if (gpa < 0.0 || gpa > 4.0) {
        printErrorMessageAndExit(GPA_OUT_OF_BOUNDS);
      }

    } catch (NumberFormatException ex) {
      printErrorMessageAndExit("Invalid GPA: " + gpaAsString);
    }
  }

  private static boolean isRecognizedGender(String gender) {
    return gender.equals("other") || gender.equals("female") || gender.equals("male");
  }
}