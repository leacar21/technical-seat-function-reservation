package com.leacar21.technical.seat.function.reservation.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.converters.PortalConverter;
import com.leacar21.technical.seat.function.reservation.dto.PortalDTO;
import com.leacar21.technical.seat.function.reservation.persistence.dao.PortalDAO;
import com.leacar21.technical.seat.function.reservation.services.PortalService;

@Service
public class PortalServiceImpl implements PortalService {

    Logger log = LoggerFactory.getLogger(PortalServiceImpl.class);

    @Autowired
    private PortalDAO portalDAO;

    @Autowired
    private PortalConverter portalConverter;

    @Override
    public List<PortalDTO> getAll() {
        var listPortal = this.portalDAO.findAll();
        return this.portalConverter.toDTO(listPortal);
    }

}
