package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.TheatreConvertor;
import com.example.BookMyShow.DTOS.TheatreEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.TheatreEntity;
import com.example.BookMyShow.Models.TheatreSeatEntity;
import com.example.BookMyShow.Repositories.TheatreRepository;
import com.example.BookMyShow.Repositories.TheatreSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatRepository theatreSeatRepository;

    // 1. Create theatre seats
    // 2. I need to save theatre : I need theatreEntity
    // 3. Always set the attribute before saving.

    public void addTheatre(TheatreEntryDto theatreEntryDto){

        TheatreEntity theatreEntity = TheatreConvertor.convertDtoToEntity(theatreEntryDto);

        List<TheatreSeatEntity> theatreSeatEntityList = createTheatreSeats(theatreEntryDto, theatreEntity);

    }

    private List<TheatreSeatEntity> createTheatreSeats(TheatreEntryDto theatreEntryDto, TheatreEntity theatreEntity) {

        int noClassicSeats = theatreEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theatreEntryDto.getPremiumSeatsCount();

        List<TheatreSeatEntity> theatreSeatEntityList = new ArrayList<>();

        for(int count = 1; count <= noClassicSeats;count++){

            // We need to make a new TheatreSeatEntity

            TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder()
                    .seatType(SeatType.SILVER).seatNo(String.valueOf(count+"S")).theatreEntity(theatreEntity).build();

            theatreSeatEntityList.add(theatreSeatEntity);
        }

        // Create premium seats
        for(int count = 1; count <= noPremiumSeats; count++){

            TheatreSeatEntity theatreSeatEntity = TheatreSeatEntity.builder().
                    seatType(SeatType.GOLD).seatNo(String.valueOf(count+"G")).theatreEntity(theatreEntity).build();

            theatreSeatEntityList.add(theatreSeatEntity);
        }

        theatreSeatRepository.saveAll(theatreSeatEntityList);

        return theatreSeatEntityList;
    }
}
