package com.empower.ecom.patientdetails;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class patientdataservice {
    @Autowired
    private  patientdataRepository PatientdataRepository;

    public List<patientdata> getAllPatients() {
        return PatientdataRepository.findAll();
    }

    public patientdata getPatientById(Long id) {
        return PatientdataRepository.findById(id).orElse(null);
    }

    public patientdata addPatient(patientdata patientdata) {
        return PatientdataRepository.save(patientdata);
    }

    public patientdata updatePatient(Long id, patientdata patientdata) {
        patientdata existingPatient = PatientdataRepository.findById(id).orElse(null);
        if (existingPatient != null) {
            BeanUtils.copyProperties(patientdata, existingPatient, "id"); // Copy properties except the 'id'
            return PatientdataRepository.save(existingPatient);
        } else {
            return null;
        }
    }
    public void deletePatient(Long id) {
        PatientdataRepository.deleteById(id);
    }
}