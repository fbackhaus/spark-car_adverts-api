package com.fbackhaus.sparkjavacars;

import com.fbackhaus.sparkjavacars.controllers.CarAdvertsController;
import com.fbackhaus.sparkjavacars.utils.PersistenceUtils;
import spark.Spark;

import static com.fbackhaus.sparkjavacars.utils.JsonUtils.json;
import static spark.Spark.*;


public class App {

    public static void main(String[] args) {

        Spark.port(8080);

        PersistenceUtils.init();

        Spark.init();

        Spark.awaitInitialization();

        before((request, response) -> response.type("application/json"));

        get("/car-adverts", CarAdvertsController::getCarAdverts, json());

        post("/car-adverts", CarAdvertsController::createCarAdvert, json());

        get("/car-adverts/:id", CarAdvertsController::getCarAdvertById, json());

        delete("/car-adverts/:id", CarAdvertsController::deleteCarAdvertById, json());

        put("/car-adverts/:id", CarAdvertsController::modifyCarAdvert, json());

        notFound((req, res) -> "{\"message\":\"Resource not found\", \"status\": 404}");

    }
}
