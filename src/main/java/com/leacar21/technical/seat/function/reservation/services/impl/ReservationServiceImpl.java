package com.leacar21.technical.seat.function.reservation.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leacar21.technical.seat.function.reservation.converters.ReservationConverter;
import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.exceptions.ConflictException;
import com.leacar21.technical.seat.function.reservation.persistence.dao.PortalDAO;
import com.leacar21.technical.seat.function.reservation.persistence.dao.ReservationDAO;
import com.leacar21.technical.seat.function.reservation.persistence.model.Reservation;
import com.leacar21.technical.seat.function.reservation.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

    Logger log = LoggerFactory.getLogger(ReservationServiceImpl.class);

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private PortalDAO portalDAO;

    @Autowired
    private ReservationConverter reservationConverter;

    @Override
    public List<ReservationDTO> getAll() {
        var listReservation = this.reservationDAO.findAll();
        return this.reservationConverter.toDTO(listReservation);
    }

    @Transactional(readOnly = false)
    @Override
    public ReservationDTO create(ReservationDTO reservationDTO) {

        final Reservation reservation = this.reservationConverter.toEntity(reservationDTO);
        reservation.setCode(UUID.randomUUID());
        reservation.getItems().forEach(i -> {
            i.setCode(UUID.randomUUID());
            i.setReservation(reservation);
        });
        reservation.setTicketNumber(RandomStringUtils.random(20, true, true));

        var portal = this.portalDAO.findByCode(reservation.getPortal().getCode());
        reservation.setPortal(portal);

        Reservation savedReservation = null;
        try {
            savedReservation = this.reservationDAO.save(reservation);
        } catch (DataIntegrityViolationException exc) {
            throw new ConflictException("Seat is not available");
        }

        // TODO: Update sectionSeat

        return this.reservationConverter.toDTO(savedReservation);
    }

}
