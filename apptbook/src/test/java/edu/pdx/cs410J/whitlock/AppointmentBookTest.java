package edu.pdx.cs410J.whitlock;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class AppointmentBookTest {
  @Test
  void addingAnAppointmentAddsAppointment() {
    Appointment appointment = new Appointment();
    AppointmentBook book = new AppointmentBook("owner");
    book.addAppointment(appointment);

    assertThat(book.getAppointments(), hasItem(appointment));
  }
}
