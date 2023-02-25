package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.DTOS.TheatreEntryDto;
import com.example.BookMyShow.Models.TheatreEntity;

public class TheatreConvertor {

    public static TheatreEntity convertDtoToEntity(TheatreEntryDto theatreEntryDto){

        TheatreEntity theatreEntity = TheatreEntity.builder().name(theatreEntryDto.getName()).location(theatreEntryDto.getLocation()).build();
        return theatreEntity;
    }
}
