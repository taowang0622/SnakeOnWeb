package snake.web;

import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import snake.dto.SnakeResult;
import snake.entity.Player;
import snake.exception.IpConflictException;
import snake.service.SnakeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by taowang on 7/15/2017.
 */
@Controller
@RequestMapping("/snake")
public class SnakeController {
    @Autowired
    private SnakeService snakeService;

    @RequestMapping(path = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public SnakeResult<Player> login(@RequestParam("playerName") String playerName, HttpServletRequest req) {
        System.out.println("login starts!");
        if (playerName == null)
            return new SnakeResult<Player>(false, "Please input your player name");

        SnakeResult<Player> result;

        try {
            Player player = snakeService.login(playerName, req.getRemoteHost());
            result = new SnakeResult<Player>(true, player, null);
        } catch (IpConflictException ice) {
            result = new SnakeResult<Player>(false, ice.getMessage());
        } catch (Exception e) {
            result = new SnakeResult<Player>(false, e.getMessage());
        }

        return result;
    }

    @RequestMapping(path = "/{id}/logout", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SnakeResult<String> logout(@PathVariable("id") int id){
        SnakeResult<String> result = null;
        try{
            snakeService.logout(id);
            result = new SnakeResult<String>(true, "log out successful", null);
        } catch (Exception e){
            result = new SnakeResult<String>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/{id}/best_score", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    @ResponseBody
    public SnakeResult<String> updateBestScore(@PathVariable("id") int id, @RequestParam("bestScore") int bestScore){
        SnakeResult<String> result = null;
        try {
            snakeService.updateBestScore(id, bestScore);
            result = new SnakeResult<String>(true, "best score updated", null);
        } catch (Exception e) {
            result = new SnakeResult<String>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/players", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SnakeResult<List<Player>> list(){
        SnakeResult<List<Player>> result = null;
        try{
            List<Player> playerList = snakeService.getPlayerList();
            result = new SnakeResult<List<Player>>(true, playerList, null);
        } catch (Exception e){
            result = new SnakeResult<List<Player>>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/{id}/start", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SnakeResult<String> startGame(@PathVariable("id") int id){
        SnakeResult<String> result = null;
        try{
            snakeService.startGame(id);
            result = new SnakeResult<String>(true, "Game started", null);
        } catch (Exception e){
            result = new SnakeResult<String>(false, e.getMessage());
        }
        return result;
    }

}
