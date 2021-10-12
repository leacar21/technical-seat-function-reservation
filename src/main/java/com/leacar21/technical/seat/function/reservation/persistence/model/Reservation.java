package com.leacar21.technical.seat.function.reservation.persistence.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "reservation")
public class Reservation extends AbstractEntityJPA {

    @Column(name = "code", columnDefinition = "BINARY(16)")
    private UUID code;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "customer_dni")
    private String customerDni;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<ReservationItem> items;

    @ManyToOne(optional = false)
    @JoinColumn(name = "portal_id", nullable = false)
    private Portal portal;

}
