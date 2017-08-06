package snake.dao;

import org.apache.ibatis.annotations.Param;
import snake.entity.Player;

import java.util.List;

/**
 * Created by taowang on 7/12/2017.
 */
public interface PlayersDao {
    /**
     *
     * @return a list of all existing players
     */
    List<Player> queryAll();

    /**
     *
     * @param playerName
     * @return a Player object. if query fails, return null
     */
    Player queryPlayer(@Param("playerName") String playerName);

    /**
     *
     * @param playerId
     * @return # of rows affected. If it's 0, then switch fails
     */
    int setPlaying(@Param("id") int playerId);

    /**
     *
     * @param playerId
     * @return # of rows affected. If it's 0, then switch fails
     */
    int setOffline(@Param("id") int playerId);

    /**
     *
     * @param playerId
     * @return # of rows affected. If it's 0, then switch fails
     */
    int setAvailable(@Param("id") int playerId);

    /**
     *
     * @param bestScore
     * @return # of rows affected. if it's 0, then fails!!
     */
    int setBestScore(@Param("bestScore") int bestScore, @Param("id") int playerId);

    /**
     *
     * @param hostName
     * @param playerId
     * @return
     */
    int setHostName(@Param("hostName") String hostName, @Param("id") int playerId);

    /**
     * This is for players who log in for the first time!!so the best score must be 0 and online is true!!
     * @param player
     * @return # of rows affected and if it's 0, then insertion failed and player
     */
    int insertPlayer(@Param("playerName") String playerName, @Param("hostName") String hostName);
}
