package com.leacar21.technical.seat.function.reservation.converters;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.leacar21.technical.seat.function.reservation.dto.PortalDTO;
import com.leacar21.technical.seat.function.reservation.exceptions.ResourceNotFoundException;
import com.leacar21.technical.seat.function.reservation.persistence.model.Portal;

@Service
public class PortalConverter extends AbstractConverter<PortalDTO, Portal> {

    @Override
    protected Portal commonToEntity(PortalDTO source) {
        var portal = this.strictModelMapper.map(source, Portal.class);
        UUID uuidCode = null;
        try {
            uuidCode = UUID.fromString(source.getCode());
        } catch (Exception exc) {
            throw new ResourceNotFoundException("Portal not found");
        }
        portal.setCode(uuidCode);
        return portal;
    }

    @Override
    protected PortalDTO commonToDTO(Portal source) {
        return this.strictModelMapper.map(source, PortalDTO.class);
    }

}