package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.RequestDto.AppointmentRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.AppointmentResponseDto;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Exception.NotEligibleForDoseException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;

public interface AppointmentService {
    AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException;
}
