package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser implements AppointmentBookParser<AppointmentBook> {
  private final Reader reader;

  public TextParser(Reader reader) {
    this.reader = reader;
  }


  @Override
  public AppointmentBook parse() throws ParserException {
    BufferedReader br = new BufferedReader(this.reader);
    try {
      String owner = br.readLine();
      AppointmentBook book = new AppointmentBook(owner);
      for (String line = br.readLine(); line != null; line = br.readLine()) {
        String description = line;
        book.addAppointment(new Appointment(description));
      }

      return book;

    } catch (IOException e) {
      throw new ParserException("While reading text", e);
    }

  }
}
