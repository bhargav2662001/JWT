package com.empower.ecom.patientdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class patientdatacontroller {

    @Autowired
    private  patientdataservice  Patientdataservice;

    @GetMapping
    public ResponseEntity<List<patientdata>> getAllPatients() {
        List<patientdata> patients = Patientdataservice.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<patientdata> getPatientById(@PathVariable Long id) {
        patientdata patient = Patientdataservice.getPatientById(id);
        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<patientdata> addPatient(@RequestBody patientdata patientdata) {
        patientdata savedPatient = Patientdataservice.addPatient(patientdata);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<patientdata> updatePatient(@PathVariable Long id, @RequestBody patientdata patientdata) {
        patientdata updatedPatient = Patientdataservice.updatePatient(id, patientdata);
        if (updatedPatient != null) {
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Patientdataservice.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
