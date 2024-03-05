package com.example.VaccinationBookingSystem.Transformer;

import com.example.VaccinationBookingSystem.Dto.RequestDto.CentreRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Model.VaccinationCentre;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CentreTansformer {
    public static VaccinationCentre centreRequestDtoToCentre(CentreRequestDto centreRequestDto){
        return VaccinationCentre.builder()
                .name(centreRequestDto.getName())
                .centreType(centreRequestDto.getCentreType())
                .location(centreRequestDto.getLocation())
                .build();
    }

    public static CentreResponseDto centreToCentreResponseDto(VaccinationCentre vaccinationCentre){
        return CentreResponseDto.builder()
                .name(vaccinationCentre.getName())
                .centreType(vaccinationCentre.getCentreType())
                .location(vaccinationCentre.getLocation())
                .build();
    }
}
