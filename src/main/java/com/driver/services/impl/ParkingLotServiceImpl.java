package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        parkingLot.setAddress(address);
        return parkingLotRepository1.save(parkingLot);
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository1.findById(parkingLotId);
        ParkingLot parkingLot = parkingLotOptional.get();

        Spot spot = new Spot();
        if (numberOfWheels<=2) spot.setSpotType(SpotType.TWO_WHEELER);
        else if (numberOfWheels>2 && numberOfWheels<=4) spot.setSpotType(SpotType.FOUR_WHEELER);
        else if (numberOfWheels>4) spot.setSpotType(SpotType.OTHERS);
        spot.setPricePerHour(pricePerHour);
        spot.setOccupied(false);
        spot = spotRepository1.save(spot);

        spot.setParkingLot(parkingLot);
        parkingLot.getSpotList().add(spot);

//        parkingLotRepository1.save(parkingLot);

        return spotRepository1.save(spot);

    }

    @Override
    public void deleteSpot(int spotId) {
        Spot spot = spotRepository1.findById(spotId).get();

        ParkingLot parkingLot = spot.getParkingLot();
        System.out.println(parkingLot);
        spotRepository1.delete(spot);
        System.out.println(parkingLot.getSpotList().contains(spot));
        parkingLot.getSpotList().remove(spot);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
        Spot spot = spotRepository1.findById(spotId).get();
        spot.setPricePerHour(pricePerHour);

        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLot.getSpotList().remove(spot);
        parkingLot.getSpotList().add(spot);
//        System.out.println(parkingLot.getSpotList().get(parkingLot.getSpotList().size()-1));

        return spotRepository1.save(spot);
    }

    @Override
    public void deleteParkingLot(int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository1.findById(parkingLotId).get();
        parkingLotRepository1.delete(parkingLot);
    }
}
