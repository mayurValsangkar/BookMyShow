package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.DTOS.MovieEntryDto;
import com.example.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try {
            movieService.addMovie(movieEntryDto);
        }catch (Exception e){
            return new ResponseEntity<>("Movie could not added", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }
}
