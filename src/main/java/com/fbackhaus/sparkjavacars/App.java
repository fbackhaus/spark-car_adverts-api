package com.fbackhaus.sparkjavacars;

import com.fbackhaus.sparkjavacars.controllers.CarsController;

import static com.fbackhaus.sparkjavacars.utils.JsonUtils.json;
import static spark.Spark.*;


public class App {
    public static void main(String[] args) {

        before((request, response) -> response.type("application/json"));

        get("/cars", CarsController::getCars, json());

        post("/car", CarsController::createCar, json());

        get("/car/:carId", CarsController::getCarById, json());

        delete("/car/:carId", CarsController::deleteCarById, json());

        put("/car/:carId", CarsController::modifyCar, json());

        notFound((req, res) -> "{\"message\":\"Resource not found\", \"status\": 404}");

    }
}
