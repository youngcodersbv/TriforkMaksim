package com.example.footballleagues.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFootballTeam {
    @Test
    public void testFilter(){
        FootballTeam team = new FootballTeam();
        team.setHome("Piter");
        team.setName("Zenit");

        assertTrue(FootballTeam.filter("Pi", team));
        assertFalse(FootballTeam.filter("haag", team));

    }
}
