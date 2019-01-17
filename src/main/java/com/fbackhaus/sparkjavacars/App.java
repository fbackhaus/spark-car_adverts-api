package com.fbackhaus.sparkjavacars;

import com.fbackhaus.sparkjavacars.controllers.CarsController;

import static com.fbackhaus.sparkjavacars.utils.JsonUtils.json;
import static spark.Spark.*;


public class App 
{
    public static void main( String[] args ) {

        get("/cars", CarsController::getCars, json());

        post("/car", CarsController::saveCar, json());

        get("/car/:carId", CarsController::getCarById, json());

        delete("/car/:carId", CarsController::deleteCarById, json());

        put ("/car/:carId", CarsController::modifyCar, json());

    }
}
