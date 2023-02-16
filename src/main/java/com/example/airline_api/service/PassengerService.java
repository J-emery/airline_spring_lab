package com.example.airline_api.service;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passRepo;

//Return all passengers
    public List<Passenger> getAllPass(){
        return passRepo.findAll();
    }

//Return a passenger by id
    public Optional<Passenger> getPassById(long id){
        return passRepo.findById(id);
    }

//add passenger
    public void addPass(Passenger passenger){
        passRepo.save(passenger);
    }
}
