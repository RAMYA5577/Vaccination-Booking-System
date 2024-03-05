package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.RequestDto.CentreRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Enum.CentreType;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;

import java.util.List;

public interface CentreService {
    CentreResponseDto addCentre(CentreRequestDto centreRequestDto);

    List<String> getListOfDoctorsAtAParticularCentre(int id) throws CentreNotFoundException;

    List<String> getListOfAllMaleDoctorsAtAParticularCentre(int id) throws CentreNotFoundException;

    List<String> getListOfAllFemaleDoctorsAtAParticularCentre(int id) throws CentreNotFoundException;

    List<String> getListOfMaleDoctorsAgeAbove40AtAParticularCentre(int id) throws CentreNotFoundException;

    List<String> getAllCentresWithParticularCentreType(CentreType centreType) throws CentreNotFoundException;
}
