package com.example.VaccinationBookingSystem.Dto.ResponseDto;

import com.example.VaccinationBookingSystem.Enum.CentreType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CentreResponseDto {
    String name;
    String location;
    CentreType centreType;

}
