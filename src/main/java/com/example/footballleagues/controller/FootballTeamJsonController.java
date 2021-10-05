package com.example.footballleagues.controller;

import com.example.footballleagues.model.FootballTeam;
import com.example.footballleagues.repository.FootballTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static com.example.footballleagues.model.FootballTeam.createFilter;

@RestController()
@RequestMapping(path = "/json/team")
public class FootballTeamJsonController {
    @Autowired
    private FootballTeamRepository teamRepository;

    @GetMapping
    public FootballTeam[] getAllTeamInJson(@RequestParam(value = "filter",required = false) String filter) {
        Iterable<FootballTeam> commutes = teamRepository.findAll();

        List<FootballTeam> result = new ArrayList();
        StreamSupport.stream(commutes.spliterator(),false)
                .filter((FootballTeam team) -> {
                    if(filter == null) {
                        return true;
                    }
                    if(team.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                        return true;
                    } else if(team.getHome().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .filter((FootballTeam team) -> {
                    return FootballTeam.filter(filter, team);
                })
                .filter(commute -> FootballTeam.filter(filter, commute))
                .filter(createFilter(filter))
                .forEach(result::add);

        return result.toArray(new FootballTeam[result.size()]);
    }

    @GetMapping("/{id}")
    public FootballTeam getTeamById(@PathVariable("id") long id) {
        Optional<FootballTeam> optionalFootballTeam = teamRepository.findById(id);

        return optionalFootballTeam.get();
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable("id") long id) {
        Optional<FootballTeam> optionalFootballTeam = teamRepository.findById(id);
        if(optionalFootballTeam.isPresent()){
        FootballTeam footballTeam = optionalFootballTeam.get();
        teamRepository.delete(footballTeam);
        }
    }
}
