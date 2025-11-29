package com.spring.data.jpa.repository;

import com.spring.data.jpa.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
