package ua.oleksii.demo_blog.exceptions;

public class TagInUseException extends RuntimeException {
    public TagInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}
