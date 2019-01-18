package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import org.junit.Test;

public class ValidationUtilsTest {

    @Test
    public void testIsValidShouldExecuteOk() {
        String body = "{\"id\": 3, \"title\": \"Corsa 3\", \"new\": false, \"price\": 10000, \"fuel\": \"Gasoline\", \"mileage\": 1500, \"first_registration\": \"2005-01-01\"}";
        ValidationUtils.validateCarAdvert(body);
    }

    @Test(expected = BadRequestException.class)
    public void testIsValidShouldThrowBadRequestExceptionBecauseIsNewAndHasUsedCarFields() {
        String body = "{\"id\": 3, \"title\": \"Corsa 3\", \"new\": true, \"price\": 10000, \"fuel\": \"Gasoline\", \"mileage\": 1500, \"first_registration\": \"2005-01-01\"}";
        ValidationUtils.validateCarAdvert(body);
    }

    @Test(expected = BadRequestException.class)
    public void testIsValidShouldThrowBadRequestExceptionBecauseIsUsedAndDoesntHaveUsedCarFields() {
        String body = "{\"id\": 3, \"title\": \"Corsa 3\", \"new\": false, \"price\": 10000, \"fuel\": \"Gasoline\"}";
        ValidationUtils.validateCarAdvert(body);
    }
}
