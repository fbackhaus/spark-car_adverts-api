package com.fbackhaus.sparkjavacars.persistence;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.*;
import com.fbackhaus.sparkjavacars.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private static volatile CarRepository instance = null;
    private static DynamoDBMapper mapper;

    private CarRepository() {
    }

    public static CarRepository getInstance() {
        if (instance == null)
            synchronized (CarRepository.class) {
                if (instance == null) {
                    instance = new CarRepository();
                    AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
                            .build();
                    mapper = new DynamoDBMapper(client);
                    try {
                        client.describeTable("Car");
                    } catch(ResourceNotFoundException ex) {
                        createTable(client);
                    }
                }

            }
        return instance;
    }

    private static void createTable(AmazonDynamoDB client) {

        List<KeySchemaElement> elements = new ArrayList<>();
        KeySchemaElement keySchemaElement = new KeySchemaElement()
                .withKeyType(KeyType.HASH)
                .withAttributeName("id");
        elements.add(keySchemaElement);
        List<AttributeDefinition> attributeDefinitions = new ArrayList<>();
        attributeDefinitions.add(new AttributeDefinition()
                .withAttributeName("id")
                .withAttributeType(ScalarAttributeType.N));
        CreateTableRequest createTableRequest = new CreateTableRequest()
                .withTableName("Car")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withAttributeDefinitions(attributeDefinitions);
        client.createTable(createTableRequest);
    }

    public void save(Car car) {
        mapper.save(car);
    }

    public List<Car> getCars() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(Car.class, scanExpression);
    }
}
