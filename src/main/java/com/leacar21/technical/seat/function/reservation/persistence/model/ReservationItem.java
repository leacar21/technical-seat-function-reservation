package com.leacar21.technical.seat.function.reservation.persistence.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation_item")
public class ReservationItem extends AbstractEntityJPA {

    @Column(name = "code", columnDefinition = "BINARY(16)")
    private UUID code;

    @Column(name = "price_paid")
    private BigDecimal pricePaid;

    @Column(name = "section_seat_code")
    private UUID sectionSeatCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

}
