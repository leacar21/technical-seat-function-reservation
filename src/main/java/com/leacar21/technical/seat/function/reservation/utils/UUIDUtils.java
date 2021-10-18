package com.leacar21.technical.seat.function.reservation.utils;

import java.util.UUID;

import com.leacar21.technical.seat.function.reservation.exceptions.ResourceNotFoundException;

public class UUIDUtils {

    private UUIDUtils() {}

    public static UUID getUUID(String strUUID, String errorMessage) {
        try {
            return UUID.fromString(strUUID);
        } catch (Exception exc) {
            throw new ResourceNotFoundException(errorMessage);
        }
    }
}
