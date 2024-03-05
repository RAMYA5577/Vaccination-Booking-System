package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Enum.VaccineType;
import com.example.VaccinationBookingSystem.Model.Dose2;
import com.example.VaccinationBookingSystem.Model.User;

public interface Dose2Service {

    Dose2 createDose2(User user, VaccineType vaccineType);
}
