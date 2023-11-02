package ru.liga;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;
import ru.liga.Exceptions.NotFoundCourierException;
import ru.liga.Exceptions.ValidationException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleGlobalException(Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Внутренняя ошибка сервера");
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);
        log.error(errorMessage);
        return ResponseEntity.badRequest().body("Ошибка валидации, поле: " + errorMessage);
    }
//    @ExceptionHandler(NotFoundCourierException.class)
//    public ResponseEntity<String> handleSomeException(NotFoundCourierException ex) {
//        // Здесь вы можете сгенерировать пользовательское сообщение об ошибке
//        String errorMessage = "Произошла ошибка курьер не найден: " + ex.getMessage();
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//    }
@ExceptionHandler(NotFoundException.class)
public ResponseEntity<ErrorMessage> handleNotFoundCourierException(NotFoundException ex) {
        log.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ex.getMessage()));
}

}
