package com.example.footballleagues.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class LeagueDto {
    @Size(min = 2, message = "Name should have at least 2 characters ")
    private String name;
    @Size(min = 2, message = "Name should have at least 2 characters ")
    private String country;

    public LeagueDto(String name) {
        this.name = name;
    }

    public LeagueDto() {
    }
}
