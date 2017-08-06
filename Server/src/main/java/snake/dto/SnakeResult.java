package snake.dto;

/**
 * Created by taowang on 7/15/2017.
 */
public class SnakeResult <T>{
    private boolean successful;
    private T  data;
    private String error;

    //successful
    public SnakeResult(boolean successful, T data, String error) {
        this.successful = successful;
        this.data = data;
        this.error = error;
    }

    //fail
    public SnakeResult(boolean successful, String error) {
        this.successful = successful;
        this.error = error;
    }


    //getters for Jackson databind to serialize object to JSON string!!!
    public boolean isSuccessful() {
        return successful;
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
