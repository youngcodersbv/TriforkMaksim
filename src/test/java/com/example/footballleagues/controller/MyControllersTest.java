package com.example.footballleagues.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyControllersTest {

    @Autowired
    private FootballTeamController footballTeamController;

    @Autowired
    private LeagueController leagueController;


    @Test
    public void initializtionTest() {
        assertThat(footballTeamController).isNotNull();
    }
    @Test
    public void initializtionTestLeague() {
        assertThat(leagueController).isNotNull();
    }



    @Test
    public void testModelProperties() {
        Model model = new ConcurrentModel();
        footballTeamController.teams(model);
        assertThat(model.getAttribute("team")).isNotNull();
    }
}
