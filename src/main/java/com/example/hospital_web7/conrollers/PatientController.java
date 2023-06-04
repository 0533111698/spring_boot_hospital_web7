package com.example.hospital_web7.conrollers;

import com.example.hospital_web7.beans.Doctor;
import com.example.hospital_web7.beans.Patient;
import com.example.hospital_web7.exeption.ExeptionDoctor;
import com.example.hospital_web7.exeption.ExeptionPatient;
import com.example.hospital_web7.facade.DPFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PatientController {
    private DPFacade dpFacade;

    public PatientController(DPFacade dpFacade) {
        this.dpFacade = dpFacade;
    }
    @PostMapping
    public ResponseEntity <String> addPatient(@RequestBody Patient patient){
       if( dpFacade.addPatient(patient)){
           return ResponseEntity.status(HttpStatus.CREATED).body(" patient added!");
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! the patient exists already");
    }
    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        if (dpFacade.addDoctor(doctor)){
            return ResponseEntity.status(HttpStatus.CREATED).body("Doctor added!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding doctor");
    }
    @PutMapping("/updateDoctor")
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor){
        if (dpFacade.upDateDoctor(doctor)) {
            return ResponseEntity.ok("The doctor update!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor is not found");
    }
    @PutMapping("/updatePatient")
    public ResponseEntity<String> updatePatient(@RequestBody Patient patient){
        if (dpFacade.upDatePatient(patient)) {
            return ResponseEntity.ok("The patient update!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient is not found");
    }
    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<String>deletePatient(@PathVariable long id){
        if (dpFacade.deletePatient(id)){
            return ResponseEntity.ok("The patient deleted!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The patient is not found!");
    }
    @DeleteMapping("/deleteDoctor/{id}")
    public ResponseEntity<String>deleteDoctor(@PathVariable long id){
        try {
            dpFacade.deleteDoctor(id);
                return ResponseEntity.ok("The doctor is deleted!");
        } catch (ExeptionDoctor e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The doctor is not found!");
        }
    }
    @GetMapping("/oneDoctor/{id}")
    public ResponseEntity<?>getOneDoctor(@PathVariable long id){
        try {
            return ResponseEntity.ok(dpFacade.getOneDoctor(id));
        } catch (ExeptionDoctor e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @GetMapping("/onePatient/{id}")
    public ResponseEntity<?>getOnePatient(@PathVariable long id){
        return ResponseEntity.ok(dpFacade.getOnePatient(id));
    }
    @GetMapping("/getAllDoctors")
    public List <Doctor>getAllDoctors(){
        return dpFacade.getAllDoctors();
    }

    @PutMapping("/addPatientToDoctor/{doctorId}")
    public ResponseEntity<String> addPatientToDoctor(@PathVariable long doctorId,@RequestBody Patient patient ){
        try {
            dpFacade.addPatientToDoctor(doctorId,patient);
            return ResponseEntity.ok("Added!");
        } catch (ExeptionDoctor e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
    @PutMapping("/deletePatientFromDoctor/{doctorId}")
    public ResponseEntity<String> deletePatientFromDoctor(@PathVariable long doctorId,@RequestBody Patient patient ){
        try {
            dpFacade.deletePatientFromDoctor(doctorId,patient);
            return ResponseEntity.ok("Deleted");
        } catch (ExeptionDoctor e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getDoctorByInternship/{internship}")
    public List<Doctor>getPatientByInternship(@PathVariable String internship){
        return dpFacade.FindByInternship(internship);
    }
    @GetMapping("/getPatientsByName/{name}")
    public List<Patient>getPatientsByName(@PathVariable String name){
        return dpFacade.findByName(name);
    }
}
