# spark-car_adverts-api

This project consists on a RESTful API that allows the creation, reading, updating and deleting of car adverts.
It has been built using Java 8, Spark Framework and Amazon DynamoDB.

## Requirements

* Java 8
* [Docker](https://www.docker.com/get-started)

## How to run the app locally

First, open up a Terminal.

With Docker daemon running, we download the [DynamoDB image](https://hub.docker.com/r/amazon/dynamodb-local/):

```bash
$ docker pull amazon/dynamodb-local
```

Now, to execute DynamoDB locally:

```bash
$ docker run -p 8000:8000 amazon/dynamodb-local
```

Finally:

```bash
$ mvn clean compile package -P development
$ mvn exec:java
```

**Very important**: the app runs on port 8080.

## Create a new car advert

```
POST /car-adverts
```

Payload example:

```json
{
	"id": 1,
	"title": "Ford Mustang GT",
	"new": true,
	"price": 120000,
	"fuel": "Gasoline"
}
```

**Very important**: the next fields are required if the car is used. If you don't add them, you'll receive a Bad Request Error. The same if you add them, and the car is new.
* 	mileage: int
*	first_registration: date (format yyyy-MM-dd)

## Get all car adverts

```
GET /car-adverts
```

You can ask the results to be sorted by one of the fields named above, adding the next query param:

```
GET /car-adverts?sort=${field}:${order}
```

* Fields you can sort the results by: id, title, new, price, fuel.
* Order: asc or desc.

## Get car advert by id

```
GET /car-adverts/${id}
```

## Modify car advert

```
PUT /car-adverts/${id}
```
You must add the id as a param, to indicate which advert you want to update.

You can use the payload example above.
**Very important**: if you want to modify a non existant car advert, you'll receive a Bad Request Error.

## Delete car advert by id

```
DELETE /car-adverts/${id}
```

You must add the id as a param, to indicate which advert you want to delete here too.
**Very important**: if you want to delete a non existant car advert, you'll receive a Bad Request Error.

## Tests
```bash
$ mvn test
```
