package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    public Doctor findByEmailId(String emailId);
}
