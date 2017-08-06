package snake.enums;

/**
 * Created by taowang on 7/13/2017.
 */
public enum PlayerStatusEnum {
    //enumeration constants
    //status code 0, 1 and 2 are mapped to the data stored in DB!!
    OFFLINE(0, "offline, not available"),
    PLAYING(1, "playing with other player"),
    AVAILABLE(2, "available to play against");


    private int statusCode;
    private String statusInfo;

    private PlayerStatusEnum(int statusCode, String statusInfo) {
        this.statusCode = statusCode;
        this.statusInfo = statusInfo;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    /**
     * A utility method for finding out what status a status code represents
     * for mapping the data in DB!!!
     * @param
     * @return
     */
    static public PlayerStatusEnum statusOf(int statusCode){
        for (PlayerStatusEnum status : values()) {
            if(status.getStatusCode() == statusCode)
                return status;
        }
        return null;
    }
}
