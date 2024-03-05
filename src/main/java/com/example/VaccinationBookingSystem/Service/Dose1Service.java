package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.VaccineType;
import com.example.VaccinationBookingSystem.Model.Dose1;
import com.example.VaccinationBookingSystem.Model.User;

public interface Dose1Service {
    public Dose1 createDose1(User user, VaccineType vaccineType);
}
