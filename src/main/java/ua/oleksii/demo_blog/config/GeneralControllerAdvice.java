package ua.oleksii.demo_blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.oleksii.demo_blog.exceptions.PostNotFoundException;
import ua.oleksii.demo_blog.exceptions.TagInUseException;
import ua.oleksii.demo_blog.exceptions.TagNotFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GeneralControllerAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleInternalServerErrorException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong...");
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        var msg = e.getFieldErrors().stream()
                .map(param -> "Parameter validation failed: field[%s] was [%s]. Requirement:[%s]".formatted(
                        param.getField(), param.getRejectedValue(), param.getDefaultMessage())).collect(Collectors.joining(System.lineSeparator()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }
    @ExceptionHandler({TagInUseException.class})
    public ResponseEntity<String> handleTagInUseException(TagInUseException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }
    @ExceptionHandler({TagNotFoundException.class})
    public ResponseEntity<String> handleTagNotFoundException(TagNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler({PostNotFoundException.class})
    public ResponseEntity<String> handlePostNotFoundException(PostNotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
