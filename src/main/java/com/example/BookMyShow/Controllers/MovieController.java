package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.DTOS.MovieEntryDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.ResponseDto.MovieResponseDto;
import com.example.BookMyShow.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDto movieEntryDto){

        try {
            movieService.addMovie(movieEntryDto);
        }catch (Exception e){
            return new ResponseEntity<>("Movie could not added", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Movie added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get-movie")
    public ResponseEntity<MovieResponseDto> getMovieById(@RequestParam("id") Integer id) {

        MovieResponseDto movieResponseDto = movieService.findMovie(id);
//        try {
//            movieResponseDto = movieService.findMovie(id);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }

        return new ResponseEntity<>(movieResponseDto, HttpStatus.ACCEPTED);
    }
}
