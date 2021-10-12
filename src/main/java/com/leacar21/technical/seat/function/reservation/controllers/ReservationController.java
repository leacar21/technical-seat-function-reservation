package com.leacar21.technical.seat.function.reservation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.services.ReservationService;

@RestController()
@RequestMapping("reservations")
public class ReservationController {

    Logger log = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public ReservationDTO create(@Valid @RequestBody(required = true) ReservationDTO reservationDTO) {
        return this.reservationService.create(reservationDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getAll() {
        return this.reservationService.getAll();
    }

}