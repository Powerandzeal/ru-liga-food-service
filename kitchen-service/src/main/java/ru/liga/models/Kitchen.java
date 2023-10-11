package ru.liga.models;

import java.time.LocalDateTime;

public class Kitchen {
    int id;
    String nameKitchen;
    String addressKitchen;
    LocalDateTime timeWork;

    int ratingKitchen;
    Dish dish;
    String statusOrder;
}
