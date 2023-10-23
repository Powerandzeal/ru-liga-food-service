package ru.liga;

public enum StatusCurrier {

    //CustomerStatusOrder status
    CUSTOMER_CREATED,
    CUSTOMER_PAID,
    CUSTOMER_CANCELLED,

    //KitchenStatusOrder status
    KITCHEN_ACCEPTED,
    KITCHEN_PREPARING,
    KITCHEN_DENIED,
    KITCHEN_REFUNDED,

    //DeliveryStatusOrder Status
    DELIVERY_PENDING,
    DELIVERY_PICKING,
    DELIVERY_DELIVERING,
    DELiVERY_DENIED,
    DELIVERY_REFUNDED,
    DELIVERY_COMPLETE,
}
