package com.example.VaccinationBookingSystem.Transformer;

import com.example.VaccinationBookingSystem.Dto.RequestDto.UserRequestDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserGetResponseDto;
import com.example.VaccinationBookingSystem.Dto.ResponseDto.UserResponseDto;
import com.example.VaccinationBookingSystem.Model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransformer {
    public static User userRequestDtoToUser(UserRequestDto userRequestDto){
        return User.builder()
                .name(userRequestDto.getName())
                .gender(userRequestDto.getGender())
                .age(userRequestDto.getAge())
                .mobNo(userRequestDto.getMobNo())
                .emailId(userRequestDto.getEmailId())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user){
        return UserResponseDto.builder()
                .name(user.getName())
                .message("User Added Successfully!!")
                .build();
    }

    public static UserGetResponseDto userToUserGetResponseDto(User user){
        return UserGetResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .mobNo(user.getMobNo())
                .emailId(user.getEmailId())
                .gender(user.getGender())
                .build();
    }
}
