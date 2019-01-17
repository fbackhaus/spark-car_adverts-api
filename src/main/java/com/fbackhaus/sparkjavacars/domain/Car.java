package com.fbackhaus.sparkjavacars.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fbackhaus.sparkjavacars.persistence.converters.LocalDateConverter;
import lombok.Data;

import java.time.LocalDate;

@Data
@DynamoDBTable(tableName = "Car")
public class Car {

    @DynamoDBHashKey
    private int id;

    @DynamoDBAttribute
    private String title;

    @DynamoDBAttribute
    private String fuel;

    @DynamoDBAttribute
    private int price;

    @DynamoDBAttribute
    private boolean isNew;

    @DynamoDBAttribute
    private int mileage;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate firstRegistration;

    public Car() {
    }

}
