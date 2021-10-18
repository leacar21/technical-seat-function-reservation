package com.leacar21.technical.seat.function.reservation.converters;

import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.dto.ReservationItemDTO;
import com.leacar21.technical.seat.function.reservation.persistence.model.ReservationItem;
import com.leacar21.technical.seat.function.reservation.utils.UUIDUtils;

@Service
public class ReservationItemConverter extends AbstractConverter<ReservationItemDTO, ReservationItem> {

    @Override
    protected ReservationItem commonToEntity(ReservationItemDTO source) {
        var reservationItem = this.strictModelMapper.map(source, ReservationItem.class);

        var sectionCodeUUID = UUIDUtils.getUUID(source.getSectionSeatCode(), "Section Code not found");
        reservationItem.setSectionSeatCode(sectionCodeUUID);

        return reservationItem;
    }

    @Override
    protected ReservationItemDTO commonToDTO(ReservationItem source) {
        return this.strictModelMapper.map(source, ReservationItemDTO.class);
    }

}