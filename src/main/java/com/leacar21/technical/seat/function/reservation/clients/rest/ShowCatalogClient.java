package com.leacar21.technical.seat.function.reservation.clients.rest;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.leacar21.technical.seat.function.reservation.clients.rest.api.SectionSeatDTO;
import com.leacar21.technical.seat.function.reservation.exceptions.ApiException;

@Component
public class ShowCatalogClient {

    Logger log = LoggerFactory.getLogger(ShowCatalogClient.class);

    @Autowired
    @Qualifier(ShowCatalogClientConfig.BEAN_NAME)
    private RestTemplate restClient;

    @Value("${service.shows.catalog.base.path}")
    private String basePath;

    @Value("${service.shows.catalog.base.path}")
    private String resourceShows;

    @Value("${service.shows.catalog.resource.section.seats}")
    private String resourceSectionSeats;

    // ------------------------------

    @Retryable(include = { ApiException.class, Exception.class }, //
            backoff = @Backoff(delay = 1000, maxDelay = 5000), //
            maxAttempts = 3)
    public SectionSeatDTO getSectionSeat(String code) {
        try {
            String url = this.basePath + this.resourceSectionSeats + "/" + code;
            return this.restClient.getForObject(url, SectionSeatDTO.class);
        } catch (Exception e) {
            this.log.warn("Show Catalog client error executing getSectionSeat. Details: {}", e.getMessage(), e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Recover
    public SectionSeatDTO recoverApiException(ApiException e, UUID String) {
        this.log.error("Show Catalog client error executing getSectionSeat. ApiException: {}", e.getMessage());
        throw e;
    }

    // ------------------------------

    @Retryable(include = { ApiException.class, Exception.class }, //
            backoff = @Backoff(delay = 1000, maxDelay = 5000), //
            maxAttempts = 3)
    public void updateSectionSeat(SectionSeatDTO sectionSeatDTO) {
        try {
            String url = this.basePath + this.resourceSectionSeats + "/" + sectionSeatDTO.getCode();
            HttpEntity<SectionSeatDTO> request = new HttpEntity<>(sectionSeatDTO);
            this.restClient.patchForObject(url, request, SectionSeatDTO.class);
        } catch (Exception e) {
            this.log.warn("Show client error executing updateSectionSeat. Details: {}", e.getMessage(), e);
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Recover
    public void recoverApiException(ApiException e, SectionSeatDTO sectionSeatDTO) {
        this.log.error("Show client error executing updateSectionSeat. ApiException: {}", e.getMessage());
        throw e;
    }

    // ------------------------------

}
