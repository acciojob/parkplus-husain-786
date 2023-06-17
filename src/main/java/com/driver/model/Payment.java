package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private PaymentMode paymentMode;
    private Boolean isPaymentCompleted;

    @OneToOne
    @JoinColumn
    private Reservation reservation;

    public Payment() {
    }
    public Payment(Integer id, PaymentMode paymentMode, Boolean isPaymentCompleted, Reservation reservation) {
        this.id = id;
        this.paymentMode = paymentMode;
        this.isPaymentCompleted = isPaymentCompleted;
        this.reservation = reservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Boolean getIsPaymentCompleted() {
        return isPaymentCompleted;
    }

    public void setIsPaymentCompleted(Boolean isPaymentCompleted) {
        this.isPaymentCompleted = isPaymentCompleted;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
