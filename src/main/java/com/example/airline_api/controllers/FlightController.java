package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.service.FlightService;
import com.example.airline_api.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;
    @Autowired
    PassengerService passService;

    // Display all available flights
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(){
        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.OK);
    }

    // Display a specific flight - checking whether there is the right flight here
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        if (flightService.getFlightById(id).isPresent()) {
            HttpStatus status = HttpStatus.FOUND;
            return new ResponseEntity<>(flightService.getFlightById(id).get(), status);
        } else {
            return new ResponseEntity<>(flightService.getFlightById(id).get(),HttpStatus.NOT_FOUND);
        }
    }

    // Add details of a new flight
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Flight flight){
        flightService.addFlight(flight);
        return new ResponseEntity<>(flight,HttpStatus.CREATED);
    }

    // Book passenger on a flight
    @PatchMapping(value = "/{flightId}")
    public ResponseEntity<Flight> addPassengerToFlight(@PathVariable long flightId, @RequestParam long passId){
        //check not on the flight already or flight is full
        if(flightService.getFlightById(flightId).get().getPassengers().contains(passService.getPassById(passId).get())
                ||
                flightService.isFlightFull(flightId)){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //check they're a real person and flight
        else if(flightService.getFlightById(flightId).isPresent() && passService.getPassById(passId).isPresent()){
            flightService.addPassToFlight(flightId,passId);
            return new ResponseEntity<>(flightService.getFlightById(flightId).get(),HttpStatus.OK );
        } else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    // Cancel flight
    @DeleteMapping(value = "/{id}")
    public ResponseEntity cancelFlight(@PathVariable long id){
        if (flightService.getFlightById(id).isPresent()) {
            flightService.deleteFlightById(id);
            return new ResponseEntity<>("Flight Deleted",HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("Flight not found",HttpStatus.NOT_FOUND);
        }
    }

}
