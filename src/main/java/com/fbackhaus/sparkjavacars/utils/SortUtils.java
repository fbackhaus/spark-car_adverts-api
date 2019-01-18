package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.domain.CarAdvert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtils {

    public static void sortCarList(String field, List<CarAdvert> carAdverts) {
        String[] sortArray = field.split(":");
        switch (sortArray[0]) {
            case "id":
                carAdverts.sort(Comparator.comparing(CarAdvert::getId));
                break;
            case "title":
                carAdverts.sort(Comparator.comparing(CarAdvert::getTitle));
                break;
            case "fuel":
                carAdverts.sort(Comparator.comparing(CarAdvert::getFuel));
                break;
            case "price":
                carAdverts.sort(Comparator.comparing(CarAdvert::getPrice));
                break;
            case "new":
                carAdverts.sort(Comparator.comparing(CarAdvert::isNew));
                break;
        }

        if ("desc".equalsIgnoreCase(sortArray[1])) {
            Collections.reverse(carAdverts);
        }
    }
}
