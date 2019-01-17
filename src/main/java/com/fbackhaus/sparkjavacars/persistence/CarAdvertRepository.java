package com.fbackhaus.sparkjavacars.persistence;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.fbackhaus.sparkjavacars.domain.CarAdvert;
import com.fbackhaus.sparkjavacars.utils.PersistenceUtils;

import java.util.List;

public class CarAdvertRepository {

    private static volatile CarAdvertRepository instance = null;
    private static DynamoDBMapper mapper;

    private CarAdvertRepository() {
    }

    public static CarAdvertRepository getInstance() {
        if (instance == null)
            synchronized (CarAdvertRepository.class) {
                if (instance == null) {
                    instance = new CarAdvertRepository();
                    mapper = new DynamoDBMapper(PersistenceUtils.getClient());
                }

            }
        return instance;
    }

    public void save(CarAdvert carAdvert) {
        mapper.save(carAdvert);
    }

    public List<CarAdvert> getCarAdverts() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(CarAdvert.class, scanExpression);
    }

    public CarAdvert getCarAdvertById(int id) {
        return mapper.load(CarAdvert.class, id);
    }

    public void deleteCarAdvertById(int id) {
        CarAdvert carAdvertToDelete = new CarAdvert();
        carAdvertToDelete.setId(id);
        mapper.delete(carAdvertToDelete);
    }
}
