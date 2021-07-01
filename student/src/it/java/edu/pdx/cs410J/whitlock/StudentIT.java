package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
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

}
