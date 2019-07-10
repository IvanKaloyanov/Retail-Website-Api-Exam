# Retail Website API
## About
Retail Website API project is a REST API for applying discounts to a given bill based on the type of user type (fixed percentage) and the amount of the total bill (fixed amount). The discounts are applied as follows:
- Employees have a percentage discount - 30%.
- Affiliates have a percentage discount - 10%.
- Loyal customers (customers from over 2 years) have a percentage discount - 5%.
- Fixed discount for every 100$ on the bill, a 5$ discount is applied
- Percentage discounts are not applied to grocery products
- Only 1 percentage discount is applied (the highest one)

 
## Implementation
The Retail Website API project is written in Java and the API is based on the Spring framework with some of the core things used in this project are:
- Spring Boot
- Spring Security with JWT
- Spring Data JPA


# UML Diagram
![Retail (2)](https://user-images.githubusercontent.com/16307530/60998741-3acac300-a362-11e9-93de-5fcff4137b4e.png)

## Build
In order to start the application perform the following steps:
 1. Clone the repository
 2. Open the root project folder and execute `./mvnw spring-boot:run`
## Test
In order to run the unit tests perform the following steps:
 1. Clone the repository
 2. Open the root project folder and execute `./mvn test`

After that a report is generated under `./target/site/jacoco/*.html`

## API Endpoints
##### `POST: /api/v1/retail/discounts`
Endpoint for app applying a discount for a given bill. Example request:
```
[
    {
        "item":{
            "name": "Tesla Model 3",
            "price": "35000",
            "itemType": "TECH",
            "description": "Best car ever"
        },
        "count": 1
    }
]
```
Example response:
```
{
    "purchases": [
        {
            "item": {
                "name": "Tesla Model 3",
                "price": 35000,
                "itemType": "TECH",
                "description": "Best car ever"
            },
            "count": 1
        }
    ],
    "totalPrice": 26250
}
```
##### `POST: /auth/register`
Endpoint for registring a user. Example request:
```
{
    "username": "ElonMusk",
    "password": "LoveSpace123",
    "roles": ["ADMIN", "LOYAL"]
}
```
##### `PUT: /auth/login`
Endpoint for obtaining a signed JWT. Example request:
```
{
    "username": "admin",
    "password": "admin"
}
```
Example response:
```
{
    "username": "admin",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIkFGRklMSUFURSIsIkxveWFsIl0sImlhdCI6MTU2Mjc4NjYzNCwiZXhwIjoxNTYyNzk2NjM0fQ.df4_kEOyjFA6WzqJwqEct2tz0GAk6QfwgdQ-PpPO57E"
}
```


