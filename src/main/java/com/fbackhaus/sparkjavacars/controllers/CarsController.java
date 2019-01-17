package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import com.fbackhaus.sparkjavacars.exceptions.NotFoundException;
import com.fbackhaus.sparkjavacars.services.CarsService;
import com.fbackhaus.sparkjavacars.utils.JsonUtils;
import com.fbackhaus.sparkjavacars.utils.SortUtils;
import com.fbackhaus.sparkjavacars.utils.ValidationUtils;
import spark.Request;
import spark.Response;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CarsController {

    private static final String DELETE_MESSAGE_OK = "Car with id %s deleted successfully";
    private static final String DEFAULT_SORT_CARS_FIELD = "id:asc";

    public static Object getCars(Request request, Response response) {
        Map<String, String> params = ValidationUtils.parseQueryParamsMap(request.queryMap());
        ValidationUtils.shouldProcess(params);
        List<Car> cars = CarsService.getInstance().getCars();
        String sortBy = params.get("sort");

        if (params.isEmpty()) {
            SortUtils.sortCarList(DEFAULT_SORT_CARS_FIELD, cars);
        } else {
            SortUtils.sortCarList(sortBy, cars);
        }
        return cars;
    }

    public static Object createCar(Request request, Response response) {
        Car car;
        try {
            ValidationUtils.validateCarAdvert(request.body());
            car = JsonUtils.toCar(request.body());
            CarsService.getInstance().create(car);
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        }

        return car;
    }

    public static Object getCarById(Request request, Response response) {
        String carId = request.params(":carId");
        Car car;
        try {
            ValidationUtils.validateCarId(carId);
            car = CarsService.getInstance().getCarById(Integer.parseInt(carId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }

        return car;
    }

    public static Object deleteCarById(Request request, Response response) {
        String carId = request.params(":carId");
        try {
            ValidationUtils.validateCarId(carId);
            CarsService.getInstance().deleteCarById(Integer.parseInt(carId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }
        return String.format(DELETE_MESSAGE_OK, carId);
    }

    public static Object modifyCar(Request request, Response response) {
        String carId = request.params(":carId");
        Car car;
        try {
            ValidationUtils.validateCarId(carId);
            car = CarsService.getInstance().modifyCarById(Integer.parseInt(carId));
        } catch (BadRequestException ex) {
            response.status(HttpServletResponse.SC_BAD_REQUEST);
            return ex.getResponseBody();
        } catch (NotFoundException ex) {
            response.status(HttpServletResponse.SC_NOT_FOUND);
            return ex.getResponseBody();
        }
        return car;
    }
}
