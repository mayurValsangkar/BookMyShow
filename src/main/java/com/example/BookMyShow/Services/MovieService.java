package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.MovieConvertor;
import com.example.BookMyShow.DTOS.MovieEntryDto;
import com.example.BookMyShow.Models.MovieEntity;
import com.example.BookMyShow.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieConvertor.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movieEntity);
    }


}
