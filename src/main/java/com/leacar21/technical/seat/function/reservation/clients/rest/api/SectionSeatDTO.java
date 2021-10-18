package com.leacar21.technical.seat.function.reservation.clients.rest.api;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionSeatDTO {

    private String code;

    @NotNull
    private Boolean available;

    private String seatCode;

    private String sectionCode;

}
