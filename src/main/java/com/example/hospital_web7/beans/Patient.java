package com.example.hospital_web7.beans;

import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    private long id;
    private String name;
    private String diagnosis;
    private int daysInHospital;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Patient() {
    }

    public Patient(long id, String name, String diagnosis, int daysInHospital) {
        this.id = id;
        this.name = name;
        this.diagnosis = diagnosis;
        this.daysInHospital = daysInHospital;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public int getDaysInHospital() {
        return daysInHospital;
    }

    public void setDaysInHospital(int daysInHospital) {
        this.daysInHospital = daysInHospital;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


}
