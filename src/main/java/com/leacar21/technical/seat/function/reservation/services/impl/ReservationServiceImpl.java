package com.leacar21.technical.seat.function.reservation.services.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.converters.ReservationConverter;
import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.exceptions.ConflictException;
import com.leacar21.technical.seat.function.reservation.persistence.dao.ReservationDAO;
import com.leacar21.technical.seat.function.reservation.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private ReservationConverter reservationConverter;

    @Override
    public List<ReservationDTO> getAll() {
        var listReservation = this.reservationDAO.findAll();
        return this.reservationConverter.toDTO(listReservation);
    }

    @Override
    public ReservationDTO create(ReservationDTO reservationDTO) {

        var reservation = this.reservationConverter.toEntity(reservationDTO);
        reservation.setCode(UUID.randomUUID());
        reservation.getItems().stream().forEach(i -> i.setCode(UUID.randomUUID()));

        try {
            reservation = this.reservationDAO.save(reservation);
        } catch (Exception exc) {
            throw new ConflictException("Seat is not available");
        }

        // TODO: Update sectionSeat

        return this.reservationConverter.toDTO(reservation);
    }

}
