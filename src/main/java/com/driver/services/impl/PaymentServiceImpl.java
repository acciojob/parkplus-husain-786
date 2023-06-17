package com.driver.services.impl;

import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {
        Reservation reservation = reservationRepository2.findById(reservationId).get();
        if (!(PaymentMode.CARD).equals(mode) || !(PaymentMode.UPI).equals(mode) || !(PaymentMode.CASH).equals(mode)){
            throw new Exception("Payment mode not detected");
        }

        int hrs = reservation.getNumberOfHours();
        int amountPerHour = reservation.getSpot().getPricePerHour();
        int amountToPay = hrs*amountPerHour;

        if (amountToPay > amountSent){
            throw new Exception("Insufficient Amount");
        }

        Payment payment = new Payment();
        payment.setPaymentMode(PaymentMode.valueOf(mode));
        payment.setReservation(reservation);
        payment.setPaymentCompleted(true);

        reservation.setPayment(payment);

        return paymentRepository2.save(payment);
    }
}
