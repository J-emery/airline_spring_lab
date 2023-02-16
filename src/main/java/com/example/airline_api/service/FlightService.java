package com.example.airline_api.service;

import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepo;
    @Autowired
    PassengerRepository passRepo;

//Get all flights
    public List<Flight> getAllFlights(){
        return flightRepo.findAll();
    }

//Get a flight by id
    public Optional<Flight> getFlightById(Long id){
        return flightRepo.findById(id);
    }

//Add a flight
    public void addFlight(Flight flight){
        flightRepo.save(flight);
    }

//Delete a flight
    public void deleteFlightById(long id){
        flightRepo.deleteById(id);
    }

//check if flight is full
    public boolean isFlightFull(long id){
        Flight flight = flightRepo.findById(id).get();
        return flight.getCapacity() < flight.getPassengers().size();
    }

//Add a passenger to a flight
    //make DTO for the flight
    //add a passenger onto that flight
    //
    public void addPassToFlight(long flightId,long passId){
        //get flight and passenger
        Flight flight = flightRepo.findById(flightId).get();
        Passenger passenger = passRepo.findById(passId).get();

        //get list of passengers and add on new passenger by id
        flight.getPassengers().add(passenger);
        flightRepo.save(flight);

        //do same for passenger
        passenger.getFlights().add(flight);
        passRepo.save(passenger);
    }
}
