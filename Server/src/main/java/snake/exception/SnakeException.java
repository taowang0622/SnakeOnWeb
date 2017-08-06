package snake.exception;

import com.sun.jmx.snmp.ServiceName;

/**
 * Created by taowang on 7/12/2017.
 */
public class SnakeException extends RuntimeException{
    public SnakeException(String message){
        super(message);
    }

    public SnakeException(String message, Throwable cause){
        super(message, cause);
    }
}
