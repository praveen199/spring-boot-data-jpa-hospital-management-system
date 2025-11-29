package com.spring.data.jpa.repository;

import com.spring.data.jpa.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
