package com.example.hospital_web7.facade;

import com.example.hospital_web7.beans.Doctor;
import com.example.hospital_web7.beans.Patient;
import com.example.hospital_web7.exeption.ExeptionDoctor;
import com.example.hospital_web7.exeption.ExeptionPatient;
import com.example.hospital_web7.repository.DoctorRepository;
import com.example.hospital_web7.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterException;
import java.util.List;
import java.util.Set;

@Service
public class DPFacade {
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;

    public DPFacade(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public boolean addPatient(Patient pat) {
        if (patientRepository.existsById(pat.getId()))
            return false;
       patientRepository.save(pat);
       return true;
    }
    public boolean addDoctor(Doctor doctor){
        if (doctorRepository.existsById(doctor.getId()))
            return false;
        doctorRepository.save(doctor);
        return true ;
    }
    public boolean upDateDoctor(Doctor doctor){
        if (doctorRepository.existsById(doctor.getId())){
            doctorRepository.save(doctor);
            return true;
        }
        return false;
    }
    public boolean upDatePatient(Patient patient){
        if (patientRepository.existsById(patient.getId())){
            patientRepository.save(patient);
            return true;
        }
        return false;
    }
    public boolean deletePatient(long id){
        if (patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public boolean deleteDoctor(long id) throws ExeptionDoctor {
        List<Patient>patients=patientRepository.findAll();
        for (Patient p:patients){
            if (p.getDoctor().getId()==id){
                deletePatientFromDoctor(p.getDoctor().getId(),p);
            }
        }
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public Doctor getOneDoctor(long id) throws ExeptionDoctor {
        return doctorRepository.findById(id).orElseThrow(()->new ExeptionDoctor("id"+id+ " not found"));
    }
    public Patient getOnePatient(long id) {
        return patientRepository.findById(id).orElseThrow();
    }
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public List<Patient> getAllPatient(){
        return patientRepository.findAll();
    }
    public List<Doctor>FindByInternship(String internship){
        return doctorRepository.findByInternship(internship);
    }
    public void addPatientToDoctor(long doctorId,Patient patient) throws ExeptionDoctor {
            Doctor doctor = getOneDoctor(doctorId);
            Patient p1=getOnePatient(patient.getId());
            p1.setDoctor(doctor);
            doctor.addPatient(p1);
            upDateDoctor(doctor);


    }
    public void deletePatientFromDoctor(long doctorId,Patient patient) throws ExeptionDoctor {
        Doctor doctor = getOneDoctor(doctorId);
        Patient patient1=getOnePatient(patient.getId());
        patient1.setDoctor(null);
        doctor.deletePatient(patient1);
        upDateDoctor(doctor);


    }
    public List<Patient>findByName(String name){
        return patientRepository.findPatientByName(name);
    }


}
