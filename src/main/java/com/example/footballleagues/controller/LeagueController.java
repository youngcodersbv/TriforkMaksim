package com.example.footballleagues.controller;

import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/leagues")
public class LeagueController {
    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping()
    public String index(Model model) {
        Iterable iter = leagueRepository.findAll();
        model.addAttribute("league", iter);
        return "leagues";
    }

    @PostMapping()
    public String postIndex(@ModelAttribute League league) {
        leagueRepository.save(league);
        return "redirect:/leagues";
    }
}
