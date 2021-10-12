package com.leacar21.technical.seat.function.reservation.persistence.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leacar21.technical.seat.function.reservation.persistence.dao.repository.ReservationRepository;
import com.leacar21.technical.seat.function.reservation.persistence.model.Reservation;

@Repository
public class ReservationDAO {

    @Autowired
    private ReservationRepository repository;

    @Transactional(readOnly = true)
    public Reservation findByCode(UUID code) {
        Optional<Reservation> optionalReservation = this.repository.findByCode(code);
        return optionalReservation.isPresent() ? optionalReservation.get() : null;
    }

    @Transactional(readOnly = false)
    public Reservation save(Reservation entity) {
        return this.repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findAll() {
        return this.repository.findAll();
    }

}
