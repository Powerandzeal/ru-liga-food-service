package ru.liga.Exceptions;

public class NotFoundCourierException extends RuntimeException{
    public NotFoundCourierException(String message) {
        super(message);
    }
}
