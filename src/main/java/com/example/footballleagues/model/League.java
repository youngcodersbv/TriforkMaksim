package com.example.footballleagues.model;

import javax.persistence.*;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;

@Entity
@Table(name = "leagues", schema = "public")
public class League {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    private String country;
    @OneToMany(mappedBy = "league", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<FootballTeam> footballTeamSet;

    public League(String name) {
        this.name = name;
    }

    public static Predicate<League> createFilter(String filter) {
        return new Predicate<League>() {
            @Override
            public boolean test(League league) {
                return filter(filter, league);
            }
        };
    }

    public static boolean filter(String filter, League league) {
        if (filter == null) {
            return true;
        }
        if (league.getName().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
            return true;
        } else if (league.getCountry().toLowerCase(Locale.ROOT).contains(filter.toLowerCase(Locale.ROOT))) {
            return true;
        } else {
            return false;
        }
    }

    public League() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<FootballTeam> getFootballTeamSet() {
        return footballTeamSet;
    }

    public void setFootballTeamSet(Set<FootballTeam> footballTeamSet) {
        this.footballTeamSet = footballTeamSet;
    }

    @Override
    public String toString() {
        return name;
    }
}
