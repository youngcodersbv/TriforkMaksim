package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.repository.FootballTeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestTeamController {

    @Autowired
    private FootballTeamController controller;
    @Autowired
    private FootballTeamRepository repository;

    @Test
    public void initializtionTest() {
        // Check the context is loaded
        assertThat(controller).isNotNull();
    }

    @Test
    public void testModelProperties() {
        Model model = new ConcurrentModel();
        controller.teams(model);
        assertThat(model.getAttribute("team")).isNotNull();
    }


}
