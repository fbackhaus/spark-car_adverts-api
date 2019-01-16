package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.services.CarsService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class CarsController {

    public static Object getCars(Request request, Response response) {
        return CarsService.getInstance().getCars();
    }

    public static Object saveCar(Request request, Response response) {
        Car car = new Gson().fromJson(request.body(), Car.class);
        CarsService.getInstance().save(car);
        return car;
    }
}
