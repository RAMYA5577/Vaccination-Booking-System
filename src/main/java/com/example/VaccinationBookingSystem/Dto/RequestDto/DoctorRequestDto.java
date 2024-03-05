package com.example.VaccinationBookingSystem.Dto.RequestDto;

import com.example.VaccinationBookingSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DoctorRequestDto {
    String name;
    int age;
    String emailId;
    String mobNo;
    Gender gender;
    int centreId;

}
