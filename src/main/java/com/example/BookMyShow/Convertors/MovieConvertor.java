package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.DTOS.MovieEntryDto;
import com.example.BookMyShow.Models.MovieEntity;

public class MovieConvertor {

    public static MovieEntity convertDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).duration(movieEntryDto.getDuration()).genre(movieEntryDto.getGenre()).language(movieEntryDto.getLanguage()).ratings(movieEntryDto.getRatings()).build();
        return movieEntity;
    }
}
