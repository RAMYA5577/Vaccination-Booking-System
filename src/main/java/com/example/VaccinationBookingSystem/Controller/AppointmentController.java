package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.RequestDto.AppointmentRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.AppointmentResponseDto;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Exception.NotEligibleForDoseException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;
import com.example.VaccinationBookingSystem.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, NotEligibleForDoseException {
        try{
            AppointmentResponseDto appointmentResponseDto=appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity<>(appointmentResponseDto, HttpStatus.FOUND);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(DoctorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(NotEligibleForDoseException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

//    generate certificate
    //dose1
    //dose2
}