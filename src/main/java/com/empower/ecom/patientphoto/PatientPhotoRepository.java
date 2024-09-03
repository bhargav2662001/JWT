package com.empower.ecom.patientphoto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientPhotoRepository extends JpaRepository<PatientPhoto, Long> {
    List<PatientPhoto> findAllByemailId(String emailId);
}