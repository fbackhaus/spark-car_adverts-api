package com.fbackhaus.sparkjavacars.services;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import com.fbackhaus.sparkjavacars.exceptions.NotFoundException;
import com.fbackhaus.sparkjavacars.persistence.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarsService {

    private static volatile CarsService instance = null;
    private static final String NOT_FOUND_MESSAGE = "Car with id %s not found";
    private static final String BAD_REQUEST_MESSAGE = "The car with id %s already exists";


    private CarsService() {
    }

    public static CarsService getInstance() {
        if (instance == null)
            synchronized (CarsService.class) {
                if (instance == null)
                    instance = new CarsService();
            }
        return instance;
    }

    public List<Car> getCars() {
        return new ArrayList<>(CarRepository.getInstance()
                .getCars());
    }

    public void create(Car car) {
        Car alreadySavedCar = CarRepository
                .getInstance()
                .getCarById(car.getId());

        if (alreadySavedCar != null) {
            throw new BadRequestException(String.format(BAD_REQUEST_MESSAGE, car.getId()));
        }

        CarRepository.getInstance()
                .save(car);
    }

    public Car getCarById(int id) {

        Car car = CarRepository
                .getInstance()
                .getCarById(id);

        if (car == null) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, id));
        }
        return car;
    }

    public void deleteCarById(int id) {

        Car car = CarRepository
                .getInstance()
                .getCarById(id);

        if (car == null) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, id));
        }

        CarRepository
                .getInstance()
                .deleteCarById(id);
    }

    public Car modifyCarById(int id) {
        Car car = getCarById(id);
        if (car == null) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, id));
        }

        CarRepository
                .getInstance()
                .save(car);

        return car;
    }
}
