package com.fbackhaus.sparkjavacars.exceptions;

import java.util.HashMap;
import java.util.Map;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public Map<String, Object> getResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", 404);
        body.put("message", getMessage());
        return body;
    }
}
