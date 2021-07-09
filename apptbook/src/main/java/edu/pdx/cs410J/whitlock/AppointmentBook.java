package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
  private final Collection<Appointment> appointments = new ArrayList<>();

  @Override
  public String getOwnerName() {
    throw new UnsupportedOperationException("This method is not implemented yet");
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
