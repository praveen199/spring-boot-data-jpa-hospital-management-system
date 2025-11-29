package com.spring.data.jpa.entity;

import com.spring.data.jpa.entity.type.BloodGroupType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 40)
  private String name;

  private LocalDate birthDate;

  @Column(unique = true, nullable = false)
  private String email;

  private String gender;

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  private BloodGroupType bloodGroup;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "patient_insurance_id") //Owning side
  private Insurance insurance;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
  @ToString.Exclude
  private List<Appointment> appointments = new ArrayList<>();

}
