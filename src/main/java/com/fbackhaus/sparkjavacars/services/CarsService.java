package com.fbackhaus.sparkjavacars.services;

import com.fbackhaus.sparkjavacars.domain.Car;
import com.fbackhaus.sparkjavacars.persistence.CarRepository;

import java.util.ArrayList;
import java.util.List;

public class CarsService {

    private static volatile CarsService instance = null;


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

    public void save(Car car) {
        CarRepository.getInstance()
                .save(car);
    }
}
