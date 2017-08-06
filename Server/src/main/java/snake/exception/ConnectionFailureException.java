package snake.exception;

/**
 * Created by taowang on 7/12/2017.
 */
public class ConnectionFailureException extends SnakeException{
    public ConnectionFailureException(String message){
        super(message);
    }

    public ConnectionFailureException(String message, Throwable cause){
        super(message, cause);
    }
}
