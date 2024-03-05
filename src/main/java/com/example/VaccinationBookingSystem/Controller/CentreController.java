package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.RequestDto.CentreRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.CentreResponseDto;
import com.example.VaccinationBookingSystem.Enum.CentreType;
import com.example.VaccinationBookingSystem.Exception.CentreNotFoundException;
import com.example.VaccinationBookingSystem.Service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/centre")
public class CentreController {
    @Autowired
    CentreService centreService;

    // 1
    //Add Centre
    @PostMapping("/add")
    public ResponseEntity addCentre(@RequestBody CentreRequestDto centreRequestDto){
        CentreResponseDto centreResponseDto=centreService.addCentre(centreRequestDto);
        return new ResponseEntity<>(centreResponseDto, HttpStatus.CREATED);
    }

    //2
    // give the list of all doctors at a particular center(centerId)
    @GetMapping("/get_list_of_all_doctors")
    public ResponseEntity getListOfAllDoctorsAtAParticularCentre(@RequestParam("centreId") int id)throws CentreNotFoundException {
        try{
            List<String> doctors=centreService.getListOfDoctorsAtAParticularCentre(id);
            return new ResponseEntity<>(doctors,HttpStatus.FOUND);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //3
    // give the list of all male doctors at a particular center(centerId)
    @GetMapping("/get_list_of_all_male_doctors_at_a_particular_centre")
    public ResponseEntity getListOfAllMaleDoctorsAtAParticularCentre(@RequestParam("centreId") int id)throws CentreNotFoundException{
        try{
            List<String> maleDoctors=centreService.getListOfAllMaleDoctorsAtAParticularCentre(id);
            return new ResponseEntity<>(maleDoctors,HttpStatus.FOUND);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //4
    // give the list of all females doctors at a particular center(centerId)
    @GetMapping("/get_list_of_all_female_doctors_at_a_particular_centre")
    public ResponseEntity getListOfAllFemaleDoctorsAtAParticularCentre(@RequestParam("centreId") int id)throws CentreNotFoundException{
        try{
            List<String> femaleDoctors=centreService.getListOfAllFemaleDoctorsAtAParticularCentre(id);
            return new ResponseEntity<>(femaleDoctors,HttpStatus.FOUND);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //5
    // give the list of all male doctors above age 40 at a particular center(centerId)
    @GetMapping("/get_male_doctors_age_above_40")
    public ResponseEntity getListOfMaleDoctorsAgeAbove40AtAParticularCentre(@RequestParam("centreId") int id)throws CentreNotFoundException{
        try{
            List<String> maleDoctorsAgeAbove40=centreService.getListOfMaleDoctorsAgeAbove40AtAParticularCentre(id);
            return new ResponseEntity<>(maleDoctorsAgeAbove40,HttpStatus.FOUND);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    //6
    // give all centers of a particular centerType
    @GetMapping("/get_all_centre_with_same_centre_type")
    public ResponseEntity getAllCentresWithParticularCentreType(@RequestParam("centreType")CentreType centreType) throws  CentreNotFoundException {
        try{
            List<String> centresWithSameCentreType=centreService.getAllCentresWithParticularCentreType(centreType);
            return new ResponseEntity<>(centresWithSameCentreType,HttpStatus.FOUND);
        }
        catch(CentreNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
}
