package com.example.footballleagues.service;

import com.example.footballleagues.dto.LeagueDto;
import com.example.footballleagues.model.League;

public interface LeagueService {
    League[] getAllLeagueInJsonService(String filter);
    League getLeagueById(long id);
    void deleteLeagueById(long id);
    void updateLeague(long id, LeagueDto leagueDto);

    League[] getAllLeagues();
}
