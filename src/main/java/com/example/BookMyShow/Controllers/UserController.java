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
    public ResponseEntity addUser(@RequestBody UserEntryDto userEntryDto){
        userService.addUser(userEntryDto);
        return new ResponseEntity<>("User added successfully", HttpStatus.CREATED);
    }

}
