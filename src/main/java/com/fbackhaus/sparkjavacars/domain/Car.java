package com.fbackhaus.sparkjavacars.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;

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

    public Car(int id, String title, String fuel, int price, boolean isNew, int mileage) {
        this.id = id;
        this.title = title;
        this.fuel = fuel;
        this.price = price;
        this.isNew = isNew;
        this.mileage = mileage;
    }

    public Car() {
    }


}
