package com.example.footballleagues;

import com.example.footballleagues.controller.FootballTeamJsonController;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.FootballTeamRepository;
import com.example.footballleagues.repository.LeagueRepository;
import com.example.footballleagues.service.LeagueService;
import com.example.footballleagues.service.LeagueServiceImpl;
import com.example.footballleagues.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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


    @Mock
    private LeagueRepository footballTeamRepository;
    @Mock
    private LeagueRepository leagueRepository;
    @Mock
    private LeagueService leagueService;
//    private AutoCloseable autoCloseable;


    @InjectMocks
    private FootballTeamJsonController footballTeamJsonController;


    @BeforeEach
    public void setup() {

        leagueService = new LeagueServiceImpl(leagueRepository) {
        };
    }

    @Test
    void getAllTeams() {
        League[] myLeages = {new League(), new League()};

        System.out.println(leagueService);

        when(leagueService.getAllLeagueInJsonService(ArgumentMatchers.anyString())).thenReturn(myLeages);

        // Perform test
        League[] returnedLeages = leagueService.getAllLeagues();


        // Verify result
        assertThat(myLeages).isSameAs(returnedLeages);
        verify(leagueService, times(1)).getAllLeagueInJsonService(null);
    }


//    @Test
//    public void canRetrieveByIdWhenExists() throws Exception {
//
//        given(footballTeamRepository.findById(2L))
//                .willReturn(Optional.of(new FootballTeam()));
//
//        MockHttpServletResponse response = mvc.perform(
//                        get("/json/teams/2")
//                                .accept(MediaType.APPLICATION_JSON))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo(
//                jsonFootballTeam.write(new FootballTeam()).getJson()
//        );
//    }


}
