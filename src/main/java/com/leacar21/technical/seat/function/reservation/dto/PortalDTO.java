package com.leacar21.technical.seat.function.reservation.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortalDTO {

    @NotBlank
    private String code;

    private String name;

}
