package com.fbackhaus.sparkjavacars.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fbackhaus.sparkjavacars.persistence.converters.LocalDateConverter;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDate;

@Data
@DynamoDBTable(tableName = "CarAdvert")
public class CarAdvert {

    @DynamoDBHashKey
    private int id;

    @DynamoDBAttribute
    private String title;

    @DynamoDBAttribute
    private String fuel;

    @DynamoDBAttribute
    private int price;

    @DynamoDBAttribute
    @SerializedName("new")
    private boolean isNew;

    @DynamoDBAttribute
    private int mileage;

    @DynamoDBAttribute
    @SerializedName("first_registration")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate firstRegistration;

    public CarAdvert() {
    }

}
