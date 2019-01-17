package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.domain.CarAdvert;
import com.fbackhaus.sparkjavacars.persistence.adapters.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import spark.ResponseTransformer;

import java.time.LocalDate;

public class JsonUtils {

    public static String toJson(Object object) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        return gson.toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtils::toJson;
    }

    public static CarAdvert toCarAdvert(String body) {
        return gson().fromJson(body, CarAdvert.class);
    }

    private static Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (localDate, type, jsonDeserializationContext) -> LocalDate.parse(localDate.getAsString())).create();
    }
}
