package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.domain.CarAdvert;
import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import com.fbackhaus.sparkjavacars.exceptions.NotFoundException;
import com.fbackhaus.sparkjavacars.services.CarAdvertsService;
import com.fbackhaus.sparkjavacars.utils.JsonUtils;
import com.fbackhaus.sparkjavacars.utils.SortUtils;
import com.fbackhaus.sparkjavacars.utils.ValidationUtils;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CarAdvertsController {

    private static final String DELETE_MESSAGE_OK = "CarAdvert with id %s deleted successfully";
    private static final String DEFAULT_SORT_CARS_FIELD = "id:asc";

    public static Object getCarAdverts(Request request, Response response) {
        Map<String, String> params = ValidationUtils.parseQueryParamsMap(request.queryMap());
        ValidationUtils.shouldProcess(params);
        List<CarAdvert> carAdverts = CarAdvertsService.getInstance().getCarAdverts();
        String sortBy = params.get("sort");

        if (params.isEmpty()) {
            SortUtils.sortCarList(DEFAULT_SORT_CARS_FIELD, carAdverts);
        } else {
            SortUtils.sortCarList(sortBy, carAdverts);
        }
        return carAdverts;
    }

    public static Object createCarAdvert(Request request, Response response) {
        CarAdvert carAdvert;
        try {
            ValidationUtils.validateCarAdvert(request.body());
            carAdvert = JsonUtils.toCarAdvert(request.body());
            CarAdvertsService.getInstance().createCarAdvert(carAdvert);
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        }

        return carAdvert;
    }

    public static Object getCarAdvertById(Request request, Response response) {
        String carAdvertId = request.params(":carAdvertId");
        CarAdvert carAdvert;
        try {
            ValidationUtils.validateCarAdvertId(carAdvertId);
            carAdvert = CarAdvertsService.getInstance().getCarAdvertById(Integer.parseInt(carAdvertId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }

        return carAdvert;
    }

    public static Object deleteCarAdvertById(Request request, Response response) {
        String carAdvertId = request.params(":carAdvertId");
        try {
            ValidationUtils.validateCarAdvertId(carAdvertId);
            CarAdvertsService.getInstance().deleteCarAdvertById(Integer.parseInt(carAdvertId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }
        return String.format(DELETE_MESSAGE_OK, carAdvertId);
    }

    public static Object modifyCarAdvert(Request request, Response response) {
        String carAdvertId = request.params(":carAdvertId");
        CarAdvert carAdvert;
        try {
            ValidationUtils.validateCarAdvertId(carAdvertId);
            carAdvert = CarAdvertsService.getInstance().modifyCarById(Integer.parseInt(carAdvertId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }
        return carAdvert;
    }
}
