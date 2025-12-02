package pe.iotteam.plantcare.user.domain.exceptions;

public class InvalidAvatarException extends RuntimeException {
    public InvalidAvatarException(String message) {
        super(message);
    }

    public InvalidAvatarException(String message, Throwable cause) {
        super(message, cause);
    }
}
