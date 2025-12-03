package pe.iotteam.plantcare.user.domain.exceptions;

public class AvatarStorageException extends RuntimeException {
    public AvatarStorageException(String message) {
        super(message);
    }

    public AvatarStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
