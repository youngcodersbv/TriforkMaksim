package com.example.footballleagues.controller;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.model.League;
import com.example.footballleagues.service.LeagueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@RestController()
@Slf4j
@RequestMapping(path = "/json/leagues")
@RequiredArgsConstructor
public class LeagueJsonController {

    private final LeagueService leagueService;

    @GetMapping
    public League[] getAllLeagueInJson(@RequestParam(value = "filter", required = false) String filter) {
        return leagueService.getAllLeagueInJsonService(filter);
    }
//
//    @GetMapping
//    public League[] getAllLeagueInJson() {
//        return leagueService.getAllLeagues();
//    }

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
        leagueService.updateLeague(id, leagueDto);
    }
}
