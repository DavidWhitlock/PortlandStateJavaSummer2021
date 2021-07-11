package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
  private final Collection<Appointment> appointments = new ArrayList<>();
  private final String owner;

  public AppointmentBook(String owner) {
    this.owner = owner;
  }

  @Override
  public String getOwnerName() {
    return owner;
  }

  @Override
  public Collection<Appointment> getAppointments() {
    return this.appointments;
  }

  @Override
  public void addAppointment(Appointment appt) {
    this.appointments.add(appt);
  }
}
