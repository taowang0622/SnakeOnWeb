package snake.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import snake.entity.Player;
import snake.enums.PlayerStatusEnum;
import snake.exception.IpConflictException;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by taowang on 7/13/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/application-context-service.xml", "classpath:spring/application-context-dao.xml"})
public class SnakeServiceTest {
    @Autowired
    private SnakeService snakeService;

    @Test
    public void login() throws Exception {
        //old player
        String playerName = "taowang";
        String hostName = "0.0.0.0";
        Player player = snakeService.login(playerName, hostName);
        Assert.assertEquals(PlayerStatusEnum.AVAILABLE.getStatusCode(), player.getStatus());
        Assert.assertEquals(playerName, player.getPlayerName());
        Assert.assertEquals(hostName, player.getHostName());
        //new player
        playerName = "jaycen";
        hostName = "23.45.67.90";
        Player newPlayer = snakeService.login(playerName, hostName);
        Assert.assertEquals(PlayerStatusEnum.AVAILABLE.getStatusCode(), newPlayer.getStatus());
        Assert.assertEquals(playerName, newPlayer.getPlayerName());
        Assert.assertEquals(hostName, newPlayer.getHostName());
    }

}