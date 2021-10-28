package com.example.footballleagues.controller;

import com.example.footballleagues.dto.TeamDto;
import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.repository.FootballTeamRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestTeamJsonController {
    @Autowired
    FootballTeamRepository repository;
    @Autowired
    FootballTeamJsonController controller;

    /*@Test
    public void testUpdateTeam(){
        FootballTeam team = new FootballTeam("Milan");
        repository.save(team);
        Long id = team.getId();

        TeamDto teamDto = new TeamDto("Zenit");
        teamDto.setLeagueId(33L);
        teamDto.setHome("Rome");

        controller.updateTeamById(id,teamDto);

        FootballTeam footballTeam = repository.findById(id).get();
        assertFalse(footballTeam.getName().equals("Milan"));
        assertTrue(footballTeam.getHome().equals("Rome"));

        controller.deleteTeamById(id);

    }*/

    /*@Ignore
    @Test
    public void testDeleteTeam(){
        FootballTeam team = new FootballTeam("Ajax");
        repository.save(team);
        Long id = team.getId();

        controller.deleteTeamById(id);

        Optional<FootballTeam> footballTeam = repository.findById(id);
        assertTrue(footballTeam.isEmpty());
    }*/

}
