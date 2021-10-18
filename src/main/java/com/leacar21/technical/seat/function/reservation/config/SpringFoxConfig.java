package com.leacar21.technical.seat.function.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leacar21.technical.seat.function.reservation.controllers.PortalController;
import com.leacar21.technical.seat.function.reservation.controllers.ReservationController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        var docket = new Docket(DocumentationType.SWAGGER_2);
        return docket.select()
                     .apis(RequestHandlerSelectors.any())
                     .paths(PathSelectors.ant("/" + ReservationController.RESERVATIONS_RESOURCE)
                                         .or(PathSelectors.ant("/" + PortalController.PORTALS_RESOURCE)))
                     .build();
    }
}
