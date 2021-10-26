package com.example.footballleagues.controller;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.model.League;
import com.example.footballleagues.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController()
@Slf4j
@RequestMapping(path = "/json/leagues")
@RequiredArgsConstructor
public class LeagueJsonController {


    private final LeagueService leagueService;
    @GetMapping
    public League[] getAllUserInJson(@RequestParam(value = "filter", required = false) String filter) throws IOException {
        return leagueService.getAllLeagueInJsonService(filter);
    }

    @GetMapping("/{id}")
    public League getLeagueById(@PathVariable("id") long id) {
        return leagueService.getLeagueById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLeagueById(@PathVariable("id") long id) {
        leagueService.deleteLeagueById(id);
    }

    @PutMapping("/{id}")
    public void updateLeagueById(@PathVariable("id") long id, @Valid @RequestBody LeagueDto leagueDto) {
        leagueService.updateLeague(id,leagueDto);
    }
}
