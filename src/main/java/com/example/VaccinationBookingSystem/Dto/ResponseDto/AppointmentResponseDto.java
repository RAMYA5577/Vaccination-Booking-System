package com.example.VaccinationBookingSystem.Dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.DoseNo;
import com.example.VaccinationBookingSystem.Enum.VaccineType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {
    String userName;

    String appointmentNo;

    Date dateOfAppointment;

    DoseNo doseNo;

    CentreResponseDto centerResponseDto;

    String doctorName;

    VaccineType vaccineType;
}
