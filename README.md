### Package Structure
* package adapter - anti-corruption layer to external systems. Contains mapping from external system model to internal system model and cashing
* package client - clients to external system with circuit-breaker, retries and open feign implementation
* package config - configuration classes
* package controller - system API, global exception handlers
* package dto - data transfer objects within system
* package listener - Kafka listeners to consume messages from restaurant, casino, hotels topics
* package rewardcalculation - reward calculation logic with strategy pattern implementation
* package service - contains calls to external systems, business logic

### Technologies Used
* Java 17
* Spring Boot
* Spring Cloud OpenFeign
* Resilience4j
* Kafka
* Redis
* Lombok
* Maven

### Overall Architecture Description
* Mobile clients or web applications interact with the system through RESTful APIs exposed by the controller layer. 
* The controller layer handles incoming requests, performs necessary validations, and delegates business logic to the service layer.
* The service layer contains the core business logic. It interacts with external systems through the adapter layer and applies reward calculation strategies.
* The adapter layer serves as an anti-corruption layer, mapping external system models to internal system models and handling caching to improve performance.
* The client layer is responsible for communication with external systems such as microservices for restaurants, casinos, and hotels. It uses OpenFeign to simplify HTTP client code and Resilience4j to implement circuit breakers and retries for fault tolerance.
* The listener layer contains Kafka listeners that consume messages from various topics related to restaurants, casinos, and hotels. These listeners process incoming messages and trigger cashed data eviction for specific customer.
* Business logic of reward calculation is implemented using the strategy pattern, allowing for easy extension and modification of reward calculation strategies.
