package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        //palauttaa kaiken tiedon
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        List<Player> playersList = Arrays.asList(players);
        Collections.sort(playersList);
        
        System.out.println("Players from FIN:");        
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                System.out.println(player);
            }
        }   
    }
  
}