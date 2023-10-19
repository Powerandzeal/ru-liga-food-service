package ru.liga;

public enum StatusCurrier {

    //Customer status
    CUSTOMER_CREATED,
    CUSTOMER_PAID,
    CUSTOMER_CANCELLED,

    //Kitchen status
    KITCHEN_ACCEPTED,
    KITCHEN_PREPARING,
    KITCHEN_DENIED,
    KITCHEN_REFUNDED,

    //Delivery Status
    DELIVERY_PENDING,
    DELIVERY_PICKING,
    DELIVERY_DELIVERING,
    DELiVERY_DENIED,
    DELIVERY_REFUNDED,
    DELIVERY_COMPLETE,
}
