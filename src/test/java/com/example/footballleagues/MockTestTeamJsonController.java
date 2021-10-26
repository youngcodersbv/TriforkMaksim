package com.example.footballleagues;

import com.example.footballleagues.controller.FootballTeamJsonController;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.repository.LeagueRepository;
import com.example.footballleagues.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class MockTestTeamJsonController {
    private MockMvc mvc;

    @Mock
    private FootballTeamRepository footballTeamRepository;
    private LeagueRepository leagueRepository;
    @Mock
    private TeamService teamService;
    private AutoCloseable autoCloseable;


    @InjectMocks
    private FootballTeamJsonController footballTeamJsonController;

    private JacksonTester<FootballTeam> jsonFootballTeam;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(footballTeamJsonController)
                .build();
        teamService = new TeamService(footballTeamRepository,leagueRepository);
    }

    @Test
    void getAllTeams() {
        List<FootballTeam> teams = new ArrayList<>();
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setName("London");
        teams.add(footballTeam);

        when(footballTeamRepository.findAll()).thenReturn(teams);
        List<FootballTeam> myTeams = List.of(teamService.getAllTeamInJsonService("London"));

        assertThat(myTeams.get(0)).isSameAs(teams.get(0));
        verify(footballTeamRepository, times(1)).findAll();
    }


    @Test
    public void canRetrieveByIdWhenExists() throws Exception {

        given(footballTeamRepository.findById(2L))
                .willReturn(Optional.of(new FootballTeam()));

        MockHttpServletResponse response = mvc.perform(
                        get("/json/teams/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonFootballTeam.write(new FootballTeam()).getJson()
        );
    }


}
