package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new ArrayList<>(), 0.0, "Doesn't matter");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  @Disabled
  void daveStudentFromAssignment() {
    Student dave = getDave();

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
      "Systems, and Java.  He says \"This class is too much work\"."));
  }

  private Student getDave() {
    ArrayList<String> classes = new ArrayList<>();
    classes.add("Algorithms");
    classes.add("Operating Systems");
    classes.add("Java");

    return new Student("Dave", classes, 3.64, "male");
  }

  @Test
  void toStringContainsStudentName() {
    Student dave = getDave();

    assertThat(dave.toString(), containsString("Dave"));
  }

  @Test
  void toStringContainsGpa() {
    double gpa = 3.45;
    Student student = new Student("Name", new ArrayList<>(), gpa, "other");

    assertThat(student.toString(), containsString(String.valueOf(gpa)));
  }

  @Test
  void toStringContainsNameAndGpa() {
    double gpa = 3.45;
    String name = "Name";
    Student student = new Student(name, new ArrayList<>(), gpa, "other");

    assertThat(student.toString(), containsString(name + " has a GPA of " + gpa));

  }

  @Test
  void studentTakingZeroClasses() {
    double gpa = 3.45;
    String name = "Name";
    Student student = new Student(name, new ArrayList<>(), gpa, "other");

    assertThat(student.toString(), containsString("is taking 0 classes."));
  }

  @Test
  void studentTakingOneClass() {
    double gpa = 3.45;
    String name = "Name";
    ArrayList<String> classes = new ArrayList<>();
    String className = "Class1";
    classes.add(className);

    Student student = new Student(name, classes, gpa, "other");

    assertThat(student.toString(), containsString("is taking 1 class: " + className + "."));

  }

}
