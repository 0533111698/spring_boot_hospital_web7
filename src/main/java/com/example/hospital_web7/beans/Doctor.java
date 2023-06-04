package com.example.hospital_web7.beans;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    private long id;
    private String name;
    private String internship;
    @OneToMany(mappedBy = "doctor",fetch = FetchType.EAGER)
    private List<Patient>patients;

    public Doctor() {
    }

    public Doctor(long id, String name, String interShip) {
        this.id = id;
        this.name = name;
        this.internship = interShip;
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

    public String getInternship() {
        return internship;
    }

    public void setInternship(String internship) {
        this.internship = internship;
    }

    public List<Patient> getPatients() {
        return patients;
    }


    public void addPatient(Patient patient) {

        patients.add(patient);
    }
    public void deletePatient(Patient patient) {
        patients.remove(patient);
    }


}
