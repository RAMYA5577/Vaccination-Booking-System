package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CentreNotFoundException;

    List<String> getAllDoctorsWhoHaveMoreThan10Appointments();

    List<String> getMaleDoctorsAgeAbove40();

    String getRatioOfMaleAndFemaleDoctors();

    DoctorResponseDto updateDoctorNameWithEmailId(String name, String emailId) throws DoctorNotFoundException;
}
