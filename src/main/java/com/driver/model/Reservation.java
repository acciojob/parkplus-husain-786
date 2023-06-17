package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer noOfHours;

    @ManyToOne
    @JoinColumn
    private User user;

    //    @OneToOne
    //    @JoinTable
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JoinColumn
    private Spot spot;

    public Reservation() {
    }

    public Reservation(Integer id, Integer noOfHours, User user, Payment payment, Spot spot) {
        this.id = id;
        this.noOfHours = noOfHours;
        this.user = user;
        this.payment = payment;
        this.spot = spot;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(Integer noOfHours) {
        this.noOfHours = noOfHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
}
