package com.irojas.demojwt.repository;
import com.irojas.demojwt.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByEmail(String email);
}