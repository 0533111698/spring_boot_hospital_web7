package com.example.hospital_web7.repository;

import com.example.hospital_web7.beans.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    List<Doctor>findByInternship(String internship);

}
