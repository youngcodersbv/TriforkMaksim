package com.example.footballleagues.controller;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestLeagueJsonController {
    @Autowired
    LeagueRepository repository;
    @Autowired
    LeagueJsonController controller;

    @Test
    public void testDeleteLeague(){
        League league = new League("NEW LEAGUE");
        repository.save(league);
        Long id = league.getId();

        controller.deleteLeagueById(id);

        Optional<League> leagueOptional = repository.findById(id);
        assertTrue(leagueOptional.isEmpty());
    }

    @Test
    public void testUpdateLeague(){
        League league = new League("OLD");
        repository.save(league);
        Long id = league.getId();

        LeagueDto leagueDto = new LeagueDto("NEW");
        leagueDto.setCountry("Italy");

        controller.updateLeagueById(id,leagueDto);

        League league1 = repository.findById(id).get();
        assertTrue(league1.getName().equals("NEW"));
        assertTrue(league1.getCountry().equals("Italy"));

        controller.deleteLeagueById(id);
    }

}
