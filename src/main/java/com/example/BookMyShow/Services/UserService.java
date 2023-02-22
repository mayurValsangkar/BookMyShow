package com.example.BookMyShow.Services;

import com.example.BookMyShow.DTOS.UserEntryDto;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser(UserEntryDto userEntryDto){

        // This is to set all the attribute in one go.
        // Creating object in smart manner
        UserEntity userEntity = UserEntity.builder()
                .age(userEntryDto.getAge())
                .name(userEntryDto.getName())
                .address(userEntryDto.getAddress())
                .email(userEntryDto.getEmail())
                .mobileNumber(userEntryDto.getMobileNumber())
                .build();

        userRepository.save(userEntity);
    }
}