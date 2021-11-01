package com.example.footballleagues;

import com.example.footballleagues.controller.LeagueJsonController;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import com.example.footballleagues.service.LeagueServiceImpl;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class MockTestLeagueController {
    private MockMvc mvc;

    @Mock
    private LeagueRepository repository;
    @Mock
    private LeagueServiceImpl service;
    private AutoCloseable autoCloseable;


    @InjectMocks
    private LeagueJsonController leagueJsonController;

    private JacksonTester<League> getJson;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(leagueJsonController)
                .build();
        service = new LeagueServiceImpl(repository);
    }

    @Test
    void getAllTeams() {
        List<League> leagues = new ArrayList<>();
        League league = new League();
        league.setName("MY");
        league.setCountry("England");
        leagues.add(league);

        when(repository.findAll()).thenReturn(leagues);
        List<League> myLeags = List.of(service.getAllLeagueInJsonService("MY"));

        assertThat(myLeags.get(0)).isSameAs(leagues.get(0));
        verify(repository, times(1)).findAll();
    }


    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get("/json/leagues/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }


}
