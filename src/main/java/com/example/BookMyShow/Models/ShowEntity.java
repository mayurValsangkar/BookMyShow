package com.example.BookMyShow.Models;


import com.example.BookMyShow.Enums.ShowType;
import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Timestamp(value = "")
    private LocalDate showDate;

    private LocalTime showTime;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    // This is child wrt to the movieEntity
    @ManyToOne
    @JoinColumn
    private MovieEntity movieEntity;

    // This is child wrt theatre
    @ManyToOne
    @JoinColumn
    private TheatreEntity theatreEntity;

    // This is parent wrt ticketEntity
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<TicketEntity> ticketEntityList = new ArrayList<>();

    // This is parent wrt showSeatEntity
    @OneToMany(mappedBy = "showEntity", cascade = CascadeType.ALL)
    private List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();
}
