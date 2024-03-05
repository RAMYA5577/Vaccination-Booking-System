package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Enum.VaccineType;
import com.example.VaccinationBookingSystem.Model.Dose2;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Service.Dose2Service;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Dose2ServiceImpl implements Dose2Service {
    @Override
    public Dose2 createDose2(User user, VaccineType vaccineType) {

        Dose2 dose2=Dose2.builder()
                .doseId(String.valueOf(UUID.randomUUID()))
                .user(user)
                .vaccineType(vaccineType)
                .build();
        return dose2;
    }
}
