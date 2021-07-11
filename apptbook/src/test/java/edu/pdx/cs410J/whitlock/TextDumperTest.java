package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TextDumperTest {

  @Test
  void dumperDumpsAppointmentBookOwner() throws IOException {
    String owner = "Owner";
    AppointmentBook book = new AppointmentBook(owner);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(book);

    sw.flush();

    String dumpedText = sw.toString();
    assertThat(dumpedText, containsString(owner));

  }
}
