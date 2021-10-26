package com.example.footballleagues.controller;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.exceptions.LeagueNotFoundException;
import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
    private final LeagueRepository leagueRepository;

    public LeagueController(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    @GetMapping()
    public String index(Model model) {
        Iterable iter = leagueRepository.findAll();
        model.addAttribute("league", iter);
        return "leagues";
    }

    @PostMapping()
    public String postIndex(@Valid @ModelAttribute League league) {
        leagueRepository.save(league);
        return "redirect:/leagues";
    }

    @PostMapping("/yourLeague/{id}")
    public String updateTeam(@PathVariable("id") Long id,
                            @Valid @ModelAttribute LeagueDto leagueDto) {

        League league = leagueRepository.findById(id).get();
        league.setName(leagueDto.getName());
        league.setCountry(leagueDto.getCountry());
        leagueRepository.save(league);
        return "redirect:/leagues";
    }


    @GetMapping("/updateLegue")
    public String showUpdateForm(@RequestParam(value = "id", required = false) Long id,
                                 Model model) {
        League league = leagueRepository.findById(id).orElseThrow(()
                -> new LeagueNotFoundException("id - " + id));
        model.addAttribute("league", league);
        return "yourLeague";
    }

    @GetMapping("/delete")
    public String deleteTeam(@RequestParam("id") Long id) {

        League league = leagueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid league Id:" + id));
        leagueRepository.delete(league);
        return "redirect:/leagues";
    }


}
