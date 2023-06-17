package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private SpotType spotType;
    private Integer pricePerHour;
    private Boolean occupied;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private ParkingLot parkingLot;

    public Spot() {
    }

    public Spot(Integer id, SpotType spotType, Integer pricePerHour, Boolean occupied, List<Reservation> reservationList, ParkingLot parkingLot) {
        this.id = id;
        this.spotType = spotType;
        this.pricePerHour = pricePerHour;
        this.occupied = occupied;
        this.reservationList = reservationList;
        this.parkingLot = parkingLot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }
}
