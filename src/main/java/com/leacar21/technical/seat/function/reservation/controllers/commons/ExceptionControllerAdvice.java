package com.leacar21.technical.seat.function.reservation.controllers.commons;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leacar21.technical.seat.function.reservation.dto.commons.APIErrorDTO;
import com.leacar21.technical.seat.function.reservation.exceptions.BadRequestException;
import com.leacar21.technical.seat.function.reservation.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIErrorDTO> handlerUnknownException(HttpServletRequest request, Exception ex) {
        return handlerException(request, ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIErrorDTO> handlerResourceNotFoundException(HttpServletRequest request, Exception ex) {
        return handlerException(request, ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIErrorDTO> handlerBadRequestException(HttpServletRequest request, Exception ex) {
        return handlerException(request, ex, HttpStatus.BAD_REQUEST);
    }

    // --------------

    /**
     * Este método sobreescribe la implementación básica de Spting en la clase ResponseEntityExceptionHandler.java
     * para poder manejar las excepciones de las anotaciones de Java Bean Validation,
     * como por ejemplo @NotEmpty, @NotNull, etc, disparadas por @Valid.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream().collect(
                Collectors.groupingBy(FieldError::getField, Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

        var body = new APIErrorDTO();

        body.setCode(status.value());
        var messageBuilder = new StringBuilder("Some of the data required by the service are not present or are invalid: ");
        for (Entry<String, List<String>> entry : errors.entrySet()) {
            List<String> values = entry.getValue();
            messageBuilder.append(StringUtils.join(values, " | "));
        }

        var message = messageBuilder.toString();
        body.setMessage(message);

        this.log.error(message, ex);

        return new ResponseEntity<>(body, new HttpHeaders(), status);
    }

    // --------------

    private ResponseEntity<APIErrorDTO> handlerException(HttpServletRequest request, Exception ex, HttpStatus status) {
        var body = new APIErrorDTO();

        body.setCode(status.value());
        body.setMessage(ex.getMessage());

        this.log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(body, status);
    }
}