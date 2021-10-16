package com.leacar21.technical.seat.function.reservation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leacar21.technical.seat.function.reservation.dto.PortalDTO;
import com.leacar21.technical.seat.function.reservation.services.PortalService;

@RestController()
@RequestMapping(PortalController.PORTALS_RESOURCE)
public class PortalController {

    public static final String PORTALS_RESOURCE = "portals";

    @Autowired
    private PortalService portalService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PortalDTO> getAll() {
        return this.portalService.getAll();
    }

}
