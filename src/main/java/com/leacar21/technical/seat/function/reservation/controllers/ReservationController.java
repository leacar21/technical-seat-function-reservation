package com.leacar21.technical.seat.function.reservation.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leacar21.technical.seat.function.reservation.constants.OAuthScopes;
import com.leacar21.technical.seat.function.reservation.dto.ReservationDTO;
import com.leacar21.technical.seat.function.reservation.services.ReservationService;

@RestController()
@RequestMapping(ReservationController.RESERVATIONS_RESOURCE)
public class ReservationController {

    public static final String RESERVATIONS_RESOURCE = "/reservations";

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#oauth2.hasAnyScope('" + OAuthScopes.SCOPE_PORTAL + "')")
    public ReservationDTO create(@Valid @RequestBody(required = true) ReservationDTO reservationDTO) {
        return this.reservationService.create(reservationDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getAll() {
        return this.reservationService.getAll();
    }

}
