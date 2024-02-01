package ua.oleksii.demo_blog.exceptions;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
