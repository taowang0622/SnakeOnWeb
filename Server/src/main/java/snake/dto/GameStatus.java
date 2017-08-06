package snake.dto;

import java.io.Serializable;

/**
 * Created by taowang on 7/14/2017.
 */
public class GameStatus implements Serializable{
    private static final long serialVersionUID = 6529685098267757690L;

    private boolean isOver;
    private String IpOfOpponent;
    private String data;

    public GameStatus(boolean isOver, String ipOfOpponent, String data) {
        this.isOver = isOver;
        IpOfOpponent = ipOfOpponent;
        this.data = data;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "isOver=" + isOver +
                ", IpOfOpponent='" + IpOfOpponent + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public String getIpOfOpponent() {
        return IpOfOpponent;
    }

    public void setIpOfOpponent(String ipOfOpponent) {
        IpOfOpponent = ipOfOpponent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
