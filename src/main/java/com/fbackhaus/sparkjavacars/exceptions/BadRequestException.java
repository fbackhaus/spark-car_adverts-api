package com.fbackhaus.sparkjavacars.exceptions;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class BadRequestException extends RuntimeException
{
    public BadRequestException(String message)
    {
        super(message);
    }

    public Map<String, Object> getResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_BAD_REQUEST);
        body.put("message", getMessage());
        return body;
    }
}
