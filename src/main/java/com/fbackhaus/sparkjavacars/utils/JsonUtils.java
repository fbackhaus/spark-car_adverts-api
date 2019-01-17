package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.google.gson.*;
import spark.ResponseTransformer;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class JsonUtils {

    public static String toJson(Object object) {
        return gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }

    public static Car toCar(String body) {
        return gson().fromJson(body, Car.class);
    }

    private static Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (localDate, type, jsonDeserializationContext) -> LocalDate.parse(localDate.getAsString())).create();
    }
}
