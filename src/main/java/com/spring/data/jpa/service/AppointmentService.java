package com.spring.data.jpa.service;

import com.spring.data.jpa.entity.Appointment;
import com.spring.data.jpa.entity.Doctor;
import com.spring.data.jpa.entity.Patient;
import com.spring.data.jpa.repository.AppointmentRepository;
import com.spring.data.jpa.repository.DoctorRepository;
import com.spring.data.jpa.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;

  @Transactional
  public void createNewAppointment(Appointment appointment, Long doctorId, Long patientId) {

    Patient patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new EntityNotFoundException("Patient not found with ID: " + patientId));
    Doctor doctor = doctorRepository.findById(doctorId)
        .orElseThrow(() -> new EntityNotFoundException("Doctor not found with ID: " + doctorId));

    appointment.setPatient(patient);
    appointment.setDoctor(doctor);

    patient.getAppointments().add(appointment);
    appointmentRepository.save(appointment);
  }

  public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
    Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
    Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

    appointment.setDoctor(doctor);
    return appointment;
  }
}
