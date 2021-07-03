package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
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
    var pat = new Student(name, emptyList(), 0.0, "Doesn't matter");
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
    List<String> classes = List.of("Algorithms", "Operating Systems", "Java");

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
    Student student = new Student("Name", emptyList(), gpa, "other");

    assertThat(student.toString(), containsString(String.valueOf(gpa)));
  }

  @Test
  void toStringContainsNameAndGpa() {
    double gpa = 3.45;
    String name = "Name";
    Student student = new Student(name, emptyList(), gpa, "other");

    assertThat(student.toString(), containsString(name + " has a GPA of " + gpa));

  }

  @Test
  void studentTakingZeroClasses() {
    double gpa = 3.45;
    String name = "Name";
    Student student = new Student(name, emptyList(), gpa, "other");

    assertThat(student.toString(), containsString("is taking 0 classes."));
  }

  @Test
  void studentTakingOneClass() {
    String className = "Class1";
    List<String> classes = List.of(className);

    Student student = new Student("Name", classes, 3.45, "other");

    assertThat(student.toString(), containsString("is taking 1 class: " + className + "."));

  }

  @Test
  void studentTakingTwoClasses() {
    String className1 = "Class1";
    String className2 = "Class2";
    List<String> classes = List.of(className1, className2);

    Student student = new Student("Name", classes, 3.45, "other");

    assertThat(student.toString(), containsString("is taking 2 classes: " + className1 + " and " + className2 + "."));

  }

}
