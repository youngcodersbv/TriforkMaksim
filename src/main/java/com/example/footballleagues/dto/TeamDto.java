package com.example.footballleagues.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class TeamDto {
    @Size(min = 2, message = "Name should have at least 2 characters ")
    private String name;
    @Size(min = 2, message = "Home should have at least 2 characters ")
    private String home;
    private Long leagueId;

    public TeamDto(String name) {
        this.name = name;
    }

    public TeamDto() {
    }
}
