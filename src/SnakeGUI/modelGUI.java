package SnakeGUI;

import SnakeGame.GamePanel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by victo on 7/24/2017.
 */
public class modelGUI {

    private static String userID;

    private Map<String, Integer> serverData;

    public int score = 0;

    public boolean GameOver = false;

    public SnakeGame.GamePanel game;


    public modelGUI() {

    }

    public void setScore(int score)
    {
        this.score = score;

        System.out.println(this.score);
    }

    public void setGameOver(boolean gameover)
    {
        this.GameOver = gameover;
        System.out.println(this.GameOver);

    }
    private Map<String, Integer> getServer(){

        //Establish connection to server

        try {
            String url = "jdbc:http://ec2-34-231-46-198.compute-1.amazonaws.com/snake/players";
            Connection conn = DriverManager.getConnection(url,"","");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT bestScore FROM players WHERE playerName = victorlee");
            while ( rs.next() ) {
                String lastName = rs.getString("Lname");
                System.out.println(lastName);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }



        Map<String, Integer> server = new HashMap<>();

        //aaa is last place
        //zzz is first place
        server.put("bbb",1000);
        server.put("zzz",2000);
        server.put("ccc",1500);
        server.put("aaa",20);

        return server;
    }

    private void sortedRank(){

        Map<String, Integer> scoreRank = new TreeMap<>(Collections.reverseOrder());

        for(Map.Entry<String,Integer> a : getServer().entrySet()){
            scoreRank.put(a.getKey(), a.getValue());
        }

        this.serverData = scoreRank;
    }

    public String getScore(){

        sortedRank();

        String s = "";
        for(Map.Entry<String,Integer> a : serverData.entrySet()){
            s += "Player: " + a.getKey() + " | " + "Score: " + a.getValue()+"\n";
        }
        return s;
    }


    public static void setUserID(String userID) {
        modelGUI.userID = userID;
    }

    public static String getUserID() {
        return userID;
    }

    public static void main(String[] args) {

        try {
            String url = "http://ec2-34-231-46-198.compute-1.amazonaws.com/snake/players";
            //Connection conn = DriverManager.getConnection(url,"","");
            URL url2 = new URL(url);
            URLConnection urlConnection = url2.openConnection();
            HttpURLConnection urlConnection1 = (HttpURLConnection) urlConnection;
            urlConnection1.setRequestMethod("GET");

            urlConnection1.connect();

            InputStream inputStream = urlConnection1.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                System.out.println(line);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

}



