package com.example.footballleagues.repository;

import com.example.footballleagues.model.League;
import org.springframework.data.repository.CrudRepository;

public interface LeagueRepository extends CrudRepository<League, Long> {
}
