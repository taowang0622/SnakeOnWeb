package snake.entity;

import snake.enums.PlayerStatusEnum;

import java.util.Date;

/**
 * Created by taowang on 7/12/2017.
 */
public class Player {
    private int id;
    private String playerName;
    private int status;
    private String hostName;
    private int bestScore;
    private Date createTime;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", status=" + PlayerStatusEnum.statusOf(status) +   //Using enum to be more readable!!
                ", hostName='" + hostName + '\'' +
                ", bestScore=" + bestScore +
                ", createTime=" + createTime +
                '}';
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
