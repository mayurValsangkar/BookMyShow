package com.example.BookMyShow.Services;

import com.example.BookMyShow.Convertors.ShowConvertor;
import com.example.BookMyShow.DTOS.ShowEntryDto;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;

    public void addShow(ShowEntryDto showEntryDto){

        ShowEntity showEntity = ShowConvertor.convertDtoToEntity(showEntryDto);

        int movieId = showEntryDto.getMovieId();

        int theatreId = showEntryDto.getTheatreId();

        MovieEntity movieEntity = movieRepository.findById(movieId).get();
        TheatreEntity theatreEntity = theatreRepository.findById(theatreId).get();

        // Setting the attribute of foreign key
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheatreEntity(theatreEntity);


        // pending attribute the listOfShowSeatsEntity
        List<ShowSeatEntity> showSeatEntityList = createShowSeatEntity(showEntryDto, showEntity);
        showEntity.setListOfShowSeats(showSeatEntityList);


        // Now we also need to update the parent entities
        // To avoid duplicate entry, we are saving show entity
        showEntity = showRepository.save(showEntity);

        movieEntity.getShowEntityList().add(showEntity);
        theatreEntity.getShowEntityList().add(showEntity);

        movieRepository.save(movieEntity);
        theatreRepository.save(theatreEntity);
    }

    public List<ShowSeatEntity> createShowSeatEntity(ShowEntryDto showEntryDto, ShowEntity showEntity){

        // Now the goal is to create the ShowSeatEntity
        // We need to set its attribute

        TheatreEntity theatreEntity = showEntity.getTheatreEntity();

        List<TheatreSeatEntity> theatreSeatEntityList = theatreEntity.getTheatreSeatEntityList();

        List<ShowSeatEntity> seatEntityList = new ArrayList<>();

        for (TheatreSeatEntity theatreSeatEntity : theatreSeatEntityList){

            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theatreSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theatreSeatEntity.getSeatType());

            if(theatreSeatEntity.getSeatType().equals(SeatType.SILVER)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }else {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }

            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity); // for the showSeatEntity set the parent.

            seatEntityList.add(showSeatEntity);
        }

        return seatEntityList;
    }
}
