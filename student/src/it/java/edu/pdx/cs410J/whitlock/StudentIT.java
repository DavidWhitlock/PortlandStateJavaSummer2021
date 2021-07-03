package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
class StudentIT extends InvokeMainTestCase {

  @Test
  void invokingMainWithNoArgumentsHasExitCodeOf1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString(Student.MISSING_COMMAND_LINE_ARGUMENTS));
    assertThat(result.getTextWrittenToStandardError(), containsString(Student.USAGE_MESSAGE));
  }

  @Test
  void missingGender() {
    MainMethodResult result = invokeMain(Student.class, "Dave");
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing Gender"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void unrecognizedGender() {
    MainMethodResult result = invokeMain(Student.class, "Dave", "3.45");
    assertThat(result.getTextWrittenToStandardError(), containsString("Unrecognized gender: \"3.45\""));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void otherIsARecognizedGenderButGpaIsMissing() {
    String gender = "other";
    assertRecognizedGenderButGpaIsMissing(gender);
  }

  private void assertRecognizedGenderButGpaIsMissing(String gender) {
    MainMethodResult result = invokeMain(Student.class, "Dave", gender);
    assertThat(result.getTextWrittenToStandardError(), containsString(Student.MISSING_GPA));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void femaleIsARecognizedGenderButGpaIsMissing() {
    assertRecognizedGenderButGpaIsMissing("female");
  }

  @Test
  void maleIsARecognizedGenderButGpaIsMissing() {
    assertRecognizedGenderButGpaIsMissing("male");
  }

  @Test
  void nonParsableGpa() {
    MainMethodResult result = invokeMain(Student.class, "Cody", "male", "Java");
    assertThat(result.getTextWrittenToStandardError(), containsString("Invalid GPA: Java"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void validGpaWithNoClasses() {
    MainMethodResult result = invokeMain(Student.class, "Cody", "male", "3.45");
    assertThat(result.getTextWrittenToStandardError(), equalTo(""));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  void gpaLessThanZero() {
    MainMethodResult result = invokeMain(Student.class, "Cody", "male", "-1.0");
    assertThat(result.getTextWrittenToStandardError(), containsString(Student.GPA_OUT_OF_BOUNDS));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void gpaGreaterThanFour() {
    MainMethodResult result = invokeMain(Student.class, "Cody", "male", "5.0");
    assertThat(result.getTextWrittenToStandardError(), containsString(Student.GPA_OUT_OF_BOUNDS));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void unrecognizedGenderAndValidGpa() {
    MainMethodResult result = invokeMain(Student.class, "Dave", "unrecognized", "3.45");
    assertThat(result.getTextWrittenToStandardError(), containsString("Unrecognized gender: \"unrecognized\""));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  void otherGenderIsCaseInsensitive() {
    assertCaseInsensitiveGenderIsSupported("Other");
  }

  @Test
  void otherFemaleIsCaseInsensitive() {
    assertCaseInsensitiveGenderIsSupported("Female");
  }

  @Test
  void otherMaleIsCaseInsensitive() {
    assertCaseInsensitiveGenderIsSupported("Male");
  }

  private void assertCaseInsensitiveGenderIsSupported(String caseInsensitiveGender) {
    MainMethodResult result = invokeMain(Student.class, "Name", caseInsensitiveGender, "3.64");
    assertThat(result.getTextWrittenToStandardError(), not(containsString("Unrecognized gender")));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  @Disabled
  void daveStudentFromAssignment() {
    MainMethodResult result = invokeMain(Student.class, "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(result.getTextWrittenToStandardError(), emptyString());
    String message = "Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
      "Systems, and Java. He says \"This class is too much work\".";
    assertThat(result.getTextWrittenToStandardOut(), equalTo(message));
    assertThat(result.getExitCode(), equalTo(0));
  }

}
