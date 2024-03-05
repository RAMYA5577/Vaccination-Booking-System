package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Enum.VaccineType;
import com.example.VaccinationBookingSystem.Model.Dose1;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Repository.Dose1Repository;
import com.example.VaccinationBookingSystem.Service.Dose1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose1ServiceImpl implements Dose1Service {
@Autowired
    Dose1Repository dose1Repository;
    @Override
    public Dose1 createDose1(User user, VaccineType vaccineType) {
        Dose1 dose1 = Dose1.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .vaccineType(vaccineType)
                .user(user)
                .build();

        return dose1;
    }
}
