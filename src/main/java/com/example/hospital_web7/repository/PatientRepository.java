package com.example.hospital_web7.repository;

import com.example.hospital_web7.beans.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient>findPatientByName(String name);

}
