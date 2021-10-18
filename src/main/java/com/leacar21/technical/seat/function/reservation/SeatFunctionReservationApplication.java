package com.leacar21.technical.seat.function.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
public class SeatFunctionReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeatFunctionReservationApplication.class, args);
    }

}
