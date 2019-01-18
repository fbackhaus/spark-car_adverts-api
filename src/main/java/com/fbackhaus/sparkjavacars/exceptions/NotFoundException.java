package com.fbackhaus.sparkjavacars.exceptions;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public Map<String, Object> getResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.SC_NOT_FOUND);
        body.put("message", getMessage());
        return body;
    }
}
