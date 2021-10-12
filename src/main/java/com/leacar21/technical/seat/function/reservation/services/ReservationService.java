package com.leacar21.technical.seat.function.reservation.services;

import java.util.List;

import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;

public interface ReservationService {

    List<ReservationDTO> getAll();

    ReservationDTO create(ReservationDTO reservation);

}
