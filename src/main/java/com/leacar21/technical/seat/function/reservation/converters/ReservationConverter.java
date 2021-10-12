package com.leacar21.technical.seat.function.reservation.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.persistence.model.Reservation;
import com.leacar21.technical.seat.function.reservation.utils.UUIDUtils;

@Service
public class ReservationConverter extends AbstractConverter<ReservationDTO, Reservation> {

    @Autowired
    private ReservationItemConverter reservationItemConverter;

    @Override
    protected Reservation commonToEntity(ReservationDTO source) {
        var reservation = this.strictModelMapper.map(source, Reservation.class);

        var portalUUID = UUIDUtils.getUUID(source.getPortal().getCode(), "Portal not found");
        reservation.getPortal().setCode(portalUUID);

        reservation.setItems(this.reservationItemConverter.toEntity(source.getItems()));

        return reservation;
    }

    @Override
    protected ReservationDTO commonToDTO(Reservation source) {
        return this.strictModelMapper.map(source, ReservationDTO.class);
    }

}