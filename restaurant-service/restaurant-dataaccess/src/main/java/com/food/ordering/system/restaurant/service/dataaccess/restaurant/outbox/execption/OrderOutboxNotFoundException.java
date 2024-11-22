package com.food.ordering.system.restaurant.service.dataaccess.restaurant.outbox.execption;

public class OrderOutboxNotFoundException extends RuntimeException {

    public OrderOutboxNotFoundException(String message) {
        super(message);
    }
}
