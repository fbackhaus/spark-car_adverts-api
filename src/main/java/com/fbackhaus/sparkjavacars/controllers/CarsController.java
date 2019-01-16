package com.fbackhaus.sparkjavacars.controllers;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.services.CarsService;
import spark.Request;
import spark.Response;

public class CarsController {

    public static Object getCars(Request request, Response response) {
        return CarsService.getInstance().getCars();
    }

    public static Object saveCar(Request request, Response response) {
        Car car = new Car(1, "Fiat Palio", "Gasoline", 123, true, 333);
        CarsService.getInstance().save(car);
        return "HOLIS";
    }
}
