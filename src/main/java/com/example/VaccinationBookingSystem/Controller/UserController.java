package com.example.VaccinationBookingSystem.Controller;

import com.example.VaccinationBookingSystem.Dto.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserGetResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Exception.EmptyListException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Service.UserService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //1
   //add User
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto =userService.addUser(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    //2
    //find by emailId
    @GetMapping("get_user_by_email")
    public ResponseEntity getUserByMailId(@RequestParam("email") String emailId){
       try{
           UserGetResponseDto userGetResponseDto=userService.getUserByMailId(emailId);
           return new ResponseEntity<>(userGetResponseDto,HttpStatus.FOUND);
       }
       catch(UserNotFoundException e) {
           return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
       }
    }

    //3
    // update the name of the user using mobile number

    @PutMapping("/update_name_with_mob_no")
    public ResponseEntity updateUserNameWithMobNo(@RequestParam("mob") String mobNo,@RequestParam("name") String name){

        try{
            UserGetResponseDto userGetResponseDto=userService.updateUserNameWithMobNo(mobNo,name);
            return new ResponseEntity<>(userGetResponseDto,HttpStatus.FOUND);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //4
    //update user name with emailId
    @PutMapping("/update_name_with_email")
    public ResponseEntity updateUserNameWithEmailId(@RequestParam("email") String emailId,@RequestParam("name") String name){
        try{
            UserGetResponseDto userGetResponseDto=userService.updateUserNameWithEmailId(emailId,name);
            return new ResponseEntity<>(userGetResponseDto,HttpStatus.FOUND);
        }
        catch(UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    //5
    //All the users who have not taken a single vaccine
    @GetMapping("/get_all_non_vaccinated_users")
    public ResponseEntity getAllNonVaccinatedUsers() throws EmptyListException {
        try{
        List<String> nonVaccinatedUsers=userService.getAllNonVaccinatedUsers();
        return new ResponseEntity<>(nonVaccinatedUsers,HttpStatus.FOUND);}
        catch(EmptyListException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
        }
    }

    //6
    //All the users who have taken dose1 but not dose2
    @GetMapping("/get_users_taken_dose1_not_dose2")
    public ResponseEntity getUsersWhoTookDose1AndNotDose2(){
        List<String> usersWhoTookDoseOneOnly=userService.getUsersWhoTookDose1AndNotDose2();
        return new ResponseEntity<>(usersWhoTookDoseOneOnly,HttpStatus.FOUND);
    }

    //7
    //All users who have fully vaccinated
    @GetMapping("/get_all_fully_vaccinated_users")
    public ResponseEntity getFullyVaccinatedUsers() throws EmptyListException{
        try{
        List<String> fullyVaccinatedUsers=userService.getFullyVaccinatedUsers();
        return new ResponseEntity<>(fullyVaccinatedUsers,HttpStatus.FOUND);}
        catch(EmptyListException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.FOUND);
        }
    }

    //8
    //All the female users
    @GetMapping("/get_all_female_users")
    public ResponseEntity getAllFemaleUsers(){
        List<String> femaleUsers=userService.getAllFemaleUsers();
        return new ResponseEntity<>(femaleUsers,HttpStatus.FOUND);
    }

    //9
    //All Male Users
    @GetMapping("/get_all_male_users")
    public ResponseEntity getAllMaleUsers(){
        List<String> maleUsers=userService.getAllMaleUsers();
        return new ResponseEntity<>(maleUsers,HttpStatus.FOUND);
    }

    //10
    //All the male users who have not taken a single vaccines
    @GetMapping("/get_non_vaccinated_male_users")
    public ResponseEntity getNonVaccinatedMaleUsers(){
        List<String> nonVaccinatedMaleUsers=userService.getNonVaccinatedMaleUsers();
        return new ResponseEntity<>(nonVaccinatedMaleUsers,HttpStatus.FOUND);
    }

    //11
    //All the female users who have not taken a single vaccines
    @GetMapping("/get_non_vaccinated_female_users")
    public ResponseEntity getNonVaccinatedFemaleUsers(){
        List<String> nonVaccinatedFemaleUsers=userService.getNonVaccinatedFemaleUsers();
        return new ResponseEntity<>(nonVaccinatedFemaleUsers,HttpStatus.FOUND);
    }

    //12
    //All the female users who have fully vaccinated
    @GetMapping("/get_fully_vaccinated_female_users")
    public ResponseEntity getFullyVaccinatedFemaleUsers(){
        List<String> fullyVaccinatedFemaleUsers=userService.getFullyVaccinatedFemaleUsers();
        return new ResponseEntity<>(fullyVaccinatedFemaleUsers,HttpStatus.FOUND);
    }

    //13
    //All the male users who have fully vaccinated
    @GetMapping("/get_fully_vaccinated_male_users")
    public ResponseEntity getFullyVaccinatedMaleUsers(){
        List<String> fullyVaccinatedMaleUsers=userService.getFullyVaccinatedMaleUsers();
        return new ResponseEntity<>(fullyVaccinatedMaleUsers,HttpStatus.FOUND);
    }

}
