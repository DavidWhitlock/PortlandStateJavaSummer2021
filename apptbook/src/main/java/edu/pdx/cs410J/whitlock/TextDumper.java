package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.IOException;
import java.io.Writer;

public class TextDumper implements AppointmentBookDumper<AppointmentBook> {
  private final Writer writer;

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

  @Override
  public void dump(AppointmentBook book) throws IOException {
    writer.write(book.getOwnerName());
  }
}
