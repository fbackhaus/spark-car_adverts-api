package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.QueryParamsMap;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValidationUtils {

    private static final String[] basicFields = {"id", "title", "fuel", "price"};
    private static final String[] usedCarsFields = {"mileage", "firstRegistration"};
    public static final String BAD_REQUEST_MESSAGE = "Please check your parameters";

    public static void validateCarAdvert(String body) {
        if (!isValid(body)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE);
        }
    }

    private static boolean isValid(String body) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(body).getAsJsonObject();

        //Validation if body has all basic fields
        boolean hasBasicFields = Stream
                .of(basicFields)
                .allMatch(jsonObject::has);

        //Validation if body has used cars fields
        boolean hasAllUsedCarsFields = Stream
                .of(usedCarsFields)
                .allMatch(jsonObject::has);

        boolean hasAnyUsedCarsFields = Stream
                .of(usedCarsFields)
                .anyMatch(jsonObject::has);

        if (isNewCar(jsonObject)) {
            return hasBasicFields && !hasAnyUsedCarsFields;
        }

        return hasBasicFields && hasAllUsedCarsFields;
    }

    private static boolean isNewCar(JsonObject carAdvert) {
        return carAdvert.get("isNew").getAsBoolean();
    }

    public static void validateCarId(String id) {
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE);
        }
    }

    public static Map<String, String> parseQueryParamsMap(QueryParamsMap params) {
        return params.toMap()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue()[0]));
    }

    public static void shouldProcess(Map<String, String> params) {
        if (params.size() > 1) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE);
        }
    }
}
