package com.empower.ecom.patientdetails;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class patientdata {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
   @SequenceGenerator(name = "person_seq", sequenceName = "PERSON_SEQ", allocationSize = 1)

   private Long getOID() {
       return OID;
   }

    private void setOID(Long OID) {
        this.OID = OID;
    }

    private Long OID;

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bloodGroup;
    private String emailId;
    private String mobile;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
