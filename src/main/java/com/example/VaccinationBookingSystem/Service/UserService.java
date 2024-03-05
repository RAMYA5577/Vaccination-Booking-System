package com.example.VaccinationBookingSystem.Service;

import com.example.VaccinationBookingSystem.Dto.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserGetResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Exception.EmptyListException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;
import com.example.VaccinationBookingSystem.Model.User;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);

    UserGetResponseDto getUserByMailId(String emailId) throws UserNotFoundException;

    UserGetResponseDto updateUserNameWithMobNo(String mobNo, String name) throws UserNotFoundException;

    UserGetResponseDto updateUserNameWithEmailId(String emailId, String name) throws UserNotFoundException;

    List<String> getAllNonVaccinatedUsers() throws EmptyListException;

    List<String> getUsersWhoTookDose1AndNotDose2();

    List<String> getFullyVaccinatedUsers() throws EmptyListException;

    List<String> getAllFemaleUsers();

    List<String> getAllMaleUsers();

    List<String> getNonVaccinatedMaleUsers();

    List<String> getNonVaccinatedFemaleUsers();

    List<String> getFullyVaccinatedFemaleUsers();

    List<String> getFullyVaccinatedMaleUsers();
}
