package com.example.BookMyShow.Services;

import com.example.BookMyShow.Repositories.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;
}
