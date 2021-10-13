package com.leacar21.technical.seat.function.reservation.services.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.clients.rest.ShowCatalogClient;
import com.leacar21.technical.seat.function.reservation.clients.rest.api.SectionSeatDTO;
import com.leacar21.technical.seat.function.reservation.converters.ReservationConverter;
import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.dto.ReservationItemDTO;
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

    @Autowired
    private ShowCatalogClient showCatalogClient;

    @Override
    public List<ReservationDTO> getAll() {
        var listReservation = this.reservationDAO.findAll();
        return this.reservationConverter.toDTO(listReservation);
    }

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

        // Update sectionSeat
        // TODO: paralelizar (y evaluar asincronismo)
        for (ReservationItemDTO item : reservationDTO.getItems()) {
            var sectionSeatDTO = SectionSeatDTO.builder().code(item.getSectionSeatCode()).available(false).build();
            this.showCatalogClient.updateSectionSeat(sectionSeatDTO);
        }

        return this.reservationConverter.toDTO(savedReservation);
    }

}
