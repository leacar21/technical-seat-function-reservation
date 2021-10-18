package com.leacar21.technical.seat.function.reservation.persistence.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.leacar21.technical.seat.function.reservation.persistence.dao.repository.PortalRepository;
import com.leacar21.technical.seat.function.reservation.persistence.model.Portal;

@Repository
public class PortalDAO {

    @Autowired
    private PortalRepository repository;

    @Transactional(readOnly = true)
    public List<Portal> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public Portal findByCode(UUID code) {
        Optional<Portal> optionalPortal = this.repository.findByCode(code);
        return optionalPortal.isPresent() ? optionalPortal.get() : null;
    }

}
