package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.services.CarsService;
import com.fbackhaus.sparkjavacars.utils.JsonUtils;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Optional;

public class CarsController {

    public static Object getCars(Request request, Response response) {
        return CarsService.getInstance().getCars();
    }

    public static Object saveCar(Request request, Response response) {
        Car car = JsonUtils.toCar(request.body());
        CarsService.getInstance().save(car);
        return car;
    }

    public static Object getCarById(Request request, Response response) {
        Optional<String> carId = Optional.ofNullable(request.params(":carId"));
        return carId.map(s -> CarsService.getInstance().getCarById(Integer.parseInt(s))).orElse(null);
    }

    public static Object deleteCarById(Request request, Response response) {
        Optional<String> carId = Optional.ofNullable(request.params(":carId"));
        if (carId.isPresent()) {
            CarsService.getInstance().deleteCarById(Integer.parseInt(carId.get()));
        }
        return "HOLIS";
    }

    public static Object modifyCar(Request request, Response response) {
        Optional<String> carId = Optional.ofNullable(request.params(":carId"));
        if (carId.isPresent()) {
            Car updatedCar = JsonUtils.toCar(request.body());
            CarsService.getInstance().modifyCarById(updatedCar);
        }
        return "HOLIS";
    }
}
