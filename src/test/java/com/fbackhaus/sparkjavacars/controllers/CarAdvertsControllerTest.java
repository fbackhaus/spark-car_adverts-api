package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.utils.RestClientTestUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CarAdvertsControllerTest {

    @Test
    public void testGetAdvertByIdShouldReturnBadRequest() {
        String uri = "http://localhost:8080/car-adverts/asdqqq";

        Request request = RestClientTestUtils.getRequest("GET", uri, "");
        Map<String, String> params = new HashMap<>();
        params.put(":id", "asdqqq");

        RestClientTestUtils.setParamsInMockRequest(request, params);

        Response response = RestClientTestUtils.getEmptyResponse();
        CarAdvertsController.getCarAdvertById(request, response);

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.status());

    }

    @Test
    public void testDeleteAdvertByIdShouldReturnBadRequest() {
        String uri = "http://localhost:8080/car-adverts/asdqqq";

        Request request = RestClientTestUtils.getRequest("DELETE", uri, "");
        Map<String, String> params = new HashMap<>();
        params.put(":id", "asdqqq");

        RestClientTestUtils.setParamsInMockRequest(request, params);

        Response response = RestClientTestUtils.getEmptyResponse();
        CarAdvertsController.deleteCarAdvertById(request, response);

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.status());

    }

    @Test
    public void testModifyAdvertByIdShouldReturnBadRequest() {
        String uri = "http://localhost:8080/car-adverts/asdqqq";

        Request request = RestClientTestUtils.getRequest("PUT", uri, "");
        Map<String, String> params = new HashMap<>();
        params.put(":id", "asdqqq");

        RestClientTestUtils.setParamsInMockRequest(request, params);

        Response response = RestClientTestUtils.getEmptyResponse();
        CarAdvertsController.modifyCarAdvert(request, response);

        assertEquals(HttpStatus.SC_BAD_REQUEST, response.status());

    }
}
