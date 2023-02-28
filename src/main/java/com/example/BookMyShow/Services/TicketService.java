package com.example.BookMyShow.Services;


import com.example.BookMyShow.Convertors.TicketConvertor;
import com.example.BookMyShow.DTOS.TicketEntryDto;
import com.example.BookMyShow.Models.ShowEntity;
import com.example.BookMyShow.Models.ShowSeatEntity;
import com.example.BookMyShow.Models.TicketEntity;
import com.example.BookMyShow.Models.UserEntity;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TicketRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public void bookTicket(TicketEntryDto ticketEntryDto) throws Exception{


        TicketEntity ticketEntity = TicketConvertor.convertDtoToEntity(ticketEntryDto);

        // Validation : Check if the requested seats are available or not
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);

        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }

        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();

        List<ShowSeatEntity> seatEntityList = showEntity.getListOfShowSeats();

        // List of seats that we need to book
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        // Calculating total price of all tickets
        double totalAmount = 0;
        for(ShowSeatEntity showSeatEntity : seatEntityList){

            if(requestedSeats.contains(showSeatEntity.getSeatNo())){
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        // Setting unique id
        ticketEntity.setTicketId(UUID.randomUUID().toString());

        // Setting price
        ticketEntity.setPrice(totalAmount);

        // Setting the other attribute for ticket entity
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTiming(showEntity.getShowTime());
        ticketEntity.setTheatreName(showEntity.getTheatreEntity().getName());


        // We need to set that string that talked about requested seats
        String allocatedSeats = getAllocatedSeatsFromSeats(requestedSeats);
        ticketEntity.setBookedSeats(allocatedSeats);


        // Setting the foreign key attribute
        UserEntity  userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        // Save the parent(ShowEntity)
        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        showEntity.setListOfBookedTickets(ticketEntityList);
        showRepository.save(showEntity);


        // Save the parent(UserEntity)
        List<TicketEntity> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticketEntity);
        userEntity.setBookedTickets(ticketEntityList1);
        userRepository.save(userEntity);

    }

    // Converting List to single string
    private String getAllocatedSeatsFromSeats(List<String> requestedSeats){

        String result = "";

        for(String seat : requestedSeats){
            result = result + seat + ",";
        }

        return result;
    }

    // Validation check
    public boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){

        int showId = ticketEntryDto.getShowId();

        // List of seats that we need to book
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        // Get show entity
        ShowEntity showEntity = showRepository.findById(showId).get();

        List<ShowSeatEntity> showSeatEntityList = showEntity.getListOfShowSeats();

        // Iterating over the list of seats for that particular show
        for(ShowSeatEntity showSeatEntity : showSeatEntityList){

            String seatNo = showSeatEntity.getSeatNo();

            if(requestedSeats.contains(seatNo)){

                if(showSeatEntity.isBooked()==true){
                    return false; // Since this seat is already booked
                }
            }
        }

        // All the seats requested are available
        return true;
    }
}
