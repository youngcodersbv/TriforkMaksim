package com.example.footballleagues;

import com.example.footballleagues.model.League;
import com.example.footballleagues.repository.LeagueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FootballLeaguesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballLeaguesApplication.class, args);
    }

    @Bean
    public CommandLineRunner runAtStartOfApplicationContext(LeagueRepository repository) {
        return (args) -> {
            Iterable<League> iter = repository.findAll();

            if (!iter.iterator().hasNext()) {
                League league = new League();
                league.setName("English Premier League");
                repository.save(league);
            }
        };
    }
}
