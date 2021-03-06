package com.example.footballleagues.controller;

import com.example.footballleagues.model.League;
import com.example.footballleagues.service.LeagueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(LeagueJsonController.class)
public class LeagueJsonControllerTest {


    @MockBean
    LeagueService leagueService;

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAllLeague() throws Exception {

        // Setup behaviour
        League[] myLeages = {new League(), new League()};

        System.out.println(leagueService);

        when(leagueService.getAllLeagueInJsonService(ArgumentMatchers.anyString())).thenReturn(myLeages);

        // Perform test
        RequestBuilder request = MockMvcRequestBuilders.get("/json/leagues?filter=");
        MvcResult result = mvc.perform(request).andReturn();

        System.out.println(result.getResponse().getContentAsString());

        //League[] returnedLeages = controller.getAllLeagueInJson(null);


        // Verify result
        //assertThat(myLeages).isSameAs(returnedLeages);
        //verify(leagueService, times(1)).getAllLeagueInJsonService(null);
    }
}
