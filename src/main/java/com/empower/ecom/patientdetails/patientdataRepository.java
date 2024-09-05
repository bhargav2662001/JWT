package com.empower.ecom.patientdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface patientdataRepository extends JpaRepository< patientdata, Long> {
}