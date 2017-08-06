package snake.exception;

/**
 * Created by taowang on 7/12/2017.
 */
public class IpConflictException extends SnakeException{
    public IpConflictException(String message){
        super(message);
    }

    public IpConflictException(String message, Throwable cause){
        super(message, cause);
    }
}
