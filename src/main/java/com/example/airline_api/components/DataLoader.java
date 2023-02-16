package com.example.airline_api.components;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passRepo;
    @Autowired
    FlightRepository flightRepo;


    @Override
    public void run(ApplicationArguments args) throws Exception{

        //James passenger
        Passenger James = new Passenger("James","james@bnta.com");
        passRepo.save(James);

        //Jack passenger
        Passenger Jack = new Passenger("Jack","jack@warick.com");
        passRepo.save(Jack);

        //Flight to London
        Flight toLondon = new Flight("London",300,"April 20th","10am");
        flightRepo.save(toLondon);

    }
}
