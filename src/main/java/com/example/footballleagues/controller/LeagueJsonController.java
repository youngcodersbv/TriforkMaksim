package com.example.footballleagues.controller;

import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "/json/leagues")
public class LeagueJsonController {
    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping
    public League[] getAllUserInJson() {
        Iterable<League> users = leagueRepository.findAll();

        // Loads all user into memory
        List<League> result = new ArrayList();
        users.forEach(result::add);

        return result.toArray(new League[result.size()]);
    }

    @GetMapping("/{id}")
    public League getUserById(@PathVariable("id") long id) {
        Optional<League> leagueOptional = leagueRepository.findById(id);

        return leagueOptional.get();
    }
}
