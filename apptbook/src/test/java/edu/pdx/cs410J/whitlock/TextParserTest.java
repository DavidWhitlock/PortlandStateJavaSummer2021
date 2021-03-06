package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TextParserTest {

  @Test
  void emptyFileCannotBeParsed() {
    InputStream resource = getClass().getResourceAsStream("emptyFile.txt");
    assertNotNull(resource);

    TextParser parser = new TextParser(new InputStreamReader(resource));
    assertThrows(ParserException.class, parser::parse);
  }

  @Test
  void appointmentBookOwnerCanBeDumpedAndParsed() throws IOException, ParserException {
    String owner = "Owner";
    AppointmentBook book = new AppointmentBook(owner);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(book);

    TextParser parser = new TextParser(new StringReader(sw.toString()));
    book = parser.parse();

    assertThat(book.getOwnerName(), equalTo(owner));
  }

  @Test
  void appointmentBookOwnerCanBeDumpedToFileAndParsed(@TempDir File dir) throws IOException, ParserException {
    File textFile = new File(dir, "appointments.txt");

    String owner = "Owner";
    AppointmentBook book = new AppointmentBook(owner);

    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(book);

    TextParser parser = new TextParser(new FileReader(textFile));
    book = parser.parse();

    assertThat(book.getOwnerName(), equalTo(owner));
  }

  @Test
  void printFilesInCurrentWorkingDirectory() {
    File cwd = new File(System.getProperty("user.dir"));
    System.out.println(cwd);

    File[] children = cwd.listFiles();
    for(File child : children) {
      System.out.println(child);
    }
  }
}
