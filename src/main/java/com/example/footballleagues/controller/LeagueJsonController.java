package com.example.footballleagues.controller;

import com.example.footballleagues.client.GiphyClient;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@Slf4j
@RequestMapping(path = "/json/leagues")
@RequiredArgsConstructor
public class LeagueJsonController {
    private final LeagueRepository leagueRepository;

    @GetMapping
    public League[] getAllUserInJson(HttpServletResponse httpServletResponse) throws IOException {
        Iterable<League> users = leagueRepository.findAll();
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
