package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.IOException;
import java.io.PrintWriter;

public class TextDumper implements AppointmentBookDumper<AppointmentBook> {
  private final PrintWriter writer;

  public TextDumper(PrintWriter writer) {
    this.writer = writer;
  }

  @Override
  public void dump(AppointmentBook book) throws IOException {
    writer.println(book.getOwnerName());
    for(Appointment appointment : book.getAppointments()) {
      writer.println(appointment.getDescription());
    }
  }
}
