package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        User user = userRepository3.findById(userId).get();
        if (user == null){
            throw new Exception("Cannot make reservation");
        }

        ParkingLot parkingLot = parkingLotRepository3.findById(parkingLotId).get();
        if (parkingLot == null){
            throw new Exception("Cannot make reservation");
        }

//        SpotType spotType = null;
//        if (numberOfWheels<=2) spotType = SpotType.TWO_WHEELER;
//        if (numberOfWheels>2 && numberOfWheels<=4)  spotType=SpotType.FOUR_WHEELER;
//        if (numberOfWheels>4) spotType=SpotType.OTHERS;

        if (parkingLot.getSpotList().size() > 0) {
            Spot toAllot = parkingLot.getSpotList().get(0);
        }
        else{
            throw new Exception("Cannot make reservation");
        }

        int x=Integer.MAX_VALUE;
        Spot minSpot = new Spot();
        boolean isSpotEmpty = false;
        for (Spot spot: parkingLot.getSpotList()){
            int wheels = 0;
            if (spot.getSpotType().equals(SpotType.OTHERS)) wheels=5;
            if (spot.getSpotType().equals(SpotType.FOUR_WHEELER)) wheels=4;
            if (spot.getSpotType().equals(SpotType.TWO_WHEELER)) wheels=4;
            System.out.println("Working@!!!");
            if (spot.getOccupied() == false && numberOfWheels <= wheels && wheels<x){
                System.out.println("Hi123");
                x = wheels;
                minSpot = spot;
                isSpotEmpty = true;
            }
        }


        System.out.println("Hi");
        if (!isSpotEmpty){
            throw new Exception("Cannot make reservation");
        }

        System.out.println("Hello"+minSpot.getId()+"==="+x);

        Reservation reservation = new Reservation();
        System.out.println("Hours:- "+timeInHours);
        reservation = reservationRepository3.save(reservation);

//        minSpot.setOccupied(true);

        user.getReservationList().add(reservation);
        minSpot.getReservationList().add(reservation);
        reservation.setSpot(minSpot);
        reservation.setUser(user);
        reservation.setNumberOfHours(timeInHours);

        return reservationRepository3.save(reservation);
    }
}
