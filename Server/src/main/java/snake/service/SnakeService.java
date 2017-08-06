package snake.service;

/**
 * Created by taowang on 7/12/2017.
 */

import org.springframework.stereotype.Service;
import snake.entity.Player;
import snake.exception.ConnectionFailureException;
import snake.exception.IpConflictException;

import java.util.List;

/**
 * Design interface from the client's perspective!!!
 */
public interface SnakeService {

    /**
     * if player already exists, then set its status to ONLINE, and update its hostname.
     * if player is new one, then insert new one record to DB!
     * @param playerName
     * @return a Player object no matter player is newbie or not, and it will never be null
     */
    public Player login(String playerName, String hostName) throws IpConflictException;

    /**
     * Set the online status of the player with the passes id to false
     * @param id.
     */
    public void logout(int id);

    /**
     *
     * @param id
     * @param bestScore
     */
    public void updateBestScore(int id, int bestScore);

    public List<Player> getPlayerList();

    /**
     *
     * @param name
     * @return if player doesn't exist, then return null!
     */
    public Player getPlayerByName(String name);

    /**
     * set the player's status to PLAYING!!
     * @param id
     */
    public void startGame(int id);


}
