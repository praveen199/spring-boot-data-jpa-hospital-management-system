package com.spring.data.jpa.repository;

import com.spring.data.jpa.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
