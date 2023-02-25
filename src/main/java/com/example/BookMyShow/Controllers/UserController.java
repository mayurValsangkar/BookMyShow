package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.DTOS.UserEntryDto;
import com.example.BookMyShow.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){

        try{
            userService.addUser(userEntryDto);
        }catch (Exception e){
            return new ResponseEntity<>("User could not add successfully", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);

    }
}
