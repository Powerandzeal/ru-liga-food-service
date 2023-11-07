package ru.liga.Exceptions;

public class CreateOrderException extends RuntimeException{
    public CreateOrderException(String message) {
        super(message);
    }
}
