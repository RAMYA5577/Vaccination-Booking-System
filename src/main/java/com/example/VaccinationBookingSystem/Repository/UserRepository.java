package com.example.VaccinationBookingSystem.Repository;

import com.example.VaccinationBookingSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailId(String emailId);

    User findByMobNo(String mobNo);
}
