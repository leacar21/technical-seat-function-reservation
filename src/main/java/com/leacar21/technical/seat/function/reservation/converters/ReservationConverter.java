package com.leacar21.technical.seat.function.reservation.converters;

import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.persistence.model.Reservation;

@Service
public class ReservationConverter extends AbstractConverter<ReservationDTO, Reservation> {

    @Override
    protected Reservation commonToEntity(ReservationDTO source) {
        return this.strictModelMapper.map(source, Reservation.class);
    }

    @Override
    protected ReservationDTO commonToDTO(Reservation source) {
        return this.strictModelMapper.map(source, ReservationDTO.class);
    }

}