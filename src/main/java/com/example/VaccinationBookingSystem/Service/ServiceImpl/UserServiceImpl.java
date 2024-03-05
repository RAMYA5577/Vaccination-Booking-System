package com.example.VaccinationBookingSystem.Service.ServiceImpl;

import com.example.VaccinationBookingSystem.Dto.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserGetResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Enum.Gender;
import com.example.VaccinationBookingSystem.Exception.EmptyListException;
import com.example.VaccinationBookingSystem.Exception.UserNotFoundException;
import com.example.VaccinationBookingSystem.Model.User;
import com.example.VaccinationBookingSystem.Repository.UserRepository;
import com.example.VaccinationBookingSystem.Service.UserService;
import com.example.VaccinationBookingSystem.Transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        //converting DTO to entity
//       User user=new User();
//       user.setName(userRequestDto.getName());
//       user.setAge(userRequestDto.getAge());
//       user.setEmailId(userRequestDto.getEmailId());
//       user.setMobNo(userRequestDto.getMobNo());
//       user.setGender(userRequestDto.getGender());
        User user = UserTransformer.userRequestDtoToUser(userRequestDto);

        //saving user object
        User savedUser = userRepository.save(user);

        //converting entity to Dto
//        UserResponseDto userResponseDto=new UserResponseDto();
//        userResponseDto.setName(savedUser.getName());
//        userResponseDto.setMessage("User added successfully!!");
        UserResponseDto userResponseDto = UserTransformer.userToUserResponseDto(savedUser);
        return userResponseDto;
    }

    @Override
    public UserGetResponseDto getUserByMailId(String emailId) throws UserNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        if (user == null) {
            throw new UserNotFoundException("Invalid MailId");
        }
        UserGetResponseDto userGetResponseDto = UserTransformer.userToUserGetResponseDto(user);
        return userGetResponseDto;

    }

    @Override
    public UserGetResponseDto updateUserNameWithMobNo(String mobNo, String name) throws UserNotFoundException {
        User user = userRepository.findByMobNo(mobNo);
        if (user == null) {
            throw new UserNotFoundException("Invalid Mobile Number");
        }
        user.setName(name);
        User savedUser = userRepository.save(user);
        UserGetResponseDto userGetResponseDto = UserTransformer.userToUserGetResponseDto(savedUser);
        return userGetResponseDto;
    }

    @Override
    public UserGetResponseDto updateUserNameWithEmailId(String emailId, String name) throws UserNotFoundException {
        User user = userRepository.findByEmailId(emailId);
        if (user == null) {
            throw new UserNotFoundException("Invalid EmailId");
        }
        user.setName(name);
        User savedUser = userRepository.save(user);
        UserGetResponseDto userGetResponseDto = UserTransformer.userToUserGetResponseDto(savedUser);
        return userGetResponseDto;
    }

    @Override
    public List<String> getAllNonVaccinatedUsers() throws EmptyListException {
        List<String> nonVaccinatedUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (!user.isDose1Taken() && !user.isDose2Taken()) {
                nonVaccinatedUsers.add(user.getName());
            }
        }
        if (nonVaccinatedUsers.size() == 0) {
            throw new EmptyListException("This list has No users with Non vaccinated Status!!");
        }
        return nonVaccinatedUsers;
    }

    @Override
    public List<String> getUsersWhoTookDose1AndNotDose2() {
        List<String> usersWhoTookDoseOneOnly = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.isDose1Taken() && !user.isDose2Taken()) {
                usersWhoTookDoseOneOnly.add(user.getName());
            }
        }
        return usersWhoTookDoseOneOnly;
    }

    @Override
    public List<String> getFullyVaccinatedUsers() throws EmptyListException {
        List<String> fullyVaccinatedUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.isDose2Taken() && user.isDose2Taken())
                fullyVaccinatedUsers.add(user.getName());
        }
        if (fullyVaccinatedUsers.size() == 0) {
            throw new EmptyListException("No User is Fully vaccinated till Now!!");
        }
        return fullyVaccinatedUsers;
    }

    @Override
    public List<String> getAllFemaleUsers() {
        List<String> femaleUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getGender().equals(Gender.FEMALE)) {
                femaleUsers.add(user.getName());
            }
        }
        return femaleUsers;
    }

    @Override
    public List<String> getAllMaleUsers() {
        List<String> maleUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getGender().equals(Gender.MALE)) {
                maleUsers.add(user.getName());
            }
        }
        return maleUsers;
    }

    @Override
    public List<String> getNonVaccinatedMaleUsers() {
        List<String> nonVaccinatedMaleUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getGender().equals(Gender.MALE)) {
                if (!user.isDose2Taken() && !user.isDose2Taken()) {
                    nonVaccinatedMaleUsers.add(user.getName());
                }
            }
        }
        return nonVaccinatedMaleUsers;
    }

    @Override
    public List<String> getNonVaccinatedFemaleUsers() {
        List<String> nonVaccinatedFemaleUsers = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getGender().equals(Gender.FEMALE)) {
                if (!user.isDose2Taken() && !user.isDose2Taken()) {
                    nonVaccinatedFemaleUsers.add(user.getName());
                }
            }
        }
        return nonVaccinatedFemaleUsers;
    }

    @Override
    public List<String> getFullyVaccinatedFemaleUsers() {
        List<String> fullyVaccinatedFemaleUsers=new ArrayList<>();
        List<User> users=new ArrayList<>();
        for(User user:users){
            if(user.getGender().equals(Gender.FEMALE)){
                if(user.isDose1Taken() && user.isDose2Taken()){
                    fullyVaccinatedFemaleUsers.add(user.getName());
                }
            }
        }
        return fullyVaccinatedFemaleUsers;
    }

    @Override
    public List<String> getFullyVaccinatedMaleUsers() {
       List<String> fullyVaccinatedMaleUsers=new ArrayList<>();
       List<User> users=userRepository.findAll();
       for(User user:users){
           if (user.getGender().equals(Gender.MALE)){
               if(user.isDose1Taken() && user.isDose2Taken()){
                   fullyVaccinatedMaleUsers.add(user.getName());
               }
           }
       }
       return fullyVaccinatedMaleUsers;
    }
}