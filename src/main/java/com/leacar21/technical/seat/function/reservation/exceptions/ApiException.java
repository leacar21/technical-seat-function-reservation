package com.leacar21.technical.seat.function.reservation.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String causes;

    public HttpStatus getStatus() {
        return this.status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getCauses() {
        return this.causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

}