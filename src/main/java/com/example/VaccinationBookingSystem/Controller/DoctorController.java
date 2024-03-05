package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.RequestDto.DoctorRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.DoctorResponseDto;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Exception.DoctorNotFoundException;
import com.example.VaccinationBookingSystem.Model.Doctor;
import com.example.VaccinationBookingSystem.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    //1
    //Add doctor
    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto)throws CentreNotFoundException{
        try{
            DoctorResponseDto doctorResponseDto=doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //2
    // get all the doctors who have more than 10 appointments
    @GetMapping("/get_all_doctors_who_have_more_than_10_appointments")
    public ResponseEntity getAllDoctorsWhoHaveMoreThan10Appointments(){
        List<String> doctors=doctorService.getAllDoctorsWhoHaveMoreThan10Appointments();
        return new ResponseEntity<>(doctors,HttpStatus.FOUND);
    }

    //3
    // get all the male doctors whose age is above 40
    @GetMapping("/get_male_doctors_age_above_40")
    public ResponseEntity getMaleDoctorsAgeAbove40(){
        List<String> maleDoctorsAgeAbove40=doctorService.getMaleDoctorsAgeAbove40();
        return new ResponseEntity<>(maleDoctorsAgeAbove40,HttpStatus.FOUND);
    }

    //4
    // get the ratio of male to female doctors
    @GetMapping("/get_ratio_of_male_and_female_doctors")
    public ResponseEntity getRatioOfMaleAndFemaleDoctors(){
        String ratio=doctorService.getRatioOfMaleAndFemaleDoctors();
        return new ResponseEntity<>(ratio,HttpStatus.FOUND);
    }

    //5
    //update the name based on email id of the doctor
    @PutMapping("/update_name_of_doctor_with_emailId")
    public ResponseEntity updateDoctorNameWithEmailId(@RequestParam("name") String name,@RequestParam("emailId") String emailId) throws DoctorNotFoundException {
        try{
        DoctorResponseDto doctorResponseDto = doctorService.updateDoctorNameWithEmailId(name, emailId);
        return new ResponseEntity<>(doctorResponseDto, HttpStatus.FOUND);
           }
        catch(DoctorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
