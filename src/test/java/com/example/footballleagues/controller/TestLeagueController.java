package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.model.League;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestLeagueController {

    @Autowired
    private LeagueController controller;

    @Test
    public void initializtionTest() {
        // Check the context is loaded
        assertThat(controller).isNotNull();
    }

    @Test
    public void testModelProperties() {
        Model model = new ConcurrentModel();
        controller.index(model);
        assertThat(model.getAttribute("league")).isNotNull();
    }

    @Test
    public void testModelPost() {
        League league = new League();
        league.setName("England");
        String st = controller.postIndex(league);
        assertThat(st.equals("redirect:/leagues"));
    }
}
