package com.example.hospital.repository;

import com.example.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContaining(String name);
}
