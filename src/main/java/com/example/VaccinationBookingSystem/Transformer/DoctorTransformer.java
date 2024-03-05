package com.example.VaccinationBookingSystem.Transformer;

import com.example.VaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.Model.Doctor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DoctorTransformer {
    public static Doctor doctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .name(doctorRequestDto.getName())
                .age(doctorRequestDto.getAge())
                .mobNo(doctorRequestDto.getMobNo())
                .emailId(doctorRequestDto.getEmailId())
                .gender(doctorRequestDto.getGender())
                .build();
    }

    public static DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor){
        CentreResponseDto centreResponseDto=CentreTansformer.centreToCentreResponseDto(doctor.getVaccinationCentre());
        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .mobNo(doctor.getMobNo())
                .emailId(doctor.getEmailId())
                .centreResponseDto(centreResponseDto)
                .build();
    }
}
