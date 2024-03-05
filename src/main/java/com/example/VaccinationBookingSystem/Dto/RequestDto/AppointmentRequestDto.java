package com.example.VaccinationBookingSystem.Dto.RequestDto;

import com.example.VaccinationBookingSystem.Enum.DoseNo;
import com.example.VaccinationBookingSystem.Enum.VaccineType;
import com.example.VaccinationBookingSystem.Model.Dose2;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentRequestDto {
    DoseNo doseNo;
    int userId;
    int doctorId;
    VaccineType vaccineType;
}
