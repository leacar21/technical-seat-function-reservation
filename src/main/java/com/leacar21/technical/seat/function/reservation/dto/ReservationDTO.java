package com.leacar21.technical.seat.function.reservation.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDTO {

    private String code;

    private String ticketNumber;

    @NotBlank
    private String customerDni;

    @NotBlank
    private String customerName;

    @NotEmpty
    private List<ReservationItemDTO> items;

    @NotNull
    private PortalDTO portal;
}
