package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser implements AppointmentBookParser<AppointmentBook> {

  private final BufferedReader reader;

  public TextParser(Reader reader) {
    this.reader = new BufferedReader(reader);
  }

  @Override
  public AppointmentBook parse() throws ParserException {
    try {
      if (!reader.ready()) {
        throw new ParserException("Missing owner");
      }

      String owner = reader.readLine();
      return new AppointmentBook(owner);

    } catch (IOException e) {
      throw new ParserException("While parsing", e);
    }

  }
}
