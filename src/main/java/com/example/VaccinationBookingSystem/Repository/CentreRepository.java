package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository<VaccinationCentre,Integer> {
}
