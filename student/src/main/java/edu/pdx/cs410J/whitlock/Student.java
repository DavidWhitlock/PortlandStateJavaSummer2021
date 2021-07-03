package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
import java.util.List;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  public static final String USAGE_MESSAGE = "usage: java Student name gender gpa classes";
  static final String MISSING_COMMAND_LINE_ARGUMENTS = "Missing command line arguments";
  static final String MISSING_GENDER = "Missing Gender";
  static final String MISSING_GPA = "Missing GPA";
  static final String GPA_OUT_OF_BOUNDS = "GPA is out of bounds.  Range is 0 to 4.";

  private final double gpa;
  private final List<String> classes;
  private final Gender gender;

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
  public Student(String name, List<String> classes, double gpa, Gender gender) {
    super(name);
    this.gpa = gpa;
    this.classes = classes;
    this.gender = gender;
  }

  /**                                                                               
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    return "This class is too much work";
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
    int numClasses = this.classes.size();

    StringBuilder sb = new StringBuilder();
    sb.append(getName()).append(" has a GPA of ").append(gpa);
    sb.append(" and is taking ").append(numClasses).append(" class");
    sb.append(numClasses == 1 ? "" : "es");

    appendClasses(sb);

    sb.append(".  ");

    appendGenderPronoun(sb);
    sb.append(" says \"");
    sb.append(says());
    sb.append("\".");

    return sb.toString();
  }

  private void appendGenderPronoun(StringBuilder sb) {
    switch (this.gender) {
      case OTHER:
        sb.append("They");
        break;

      case FEMALE:
        sb.append("She");
        break;

      case MALE:
        sb.append("He");
        break;

      default:
        throw new UnsupportedOperationException("Unrecognized gender: " + gender);
    }

  }

  private void appendClasses(StringBuilder sb) {
    int numClasses = this.classes.size();
    if (numClasses > 0) {
      sb.append(": ");

      if (numClasses == 1) {
        sb.append(this.classes.get(0));

      } else if (numClasses == 2) {
        sb.append(this.classes.get(0));
        sb.append(" and ");
        sb.append(this.classes.get(1));

      } else {
        for (int i = 0; i < numClasses; i++) {
          boolean isLastClass = (i == numClasses - 1);
          if (isLastClass) {
            sb.append("and ");
          }

          String className = this.classes.get(i);
          sb.append(className);

          if (!isLastClass) {
            sb.append(", ");
          }
        }
      }
    }
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    String name = null;
    Gender gender = null;
    Double gpa = null;
    List<String> classes = new ArrayList<>();

    for (String arg : args) {
      if (name == null) {
        name = arg;

      } else if (gender == null) {
        gender = parseGender(arg);
        if (gender == null) {
          printErrorMessageAndExit("Unrecognized gender: \"" + arg + "\"");
        }

      } else if (gpa == null) {
        gpa = validateGpa(arg);

      } else {
        classes.add(arg);
      }
    }

    if (name == null) {
      printErrorMessageAndExit(MISSING_COMMAND_LINE_ARGUMENTS);
      return;

    } else if (gender == null) {
      printErrorMessageAndExit(MISSING_GENDER);
      return;

    } else if (gpa == null) {
      printErrorMessageAndExit(MISSING_GPA);
      return;
    }

    Student student = new Student(name, classes, gpa, gender);
    System.out.println(student);

    System.exit(0);
  }

  private static void printErrorMessageAndExit(String message) {
    System.err.println(message);
    System.err.println(USAGE_MESSAGE);
    System.exit(1);
  }

  private static Double validateGpa(String gpaAsString) {
    try {
      double gpa = Double.parseDouble(gpaAsString);
      if (gpa < 0.0 || gpa > 4.0) {
        printErrorMessageAndExit(GPA_OUT_OF_BOUNDS);
      }
      return gpa;

    } catch (NumberFormatException ex) {
      printErrorMessageAndExit("Invalid GPA: " + gpaAsString);
      return null;
    }
  }

  private static Gender parseGender(String gender) {
    if (gender.equalsIgnoreCase("other")) {
      return Gender.OTHER;

    } else if (gender.equalsIgnoreCase("female")) {
      return Gender.FEMALE;

    } else if (gender.equalsIgnoreCase("male")) {
      return Gender.MALE;

    } else {
      return null;
    }
  }
}