package com.fbackhaus.sparkjavacars.services;

import com.fbackhaus.sparkjavacars.domain.CarAdvert;
import com.fbackhaus.sparkjavacars.exceptions.BadRequestException;
import com.fbackhaus.sparkjavacars.exceptions.NotFoundException;
import com.fbackhaus.sparkjavacars.persistence.CarAdvertRepository;

import java.util.ArrayList;
import java.util.List;

public class CarAdvertsService {

    private static volatile CarAdvertsService instance = null;
    private static final String NOT_FOUND_MESSAGE = "Car advert with id %s not found";
    private static final String BAD_REQUEST_MESSAGE = "The car advert with id %s already exists";


    private CarAdvertsService() {
    }

    public static CarAdvertsService getInstance() {
        if (instance == null)
            synchronized (CarAdvertsService.class) {
                if (instance == null)
                    instance = new CarAdvertsService();
            }
        return instance;
    }

    public List<CarAdvert> getCarAdverts() {
        return new ArrayList<>(CarAdvertRepository.getInstance()
                .getCarAdverts());
    }

    public void createCarAdvert(CarAdvert carAdvert) {
        CarAdvert alreadySavedCarAdvert = CarAdvertRepository
                .getInstance()
                .getCarAdvertById(carAdvert.getId());

        if (alreadySavedCarAdvert != null) {
            throw new BadRequestException(String.format(BAD_REQUEST_MESSAGE, carAdvert.getId()));
        }

        CarAdvertRepository.getInstance()
                .save(carAdvert);
    }

    public CarAdvert getCarAdvertById(int id) {

        CarAdvert carAdvert = CarAdvertRepository
                .getInstance()
                .getCarAdvertById(id);

        if (carAdvert == null) {
            throw new NotFoundException(String.format(NOT_FOUND_MESSAGE, id));
        }
        return carAdvert;
    }

    public void deleteCarAdvertById(int id) {

        getCarAdvertById(id);
        CarAdvertRepository
                .getInstance()
                .deleteCarAdvertById(id);
    }

    public void modifyCarById(CarAdvert modifiedCarAdvert) {

        getCarAdvertById(modifiedCarAdvert.getId());

        CarAdvertRepository
                .getInstance()
                .save(modifiedCarAdvert);
    }
}
