package dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import snake.dao.PlayersDao;
import snake.entity.Player;

import java.util.List;

/**
 * Created by taowang on 7/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-context-dao.xml")
public class PlayersDaoTest {
    @Autowired
    private PlayersDao playersDao;

    @Test
    public void queryAll() throws Exception {
        List<Player> players = playersDao.queryAll();
        players.forEach(player -> System.out.println(player));
    }

    @Test
    public void queryPlayer() throws Exception {
        String playerName = "taowang";
        Assert.assertEquals(playerName, playersDao.queryPlayer(playerName).getPlayerName());
        playerName = "kevin";
        Assert.assertEquals(null, playersDao.queryPlayer(playerName));
    }

    @Test
    public void setPlaying() throws Exception {
        String playerName = "taowang";
        Player player = playersDao.queryPlayer(playerName);
        playersDao.setPlaying(player.getId());
        Assert.assertEquals(1, playersDao.queryPlayer(playerName).getStatus());
    }

    @Test
    public void setOffline() throws Exception {
        String playerName = "taowang";
        Player player = playersDao.queryPlayer(playerName);
        playersDao.setOffline(player.getId());
        Assert.assertEquals(0, playersDao.queryPlayer(playerName).getStatus());
    }

    @Test
    public void setAvailable() throws Exception {
        String playerName = "taowang";
        Player player = playersDao.queryPlayer(playerName);
        playersDao.setAvailable(player.getId());
        Assert.assertEquals(2, playersDao.queryPlayer(playerName).getStatus());
    }

    @Test
    public void setBestScore() throws Exception {
        String playerName = "taowang";
        Player player = playersDao.queryPlayer(playerName);
        playersDao.setBestScore(100, player.getId());
        Assert.assertEquals(100, playersDao.queryPlayer(player.getPlayerName()).getBestScore());
    }

    @Test
    public void setHostName() throws Exception {
        String playerName = "taowang";
        Player player = playersDao.queryPlayer(playerName);
        String hostName = "123.456.789.000";
        playersDao.setHostName(hostName, player.getId());
        Assert.assertEquals(hostName, playersDao.queryPlayer(player.getPlayerName()).getHostName());
    }

    @Test
    public void insertPlayer() throws Exception {
        String playerName = "victorlee";
        int i = playersDao.insertPlayer(playerName, "192.166.21.245");
        if (i != 0) {
            Player player = playersDao.queryPlayer(playerName);
            Assert.assertEquals(2, player.getStatus()); //When inserting a player, it suggests he or she is online!!!
            Assert.assertEquals(0, player.getBestScore());
            Assert.assertEquals(0, playersDao.insertPlayer(playerName, "192.166.21.245")); //Avoiding inserting the same player name repeatedly!!
        }
    }

}