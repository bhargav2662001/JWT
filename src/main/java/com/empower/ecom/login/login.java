package com.empower.ecom.login;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Table(name="jwt")
@Entity
public class login {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq")
    @SequenceGenerator(name = "person_seq", sequenceName = "PERSON_SEQ", allocationSize = 1)

    public Long getOID() {
        return OID;
    }

    public Long OID;

    @Getter
    public String Username;

    @Getter
    public String Email;

    @Getter
    public String Password;
}
