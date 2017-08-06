package snake.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import snake.dao.PlayersDao;
import snake.entity.Player;
import snake.enums.PlayerStatusEnum;
import snake.exception.IpConflictException;
import snake.service.SnakeService;

import java.util.List;

/**
 * Created by taowang on 7/13/2017.
 */
@Service
public class SnakeServiceImpl implements SnakeService{
    @Autowired
    private PlayersDao playersDao;


    @Override
    public Player login(String playerName, String hostName) {
        Player player = playersDao.queryPlayer(playerName);
        if (player == null){
            //New player
            int numOfAffectedRows = playersDao.insertPlayer(playerName, hostName);
//            //if ip exists already, then throw exception!!
//            if(numOfAffectedRows == 0) throw new IpConflictException("IP conflicts");
        }else {
            //old player
            playersDao.setAvailable(player.getId());
            playersDao.setHostName(hostName, player.getId());
        }
        return playersDao.queryPlayer(playerName);
    }

    @Override
    public void logout(int id) {
        playersDao.setOffline(id);
    }

    @Override
    public void updateBestScore(int id, int bestScore) {
        playersDao.setBestScore(bestScore, id);
    }

    @Override
    public List<Player> getPlayerList() {
        return playersDao.queryAll();
    }

    @Override
    public Player getPlayerByName(String name) {
        return playersDao.queryPlayer(name);
    }

    @Override
    public void startGame(int id) {
        playersDao.setPlaying(id);
    }
}
