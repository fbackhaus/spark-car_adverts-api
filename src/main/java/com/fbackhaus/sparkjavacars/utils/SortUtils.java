package com.fbackhaus.sparkjavacars.utils;

import com.fbackhaus.sparkjavacars.domain.Car;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtils {

    public static void sortCarList(String field, List<Car> cars) {
        String[] sortArray = field.split(":");
        switch (sortArray[0]) {
            case "id":
                cars.sort(Comparator.comparing(Car::getId));
                break;
            case "title":
                cars.sort(Comparator.comparing(Car::getTitle));
                break;
            case "fuel":
                cars.sort(Comparator.comparing(Car::getFuel));
                break;
            case "price":
                cars.sort(Comparator.comparing(Car::getPrice));
                break;
        }

        if ("desc".equalsIgnoreCase(sortArray[1])) {
            Collections.reverse(cars);
        }
    }
}
