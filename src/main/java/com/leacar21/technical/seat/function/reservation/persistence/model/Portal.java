package com.leacar21.technical.seat.function.reservation.persistence.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "portal")
public class Portal extends AbstractEntityJPA {

    @Column(name = "code", columnDefinition = "BINARY(16)")
    private UUID code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "portal")
    private List<Reservation> reservations;

}
