package com.example.VaccinationBookingSystem.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorResponseDto {
    String name;
    String emailId;
    String mobNo;
    CentreResponseDto centreResponseDto;
}
