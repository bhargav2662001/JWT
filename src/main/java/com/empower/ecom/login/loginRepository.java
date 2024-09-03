package com.empower.ecom.login;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface loginRepository extends JpaRepository <login,Long> {
    Optional<login>findByEmail(String Email);
    Optional<login>findByUsername(String Username);
}
