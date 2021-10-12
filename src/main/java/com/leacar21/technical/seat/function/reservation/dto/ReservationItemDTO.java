package com.leacar21.technical.seat.function.reservation.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationItemDTO {

    private String code;

    private BigDecimal pricePaid;

    @NotBlank
    private String sectionSeatCode;

}
