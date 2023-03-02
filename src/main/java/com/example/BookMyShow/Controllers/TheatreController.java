package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.DTOS.TheatreEntryDto;
import com.example.BookMyShow.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreEntryDto theatreEntryDto){

        try {
            theatreService.addTheatre(theatreEntryDto);
        }catch (Exception e){
            return new ResponseEntity<>("Theatre could not added", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Theatre added successfully", HttpStatus.CREATED);
    }
}
