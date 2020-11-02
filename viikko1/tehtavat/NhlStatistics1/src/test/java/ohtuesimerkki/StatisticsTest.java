/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;


/**
 *
 * @author Jenna
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void findsPlayerByName() {
        assertEquals("Kurri", stats.search("Kurri").getName());
    }
    
    @Test
    public void findsNoUnexistingPlayersByName() {
        assertNull(stats.search("Testi"));
    }
    
    @Test
    public void listsFullTeam() {
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void sizeOfANonexistingTeamIsZero() {
        assertEquals(0, stats.team("Testi").size());
    }
    
    @Test
    public void findsPlayerWithTopScore() {
        List<Player> topPlayers = stats.topScorers(1);
        assertEquals("Gretzky", topPlayers.get(0).getName());
    }
}
