package com.leacar21.technical.seat.function.reservation.persistence.dao.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leacar21.technical.seat.function.reservation.persistence.model.Portal;

public interface PortalRepository extends JpaRepository<Portal, Long> {

    Optional<Portal> findByCode(UUID code);

}