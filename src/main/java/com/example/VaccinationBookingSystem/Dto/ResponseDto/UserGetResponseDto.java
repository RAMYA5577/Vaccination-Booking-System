package com.example.VaccinationBookingSystem.Dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserGetResponseDto {
    String name;
    int age;
    Gender gender;
    String emailId;
    String mobNo;
}
