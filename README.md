### Package Structure
* package adapter - anti-corruption layer to external systems. Contains mapping from external system model to internal system model and cashing
* package client - clients to external system with circuit-breaker, retries and open feign implementation
* package config - configuration classes
* package controller - system API, global exception handlers
* package dto - data transfer objects within system
* package listener - Kafka listeners to consume messages from restaurant, casino, hotels topics
* package rewardcalculation - reward calculation logic with strategy pattern implementation
* package service - contains calls to external systems, business logic
