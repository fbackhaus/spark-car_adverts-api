package com.fbackhaus.sparkjavacars.utils;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.ArrayList;
import java.util.List;

public class PersistenceUtils {

    private static final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "us-west-2"))
            .build();

    public static void init() {
        try {
            client.describeTable("CarAdvert");
        } catch (ResourceNotFoundException ex) {
            createTable(client);
        }
    }

    public static AmazonDynamoDB getClient() {
        return client;
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
                .withTableName("CarAdvert")
                .withKeySchema(elements)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(5L)
                        .withWriteCapacityUnits(5L))
                .withAttributeDefinitions(attributeDefinitions);
        client.createTable(createTableRequest);
    }
}
